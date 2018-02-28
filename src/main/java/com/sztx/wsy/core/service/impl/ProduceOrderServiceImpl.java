package com.sztx.wsy.core.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sztx.wsy.common.domain.BusinessException;
import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.ProduceOrderService;
import com.sztx.wsy.core.service.domain.request.OrderStatisticReq;
import com.sztx.wsy.core.service.domain.request.ProduceOrderListReq;
import com.sztx.wsy.core.service.domain.request.ProduceOrderProductDetailVO;
import com.sztx.wsy.core.service.domain.request.ProduceOrderProductVO;
import com.sztx.wsy.core.service.domain.request.ProduceOrderVO;
import com.sztx.wsy.dataaccess.dao.AccountMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ProduceOrderMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ProduceOrderProductDetailMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ProduceOrderProductDistributeMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ProduceOrderProductMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ReceiptMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ReceiptProductMysqlDAO;
import com.sztx.wsy.dataaccess.domain.AccountDO;
import com.sztx.wsy.dataaccess.domain.ProduceOrderDO;
import com.sztx.wsy.dataaccess.domain.ProduceOrderProductDO;
import com.sztx.wsy.dataaccess.domain.ProduceOrderProductDetailDO;
import com.sztx.wsy.dataaccess.domain.ProduceOrderProductDistributeDO;
import com.sztx.wsy.dataaccess.domain.ReceiptDO;
import com.sztx.wsy.dataaccess.domain.ReceiptProductDO;
import com.sztx.wsy.util.LoginUtil;
import com.sztx.wsy.util.MsgUtil;
import com.sztx.wsy.util.StringUtil;
import com.sztx.wsy.util.ValidateUtil;

@Service("produceOrderService")
public class ProduceOrderServiceImpl implements ProduceOrderService{
	
	@Autowired
	private ProduceOrderMysqlDAO produceOrderMysqlDAO;
	
	@Autowired
	private ProduceOrderProductMysqlDAO produceOrderProductMysqlDAO;
	
	@Autowired
	private ProduceOrderProductDetailMysqlDAO produceOrderProductDetailMysqlDAO;
	
	@Autowired
	private ProduceOrderProductDistributeMysqlDAO produceOrderProductDistributeMysqlDAO;
	
	@Autowired
	private AccountMysqlDAO accountMysqlDAO;
	
	@Autowired
	private ReceiptMysqlDAO receiptMysqlDAO;
	
	@Autowired
	private ReceiptProductMysqlDAO receiptProductMysqlDAO;
	
	private static final Logger log = LoggerFactory.getLogger(ProduceOrderServiceImpl.class);

	@Override
	public void add(ProduceOrderVO produceOrderVO, Integer smsIsOpen, Integer isCreatDeliveryNote) {
		
		Integer orderNum = chackParamAndOrderNum(produceOrderVO);
		log.info("订单总双数：" + orderNum);
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String orderNo = sf.format(new Date());
		
		produceOrderVO.setOrderNo(orderNo);
		produceOrderVO.setOrderNum(orderNum);
		
		ProduceOrderDO produceOrderDO = new ProduceOrderDO();
		BeanUtils.copyProperties(produceOrderVO, produceOrderDO);
		produceOrderMysqlDAO.add(produceOrderDO);//添加订单生产表
		
		Long orderAmount = saveOrderProductAndDetail(produceOrderVO, orderNo);
		
		ProduceOrderDO updatePsroduceOrderDO = new ProduceOrderDO();
		updatePsroduceOrderDO.setOrderNo(orderNo);
		updatePsroduceOrderDO.setOrderAmount(orderAmount);
		updatePsroduceOrderDO.setUpdateUser(produceOrderVO.getUpdateUser());
		produceOrderMysqlDAO.update(updatePsroduceOrderDO);
		
		//创建送货单
		if(isCreatDeliveryNote != null && isCreatDeliveryNote == 1){
			
			ReceiptDO receiptDO = new ReceiptDO();
			receiptDO.setOrderNo(orderNo);
			receiptDO.setOrderName(produceOrderVO.getOrderName());
			receiptDO.setCustomerName(produceOrderVO.getCustomerName());
			receiptDO.setCustomerPhone(produceOrderVO.getCustomerPhone());
			receiptDO.setDeliveryAddress(produceOrderVO.getDeliveryAddress());
			receiptDO.setTotalNum(orderNum);
			receiptDO.setTotalMoney(Integer.parseInt(orderAmount.toString()));
			receiptDO.setBalanceStatus(0);
			
			receiptMysqlDAO.add(receiptDO);//票据表插入数据
			creatDeliveryNote(produceOrderVO, orderNo);//票据商品表插入数据
		}
		
		if(smsIsOpen != null && smsIsOpen == 1){
			//发送短信通知上案管理员
			List<AccountDO> accountDOs = accountMysqlDAO.findByType(2);
			String tplValue = "#orderNo#=" + orderNo + "&#orderName#=" + produceOrderVO.getOrderName();
			for(AccountDO accountDO : accountDOs){
				MsgUtil.sendSMS("32120", tplValue, accountDO.getMobile());
			}
		}
	}
	
	@Override
	public List<ProduceOrderVO> findOrderList(ProduceOrderListReq req, PageQuery pageQuery) {
		
		List<ProduceOrderDO> produceOrderDOs = produceOrderMysqlDAO.findOrderList(req, pageQuery);
		
		List<ProduceOrderVO> produceOrderVOs = new ArrayList<ProduceOrderVO>();
		for(ProduceOrderDO produceOrderDO : produceOrderDOs){
			
			//剩余天数
			ProduceOrderVO produceOrderVO = new ProduceOrderVO();
			BeanUtils.copyProperties(produceOrderDO, produceOrderVO);
			float residueTime = getTimeDecimals(produceOrderDO.getDeliveryTime()) < 0 ? 0 : getTimeDecimals(produceOrderDO.getDeliveryTime());
			log.info("剩余时间：" + residueTime + "天");
			produceOrderVO.setResidueTime(residueTime);
			if(LoginUtil.currentUser() != null && LoginUtil.currentUser().getType() != 1){//超级管理员除外的用户不能看到订单金额
				produceOrderVO.setOrderAmount(null);
			}
			
			//产品图片
			List<ProduceOrderProductDO> produceOrderProductDOs = produceOrderProductMysqlDAO.findByOrderNo(produceOrderDO.getOrderNo());
			for(ProduceOrderProductDO produceOrderProductDO : produceOrderProductDOs){
				if(StringUtil.isNotBlank(produceOrderProductDO.getProductUrl())){
					produceOrderVO.setOrderPic(produceOrderProductDO.getProductUrl());
					break;
				}
			}
			produceOrderVOs.add(produceOrderVO);
		}
		Collections.sort(produceOrderVOs);
		return produceOrderVOs;
	}
	
	@Override
	public Integer orderTotalCount(ProduceOrderListReq req) {
		return produceOrderMysqlDAO.orderTotalCount(req);
	}
	
	@Override
	public void orderDistribute(List<ProduceOrderProductDistributeDO> produceOrderProductDistributeDOs, Integer type, 
			String orderNo, String updateUser) {
		
		for(ProduceOrderProductDistributeDO produceOrderProductDistributeDO : produceOrderProductDistributeDOs){
			produceOrderProductDistributeDO.setType(type);
			produceOrderProductDistributeDO.setProduceOrderNo(orderNo);
		}
		produceOrderProductDistributeMysqlDAO.batchAdd(produceOrderProductDistributeDOs);
		
		ProduceOrderDO produceOrderDO = new ProduceOrderDO();
		produceOrderDO.setUpdateUser(updateUser);
		produceOrderDO.setOrderNo(orderNo);
		if(type.equals(1)){//上案分配
			produceOrderDO.setVampHandleTime(new Date());
			produceOrderDO.setVampStatus(1);
			produceOrderDO.setVampHandleUser(updateUser);
		}else if(type.equals(2)){//下案分配
			produceOrderDO.setSoleHandleTime(new Date());
			produceOrderDO.setSoleStatus(1);
			produceOrderDO.setSoleHandleUser(updateUser);
		}
		
		produceOrderMysqlDAO.update(produceOrderDO);
	}
	
	@Override
	public ProduceOrderVO findByOrderNo(String orderNo, Integer type) {
		
		ValidateUtil.isNotBlank(orderNo, "生产订单编号不能为空");
		ProduceOrderVO produceOrderVO = new ProduceOrderVO();
		ProduceOrderDO produceOrderDO = produceOrderMysqlDAO.findByOrderNo(orderNo);
		if(produceOrderDO != null){
			BeanUtils.copyProperties(produceOrderDO, produceOrderVO);
		}
		
		List<ProduceOrderProductVO> produceOrderProductVOs = new ArrayList<ProduceOrderProductVO>();
		List<ProduceOrderProductDO> produceOrderProductDOs = produceOrderProductMysqlDAO.findByOrderNo(orderNo);
		//组装订单产品列表
		for(ProduceOrderProductDO produceOrderProductDO : produceOrderProductDOs){
			
			ProduceOrderProductVO produceOrderProductVO = new ProduceOrderProductVO();
			BeanUtils.copyProperties(produceOrderProductDO, produceOrderProductVO);
			if(LoginUtil.currentUser() != null && LoginUtil.currentUser().getType() != 1){
				produceOrderProductVO.setProduceAmount(null);
				produceOrderProductVO.setProducePrice(null);
			}
			produceOrderProductVOs.add(produceOrderProductVO);
		}
		//组装订单产品详情列表
		for(ProduceOrderProductVO produceOrderProductVO : produceOrderProductVOs){
			List<ProduceOrderProductDetailVO> produceOrderProductDetailVOs = new ArrayList<ProduceOrderProductDetailVO>();
			List<ProduceOrderProductDetailDO> produceOrderProductDetailDOs = produceOrderProductDetailMysqlDAO.findByProductId(produceOrderProductVO.getId());
			
			for(ProduceOrderProductDetailDO produceOrderProductDetailDO : produceOrderProductDetailDOs){
				
				ProduceOrderProductDetailVO produceOrderProductDetailVO = new ProduceOrderProductDetailVO();
				BeanUtils.copyProperties(produceOrderProductDetailDO, produceOrderProductDetailVO);
				produceOrderProductDetailVOs.add(produceOrderProductDetailVO);
			}
			
			//组装订单产品分配列表
			for(ProduceOrderProductDetailVO produceOrderProductDetailVO : produceOrderProductDetailVOs){
				
				Integer produceOrderProductDetailId = produceOrderProductDetailVO.getId();
				List<ProduceOrderProductDistributeDO> produceOrderProductDistributeDOs = produceOrderProductDistributeMysqlDAO.findByDetailId(produceOrderProductDetailId,type);
				produceOrderProductDetailVO.setProduceOrderProductDistributeDOs(produceOrderProductDistributeDOs);
			}
			
			produceOrderProductVO.setProduceOrderProductDetailVOs(produceOrderProductDetailVOs);
		}
		
		produceOrderVO.setProduceOrderProductVOs(produceOrderProductVOs);
		return produceOrderVO;
	}
	
	@Override
	public void delete(String orderNo) {
		
		ValidateUtil.isNotBlank(orderNo, "生产订单编号不能为空");
		ProduceOrderDO produceOrderDO = produceOrderMysqlDAO.findByOrderNo(orderNo);
		if(produceOrderDO.getTailorStatus() != 0 || produceOrderDO.getVampStatus() != 0
				|| produceOrderDO.getSoleStatus() != 0 || produceOrderDO.getQcStatus() != 0){
			throw new BusinessException("该订单已进入生产流程，不支持删除");
		}
		produceOrderMysqlDAO.delete(orderNo);//删除订单表
		produceOrderProductMysqlDAO.delete(orderNo);//删除订单产品表
		produceOrderProductDetailMysqlDAO.delete(orderNo);//删除订单产品详情表
		receiptMysqlDAO.deleteByOrderNo(orderNo);//删除票据表
		receiptProductMysqlDAO.deleteByOrderNo(orderNo);//删除票据商品表
	}
	
	@Override
	public void update(ProduceOrderVO produceOrderVO, Integer isCreatDeliveryNote) {
		
		String orderNo = produceOrderVO.getOrderNo();
		ValidateUtil.isNotBlank(orderNo, "生产订单编号不能为空");
		ProduceOrderDO produceOrderDO = produceOrderMysqlDAO.findByOrderNo(orderNo);
		//未进入生产流程
		if(produceOrderDO.getTailorStatus() == 0 && produceOrderDO.getVampStatus() == 0
				&& produceOrderDO.getSoleStatus() == 0 && produceOrderDO.getQcStatus() == 0){
			produceOrderMysqlDAO.delete(orderNo);//删除订单表
			produceOrderProductMysqlDAO.delete(orderNo);//删除订单产品表
			produceOrderProductDetailMysqlDAO.delete(orderNo);//删除订单产品详情表
			receiptMysqlDAO.deleteByOrderNo(orderNo);//删除票据表
			receiptProductMysqlDAO.deleteByOrderNo(orderNo);//删除票据商品表
			add(produceOrderVO,null,isCreatDeliveryNote);
		}else{//已进入生产流程，只更新订单表，订单产品和订单产品详情表不更新
			ProduceOrderDO updateProduceOrderDO = new ProduceOrderDO();
			BeanUtils.copyProperties(produceOrderVO, updateProduceOrderDO);
			produceOrderMysqlDAO.update(updateProduceOrderDO);
		}
	}
	
	@Override
	public void updateStatus(ProduceOrderDO produceOrderDO, Integer smsIsOpen) {
		
		if(produceOrderDO.getQcStatus() != null && produceOrderDO.getQcStatus() == 1){
			ProduceOrderDO produceOrderDO2 = produceOrderMysqlDAO.findByOrderNo(produceOrderDO.getOrderNo());
			if(produceOrderDO2.getTailorStatus() == 0){
				throw new BusinessException("裁剪未完成，不能操作质检");
			}
			if(produceOrderDO2.getVampStatus() == 0 || produceOrderDO2.getVampStatus() == 1){
				throw new BusinessException("上案未完成，不能操作质检");
			}
			if(produceOrderDO2.getSoleStatus() == 0 || produceOrderDO2.getSoleStatus() == 1){
				throw new BusinessException("下案未完成，不能操作质检");
			}
		}
		
		produceOrderMysqlDAO.update(produceOrderDO);
		
		ProduceOrderDO produceOrder = produceOrderMysqlDAO.findByOrderNo(produceOrderDO.getOrderNo());
		
		if(smsIsOpen != null && smsIsOpen == 1){
			if(produceOrderDO.getVampStatus() != null && produceOrderDO.getVampStatus() == 2){
				//发送短信通知下案管理员
				List<AccountDO> accountDOs = accountMysqlDAO.findByType(3);
				try {
					String tplValue = "#orderNo#=" + produceOrder.getOrderNo() + "&#orderName#=" + produceOrder.getOrderName();
					for(AccountDO accountDO : accountDOs){
						MsgUtil.sendSMS("32120", tplValue, accountDO.getMobile());
					}
				} catch (Exception e) {
					throw new BusinessException("短信发送异常");
				}
			}
			
			if(produceOrderDO.getQcStatus() != null && produceOrderDO.getQcStatus() == 1){
				
				//发送短信通知管理员
				float useTime = useTime(produceOrder.getCreateTime());
				List<AccountDO> accountDOs = accountMysqlDAO.findByType(1);
				try {
					String tplValue = "#orderNo#=" + produceOrder.getOrderNo() + "&#orderName#=" 
							+ produceOrder.getOrderName() + "&#useTime#=" + useTime;
					for(AccountDO accountDO : accountDOs){
						MsgUtil.sendSMS("32122", tplValue, accountDO.getMobile());
					}
				} catch (Exception e) {
					throw new BusinessException("短信发送异常");
				}
				
			}
		}
	}
	
	@Override
	public void updateDistribute(
			List<ProduceOrderProductDistributeDO> produceOrderProductDistributeDOs,
			Integer type, String orderNo, String operator) {
		
		produceOrderProductDistributeMysqlDAO.delete(orderNo, type);//删除分配表
		orderDistribute(produceOrderProductDistributeDOs, type, orderNo, operator);
	}
	
	@Override
	public List<ProduceOrderDO> orderStatisticList(OrderStatisticReq orderStatisticReq, PageQuery pageQuery) {
		
		String vampEmployeeNo = orderStatisticReq.getVampEmployeeNo();
		String soleEmployeeNo = orderStatisticReq.getSoleEmployeeNo();
		
		if(StringUtil.isBlank(vampEmployeeNo) && StringUtil.isBlank(soleEmployeeNo)){
			orderStatisticReq.setSqlType(1);
		}else if(StringUtil.isNotBlank(vampEmployeeNo) && StringUtil.isBlank(soleEmployeeNo)){
			orderStatisticReq.setSqlType(2);
		}else if(StringUtil.isBlank(vampEmployeeNo) && StringUtil.isNotBlank(soleEmployeeNo)){
			orderStatisticReq.setSqlType(3);
		}else if(StringUtil.isBlank(vampEmployeeNo) && StringUtil.isBlank(soleEmployeeNo)){
			orderStatisticReq.setSqlType(4);
		}
		return produceOrderMysqlDAO.orderStatisticList(orderStatisticReq, pageQuery);
	}

	@Override
	public Integer orderStatisticCount(OrderStatisticReq orderStatisticReq) {
		
		String vampEmployeeNo = orderStatisticReq.getVampEmployeeNo();
		String soleEmployeeNo = orderStatisticReq.getSoleEmployeeNo();
		
		if(StringUtil.isBlank(vampEmployeeNo) && StringUtil.isBlank(soleEmployeeNo)){
			orderStatisticReq.setSqlType(1);
		}else if(StringUtil.isNotBlank(vampEmployeeNo) && StringUtil.isBlank(soleEmployeeNo)){
			orderStatisticReq.setSqlType(2);
		}else if(StringUtil.isBlank(vampEmployeeNo) && StringUtil.isNotBlank(soleEmployeeNo)){
			orderStatisticReq.setSqlType(3);
		}else if(StringUtil.isBlank(vampEmployeeNo) && StringUtil.isBlank(soleEmployeeNo)){
			orderStatisticReq.setSqlType(4);
		}
		return produceOrderMysqlDAO.orderStatisticCount(orderStatisticReq);
	}

	public Integer chackParamAndOrderNum(ProduceOrderVO produceOrderVO){
		
		Integer orderNum = 0;//订货总双数
		
		if(StringUtil.isEmpty(produceOrderVO.getOrderName()) || produceOrderVO.getIsUrgent() == null || produceOrderVO.getDeliveryTime() == null){
			throw new BusinessException("参数异常");
		}
		
		List<ProduceOrderProductVO> produceOrderProductVOs = produceOrderVO.getProduceOrderProductVOs();
		if(produceOrderProductVOs == null || produceOrderProductVOs.size() == 0){
			throw new BusinessException("订单产品不能为空");
		}
		for(ProduceOrderProductVO produceOrderProduct : produceOrderProductVOs){
			
			ValidateUtil.isNotBlank(produceOrderProduct.getProductName(), "产品名称不能为空");
			
			List<ProduceOrderProductDetailDO> produceOrderProductDetailDOs = produceOrderProduct.getProduceOrderProductDetailDOs();
			if(produceOrderProductDetailDOs == null || produceOrderProductDetailDOs.size() == 0){
				throw new BusinessException(produceOrderProduct.getProductName() + "的产品鞋码和双数不能为空");
			}
			for(ProduceOrderProductDetailDO produceOrderProductDetail : produceOrderProductDetailDOs){
				ValidateUtil.notNull(produceOrderProductDetail, produceOrderProduct.getProductName() + "的产品鞋码和双数不能为空");
				ValidateUtil.notNull(produceOrderProductDetail.getShoeCode(), produceOrderProduct.getProductName() + "的鞋码不能为空");
				ValidateUtil.notNull(produceOrderProductDetail.getShoeNum(), produceOrderProduct.getProductName() + "的鞋双数不能为空");
				orderNum = orderNum + produceOrderProductDetail.getShoeNum();
			}
		}
		return orderNum;
	}
	
	/**
	 * 存储订单产品信息及订单产品鞋码、数量信息
	 * @param produceOrderVO
	 * @param orderNo
	 * @return
	 */
	public Long saveOrderProductAndDetail(ProduceOrderVO produceOrderVO, String orderNo){
		
		Long orderAmount = 0L;//订单总金额
		
		List<ProduceOrderProductVO> produceOrderProductVOs = produceOrderVO.getProduceOrderProductVOs();
		for(ProduceOrderProductVO produceOrderProductVO : produceOrderProductVOs){
			
			Integer shoeNum = 0;//产品鞋双数
			ProduceOrderProductDO produceOrderProductDO = new ProduceOrderProductDO();
			BeanUtils.copyProperties(produceOrderProductVO, produceOrderProductDO);
			produceOrderProductDO.setOrderNo(orderNo);
			
			List<ProduceOrderProductDetailDO> produceOrderProductDetailDOs = produceOrderProductVO.getProduceOrderProductDetailDOs();
			for(ProduceOrderProductDetailDO produceOrderProductDetail : produceOrderProductDetailDOs){
				shoeNum = shoeNum + produceOrderProductDetail.getShoeNum();
			}
			
			Long produceAmount = (long) (shoeNum*produceOrderProductDO.getProducePrice());
			orderAmount = orderAmount + produceAmount; 
			log.info(produceOrderProductVO.getProductName() + "的单价：" + produceOrderProductDO.getProducePrice());
			log.info(produceOrderProductVO.getProductName() + "的双数：" + shoeNum);
			log.info(produceOrderProductVO.getProductName() + "的金额：" + produceAmount);
			
			produceOrderProductDO.setProduceNum(shoeNum);
			produceOrderProductDO.setProduceAmount(produceAmount);
			produceOrderProductMysqlDAO.add(produceOrderProductDO);
			
			for(ProduceOrderProductDetailDO produceOrderProductDetail : produceOrderProductDetailDOs){
				produceOrderProductDetail.setOrderNo(orderNo);
				produceOrderProductDetail.setProduceOrderProductId(produceOrderProductDO.getId());
				shoeNum = shoeNum + produceOrderProductDetail.getShoeNum();
			}
			produceOrderProductDetailMysqlDAO.batchAdd(produceOrderProductDetailDOs);
		}
		
		return orderAmount;
	}
	
	public void creatDeliveryNote(ProduceOrderVO produceOrderVO, String orderNo) {
		
		Long orderAmount = 0L;//订单总金额
		
		List<ReceiptProductDO> receiptProducts = new ArrayList<ReceiptProductDO>();
		
		List<ProduceOrderProductVO> produceOrderProductVOs = produceOrderVO.getProduceOrderProductVOs();
		for(ProduceOrderProductVO produceOrderProductVO : produceOrderProductVOs){
			
			ReceiptProductDO receiptProductDO = new ReceiptProductDO();
			
			Integer shoeNum = 0;//产品鞋双数
			ProduceOrderProductDO produceOrderProductDO = new ProduceOrderProductDO();
			BeanUtils.copyProperties(produceOrderProductVO, produceOrderProductDO);
			
			List<ProduceOrderProductDetailDO> produceOrderProductDetailDOs = produceOrderProductVO.getProduceOrderProductDetailDOs();
			for(ProduceOrderProductDetailDO produceOrderProductDetail : produceOrderProductDetailDOs){
				shoeNum = shoeNum + produceOrderProductDetail.getShoeNum();
			}
			
			Integer produceAmount = shoeNum*produceOrderProductDO.getProducePrice();
			orderAmount = orderAmount + produceAmount; 
			log.info(produceOrderProductVO.getProductName() + "的单价：" + produceOrderProductDO.getProducePrice());
			log.info(produceOrderProductVO.getProductName() + "的双数：" + shoeNum);
			log.info(produceOrderProductVO.getProductName() + "的金额：" + produceAmount);
			
			receiptProductDO.setOrderNo(orderNo);
			receiptProductDO.setProductName(produceOrderProductVO.getProductName());
			receiptProductDO.setProductPrice(produceOrderProductDO.getProducePrice());
			receiptProductDO.setProductum(shoeNum);
			receiptProductDO.setTotalMoney(produceAmount);
			receiptProducts.add(receiptProductDO);
		}
		receiptProductMysqlDAO.batchAdd(receiptProducts);
	}
	
	/**
	 * 
	 * @return
	 */
	public float getTimeDecimals(Date deliveryDate){
		
		long deliveryTime = deliveryDate.getTime();
		float residueDay = (float)(deliveryTime - new Date().getTime())/(1000 * 60 * 60 * 24);
		BigDecimal bd = new BigDecimal(residueDay);
		return bd.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public float useTime(Date creatDate){
			
		long deliveryTime = creatDate.getTime();
		float residueDay = (float)(new Date().getTime() - deliveryTime)/(1000 * 60 * 60 * 24);
		BigDecimal bd = new BigDecimal(residueDay);
		return bd.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public static void main(String[] args) {
//		String orderNo = "20170222113636330";
//		String orderName = "测试订单";
//		String mobile = "18380448932";
//		MsgUtil.sendSMS("32120", "#orderNo#=" + orderNo + "&#orderName#=" + orderName, mobile);
		Integer num = null;
		if(num == 2){}
	}

}
