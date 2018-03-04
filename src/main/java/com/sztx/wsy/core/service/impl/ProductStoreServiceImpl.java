package com.sztx.wsy.core.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.sztx.wsy.common.domain.BusinessException;
import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.ProductStoreService;
import com.sztx.wsy.core.service.domain.request.ProductStoreDetailVO;
import com.sztx.wsy.core.service.domain.request.ProductStoreOperateListReq;
import com.sztx.wsy.core.service.domain.request.ProductStoreOperateListRsp;
import com.sztx.wsy.core.service.domain.request.ProductStoreOperateRequest;
import com.sztx.wsy.core.service.domain.request.ProductStoreVO;
import com.sztx.wsy.dataaccess.dao.AccountMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ProductMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ProductStoreMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ProductStoreOperateDetailMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ProductStoreOperateMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ProductStoreSetMysqlDAO;
import com.sztx.wsy.dataaccess.domain.AccountDO;
import com.sztx.wsy.dataaccess.domain.ProductDO;
import com.sztx.wsy.dataaccess.domain.ProductStoreDO;
import com.sztx.wsy.dataaccess.domain.ProductStoreOperateDO;
import com.sztx.wsy.dataaccess.domain.ProductStoreOperateDetailDO;
import com.sztx.wsy.util.LoginUtil;
import com.sztx.wsy.util.MsgUtil;
import com.sztx.wsy.util.ValidateUtil;

@Service("productStoreService")
public class ProductStoreServiceImpl implements ProductStoreService{
	
	private static final Logger log = LoggerFactory.getLogger(ProductStoreServiceImpl.class);
	
	@Autowired
	private ProductMysqlDAO productMysqlDAO;

	@Autowired
	private ProductStoreMysqlDAO productStoreMysqlDAO;
	
	@Autowired
	private ProductStoreSetMysqlDAO productStoreSetMysqlDAO;
	
	@Autowired
	private ProductStoreOperateMysqlDAO productStoreOperateMysqlDAO;
	
	@Autowired
	private ProductStoreOperateDetailMysqlDAO productStoreOperateDetailMysqlDAO;
	
	@Autowired
	private AccountMysqlDAO accountMysqlDAO;

	@Override
	public List<ProductStoreDO> findProductStoreDetail(Integer productId) {
		
		ValidateUtil.notNull(productId, "产品id不能为空");
		List<ProductStoreDO> productStoreDOs = productStoreMysqlDAO.findByProductId(productId);//产品库存
		Collections.sort(productStoreDOs);
		return productStoreDOs;
	}

	@Override
	public void productOutputOrInput(ProductStoreOperateRequest request) {
		
		chackProductStoreOperateRequest(request);//校验参数
		
		ProductStoreOperateDO productStoreOperateDO = makeProductStoreOperate(request);
		productStoreOperateMysqlDAO.add(productStoreOperateDO);//添加库存操作表
		
		log.info("新增的库存操作id为：" + productStoreOperateDO.getId());
		
		List<ProductStoreOperateDetailDO> productStoreOperateDetailList = makeProductStoreOperateDetail(request, productStoreOperateDO.getId());
		productStoreOperateDetailMysqlDAO.batchAdd(productStoreOperateDetailList);//添加库存操作详情表
		
		List<ProductStoreDO> productStoreList = makeProductStore(request);
		productStoreMysqlDAO.batchUpdate(productStoreList);//更新库存表
		
		ProductDO productDO = new ProductDO();
		productDO.setId(request.getProductId());
		productDO.setStatus(storeStatus(request) == true ? 1 : 2);
		productDO.setStoreTotalNum(productStoreOperateDO.getStoreAfterNum());
		productDO.setUpdateUser(request.getCreateUser());
		productMysqlDAO.update(productDO);//更新产品表
		
		//超级管理员出库短信通知下案管理员
		if(request.getType() == 1 && LoginUtil.currentUser().getType() == 1){
			Integer operateNum = productStoreOperateDO.getStoreOperateNum();
			List<AccountDO> accountDOs = accountMysqlDAO.findByType(1);
			String tplValue = "#productName#=" + request.getProductName() + "&#productNum#=" + operateNum;
			for(AccountDO accountDO : accountDOs){
				MsgUtil.sendSMS("32124", tplValue, accountDO.getMobile());
			}
		}
 	}
	
	@Override
	public Integer totalCount(ProductStoreOperateListReq request) {
		return productStoreOperateMysqlDAO.totalCount(request);
	}

	/**
	 * 分页查询出入库操作列表
	 */
	@Override
	public List<ProductStoreOperateListRsp> productOutputOrInputList(
			ProductStoreOperateListReq request, PageQuery pageQuery) {
		
		List<ProductStoreOperateDO> productStoreOperateDOs = productStoreOperateMysqlDAO.findByPage(request, pageQuery);
		List<ProductStoreOperateListRsp> productStoreOperateListRsps = new ArrayList<ProductStoreOperateListRsp>();
		
		for(ProductStoreOperateDO productStoreOperateDO : productStoreOperateDOs){
			
			ProductStoreOperateListRsp rsp = new ProductStoreOperateListRsp();
			
			List<ProductStoreOperateDetailDO> productStoreOperateDetailDOs = productStoreOperateDetailMysqlDAO.findByProductStoreOperateId(productStoreOperateDO.getId());
			
			BeanUtils.copyProperties(productStoreOperateDO, rsp);
			rsp.setStoreBefore(JSONObject.parse(productStoreOperateDO.getStoreBefore()));
			rsp.setStoreAfter(JSONObject.parse(productStoreOperateDO.getStoreAfter()));
			rsp.setStoreOperate(productStoreOperateDetailDOs);
			productStoreOperateListRsps.add(rsp);
		}
		return productStoreOperateListRsps;
	}
	
	/**
	 * 更新库存设置
	 */
	@Override
	public void updateSotreSet(List<ProductStoreDO> productStoreDOs, Integer productId) {
		
		for(ProductStoreDO productStoreDO : productStoreDOs){
			if(productStoreDO.getId() == null || productStoreDO.getStoreMax() == null || productStoreDO.getStoreMin() == null){
				throw new BusinessException("参数异常");
			}
		}
		productStoreMysqlDAO.batchUpdateStoreSet(productStoreDOs);
		
		//更新产品库存状态
		List<ProductStoreDO> newProductStoreDOs = productStoreMysqlDAO.findByProductId(productId);
		boolean storeStatus = true;
		for(ProductStoreDO productStoreDO : newProductStoreDOs){
			
			Integer currentStoreNum = productStoreDO.getShoeNum();//当前库存数量
			Integer maxStoreNum = productStoreDO.getStoreMax();//库存上限
			Integer minStoreNum = productStoreDO.getStoreMin();//库存下限
			if(currentStoreNum > maxStoreNum || currentStoreNum < minStoreNum){
				storeStatus = false;
				break;
			}
		}
		ProductDO productDO = new ProductDO();
		productDO.setId(productId);
		productDO.setStatus(storeStatus == true ? 1 : 2);
		productDO.setUpdateUser(LoginUtil.currentRealname());
		productMysqlDAO.update(productDO);
	}
	
	public void chackProductStoreOperateRequest(ProductStoreOperateRequest request){
		
		if(request.getProductId() == null || request.getStoreBeforeNum() == null 
				|| request.getType() == null){
			throw new BusinessException("参数异常");
		}
		List<ProductStoreVO> productStoreVos = request.getProductStoreVOs();
		ValidateUtil.notNull(productStoreVos, "参数异常");
		
		for(ProductStoreVO productStore : productStoreVos ){
			
			ValidateUtil.notNull(productStore.getOperateNum(), "库存操作数量不能为空");
			if(1 == request.getType()){//出库
				
				ValidateUtil.ge(productStore.getShoeNum(), productStore.getOperateNum(), "出库数量不能大于库存量");
			}
		}
	}
	
	/**
	 * 组装库存操作记录
	 * @param request
	 * @return
	 */
	public ProductStoreOperateDO makeProductStoreOperate(ProductStoreOperateRequest request){
		
		Integer storeOperateNum = 0;//库存操作数量
		Integer storeAfterNum = 0;//操作后库存数量
		List<ProductStoreDetailVO> beforeDetailVOs = new ArrayList<ProductStoreDetailVO>();//操作前库存详情
		List<ProductStoreDetailVO> afterDetailVOs = new ArrayList<ProductStoreDetailVO>();//操作后库存详情
		
		List<ProductStoreVO> productStoreVos = request.getProductStoreVOs();
		for(ProductStoreVO productStore : productStoreVos ){
			
			storeOperateNum = storeOperateNum + productStore.getOperateNum();
			
			ProductStoreDetailVO beforeDetailVO = new ProductStoreDetailVO();
			beforeDetailVO.setShoeCode(productStore.getShoeCode());
			beforeDetailVO.setShoeNum(productStore.getShoeNum());
			
			ProductStoreDetailVO afterDetailVO = new ProductStoreDetailVO();
			afterDetailVO.setShoeCode(productStore.getShoeCode());
			if(1 == request.getType()){
				afterDetailVO.setShoeNum(productStore.getShoeNum() - productStore.getOperateNum());
			}else{
				afterDetailVO.setShoeNum(productStore.getShoeNum() + productStore.getOperateNum());
			}
			
			beforeDetailVOs.add(beforeDetailVO);
			afterDetailVOs.add(afterDetailVO);
		}
		
		if(1 == request.getType()){
			storeAfterNum = request.getStoreBeforeNum() - storeOperateNum;
		}else{
			storeAfterNum = request.getStoreBeforeNum() + storeOperateNum;
		}
		log.info("操作前库存数量："+request.getStoreBeforeNum());
		log.info("操作后库存数量："+storeAfterNum);
		log.info("库存操作数量："+storeOperateNum);
		log.info("操作前库存详情："+JSONObject.toJSONString(beforeDetailVOs));
		log.info("操作后库存详情："+JSONObject.toJSONString(afterDetailVOs));
		
		ProductStoreOperateDO productStoreOperateDO = new ProductStoreOperateDO();
		productStoreOperateDO.setProductId(request.getProductId());
		productStoreOperateDO.setProductName(request.getProductName());
		productStoreOperateDO.setType(request.getType());
		productStoreOperateDO.setStoreBeforeNum(request.getStoreBeforeNum());
		productStoreOperateDO.setStoreOperateNum(storeOperateNum);
		productStoreOperateDO.setStoreAfterNum(storeAfterNum);
		productStoreOperateDO.setStoreBefore(JSONObject.toJSONString(beforeDetailVOs));
		productStoreOperateDO.setStoreAfter(JSONObject.toJSONString(afterDetailVOs));
		productStoreOperateDO.setRemak(request.getRemak());
		productStoreOperateDO.setCreateUser(request.getCreateUser());
		return productStoreOperateDO;
	}
	
	/**
	 * 组装库存操作详情数据
	 * @param request
	 * @return
	 */
	public List<ProductStoreOperateDetailDO> makeProductStoreOperateDetail(ProductStoreOperateRequest request, Integer productStoreOperateId){
		
		List<ProductStoreOperateDetailDO> productStoreOperateDetailDOs = new ArrayList<ProductStoreOperateDetailDO>();
		
		List<ProductStoreVO> productStoreVos = request.getProductStoreVOs();
		for(ProductStoreVO productStore : productStoreVos ){
			
			ProductStoreOperateDetailDO productStoreOperateDetail = new ProductStoreOperateDetailDO();
			productStoreOperateDetail.setProductStoreOperateId(productStoreOperateId);
			productStoreOperateDetail.setShoeCode(productStore.getShoeCode());
			productStoreOperateDetail.setShoeNum(productStore.getOperateNum());
			
			productStoreOperateDetailDOs.add(productStoreOperateDetail);
		}
		
		return productStoreOperateDetailDOs;
	}
	
	/**
	 * 组装产品库存数据
	 * @param request
	 * @return
	 */
	public List<ProductStoreDO> makeProductStore(ProductStoreOperateRequest request){
		
		List<ProductStoreDO> productStoreDOs = productStoreMysqlDAO.findByProductId(request.getProductId());
		
//		List<ProductStoreDO> afterProductStoreDOs = new ArrayList<ProductStoreDO>();
		
		List<ProductStoreVO> productStoreVos = request.getProductStoreVOs();
		for(ProductStoreVO productStoreVO : productStoreVos ){
			
			Integer shoeCode = productStoreVO.getShoeCode();
			for(ProductStoreDO productStoreDO : productStoreDOs){
//				ProductStoreDO productStore = new ProductStoreDO();
				if(shoeCode.equals(productStoreDO.getShoeCode())){
					Integer afterStoreNum = 0;
					if(1 == request.getType()){
						afterStoreNum = productStoreDO.getShoeNum() - productStoreVO.getOperateNum();
					}else{
						afterStoreNum = productStoreDO.getShoeNum() + productStoreVO.getOperateNum();
					}
//					BeanUtils.copyProperties(productStoreDO, productStore);
					productStoreDO.setShoeNum(afterStoreNum);
//					afterProductStoreDOs.add(productStore);
					break;
				}
			}
		}
		return productStoreDOs;
	}
	
	/**
	 * 产品库存状态
	 * @param request
	 * @return
	 */
	public Boolean storeStatus(ProductStoreOperateRequest request){
		
		boolean status = true;
		Integer storeAfterNum = 0;//操作后库存数量
		List<ProductStoreVO> productStoreVos = request.getProductStoreVOs();
		for(ProductStoreVO productStore : productStoreVos ){
			//如果库存设置无限
			if(productStore.getStoreMax().equals(-1) && productStore.getStoreMin().equals(-1)){
				continue;
			}
			if(!productStore.getStoreMax().equals(-1)){
				if(2 == request.getType()){
					storeAfterNum = request.getStoreBeforeNum() + productStore.getOperateNum();
					if(storeAfterNum > productStore.getStoreMax() || storeAfterNum < productStore.getStoreMin()){
						status = false;
						break;
					}
				}
			}
			if(!productStore.getStoreMin().equals(-1)){
				if(1 == request.getType()){
					storeAfterNum = request.getStoreBeforeNum() - productStore.getOperateNum();
					if(storeAfterNum < productStore.getStoreMin() || storeAfterNum > productStore.getStoreMax()){
						status = false;
						break;
					}
				}
			}
		}
		return status;
	}
	
	public static void main(String[] args) {
		
		List<ProductStoreDO> productStoreDOs = new ArrayList<ProductStoreDO>();
		ProductStoreDO p1 = new ProductStoreDO();
		p1.setShoeCode(34);
		p1.setShoeNum(12);
		ProductStoreDO p2 = new ProductStoreDO();
		p2.setShoeCode(35);
		p2.setShoeNum(10);
		ProductStoreDO p3 = new ProductStoreDO();
		p3.setShoeCode(36);
		p3.setShoeNum(8);
		productStoreDOs.add(p1);
		productStoreDOs.add(p2);
		productStoreDOs.add(p3);
		
		List<ProductStoreDO> productStoreVOs = new ArrayList<ProductStoreDO>();
		productStoreVOs.add(p1);
		productStoreVOs.add(p2);
		productStoreVOs.add(p3);
		
		for(ProductStoreDO productStoreDO : productStoreDOs){
			
			for(ProductStoreDO productStoreVO : productStoreVOs){
				if(productStoreDO.getShoeCode().equals(productStoreVO.getShoeCode())){
					productStoreDO.setShoeNum(productStoreDO.getShoeNum() + productStoreVO.getShoeNum());
					break;
				}
			}
		}
	}

	@Override
	public Integer getInputNum(ProductStoreOperateListReq request) {
		return productStoreOperateMysqlDAO.getInputNum(request);
	}

	@Override
	public Integer getOutputNum(ProductStoreOperateListReq request) {
		return productStoreOperateMysqlDAO.getOutputNum(request);
	}
}
