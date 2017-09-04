package com.sztx.wsy.core.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sztx.wsy.common.domain.BusinessException;
import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.ProductService;
import com.sztx.wsy.dataaccess.dao.ProductMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ProductStoreMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ProductStoreSetMysqlDAO;
import com.sztx.wsy.dataaccess.domain.ProductDO;
import com.sztx.wsy.dataaccess.domain.ProductStoreDO;
import com.sztx.wsy.util.StringUtil;
import com.sztx.wsy.util.ValidateUtil;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMysqlDAO productMysqlDAO;
	
	@Autowired
	private ProductStoreMysqlDAO productStoreMysqlDAO;
	
	@Autowired
	private ProductStoreSetMysqlDAO productStoreSetMysqlDAO;
	
	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public void add(ProductDO productDO) {
		
		chackParam(productDO);
		if(productMysqlDAO.findByName(productDO.getName()) != null){
			throw new BusinessException("改商品名称已经存在");
		}
		productMysqlDAO.add(productDO);
		log.info("新增产品的id：" + productDO.getId());
		//初始化产品库存表
		List<ProductStoreDO> productStoreList = makeProductStoreList(productDO.getId(), productDO.getMinCode(), productDO.getMaxCode());
		productStoreMysqlDAO.batchAdd(productStoreList);
//		//初始化产品库存设置表
//		List<ProductStoreSetDO> productStoreSetList = makeProductStoreSetList(productDO.getId(), productDO.getMinCode(), productDO.getMaxCode());
//		productStoreSetMysqlDAO.batchAdd(productStoreSetList);
	}

	@Override
	public void update(ProductDO productDO) {
		
		ValidateUtil.notNull(productDO.getId(), "产品id不能为空");
		chackParam(productDO);
		try {
			productMysqlDAO.update(productDO);
		} catch (DuplicateKeyException e) {
			throw new BusinessException("该产品名称已经存在");
		}
	}

	@Override
	public ProductDO findById(Integer id) {
		
		ValidateUtil.notNull(id, "产品id不能为空");
		return productMysqlDAO.findById(id);
	}
	
	public void chackParam(ProductDO productDO){
		
		if(StringUtil.isEmpty(productDO.getColour()) || StringUtil.isEmpty(productDO.getUrl())
				|| StringUtil.isEmpty(productDO.getName()) || StringUtil.isEmpty(productDO.getClassifyName())
				|| productDO.getClassifyId() == null || productDO.getApplySex() == null
				|| productDO.getApplyCrowd() == null || productDO.getMinCode() == null
				|| productDO.getMaxCode() == null){
			
			throw new BusinessException("参数错误");
		}
		if(productDO.getMaxCode() < productDO.getMinCode()){
			throw new BusinessException("鞋码最大值不能小于鞋码最小值");
		}
	}

	@Override
	public Integer totalCount(ProductDO productDO) {
		
		return productMysqlDAO.totalCount(productDO);
	}

	@Override
	public List<ProductDO> findByPage(ProductDO productDO, PageQuery pageQuery) {
		
		return productMysqlDAO.findByPage(productDO, pageQuery);
	}

	@Override
	public String uploadFile(MultipartFile file, HttpServletRequest request)
			throws IOException {
		String fileName = file.getOriginalFilename();
		String newFilePath = new Date().getTime() + getFileSuffix(fileName);
        File tempFile = new File(request.getSession().getServletContext().getRealPath("../pic/wsy/"), newFilePath);  
        if (!tempFile.getParentFile().exists()) {  
            tempFile.getParentFile().mkdir();  
        }  
        if (!tempFile.exists()) {  
            tempFile.createNewFile();  
        }  
        file.transferTo(tempFile);  
        return getBaseUrl(request) + "/pic/wsy/" + newFilePath; 
	}
	
	/**
	 * 获取文件后缀
	 * @param fileName
	 * @return
	 */
	public String getFileSuffix(String fileName){
		   
		String newPicPath = fileName.substring(fileName.lastIndexOf("."));
		return newPicPath;
	}
	
	/**
	 * 获取域名
	 * @param request
	 * @return
	 */
	public String getBaseUrl(HttpServletRequest request){
		String baseUrl = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 8)); 
		return baseUrl;
	}
	
	/**
	 * 组装产品库存初始化数据
	 * @param productId
	 * @param minShoeCode
	 * @param maxShoeCode
	 * @return
	 */
	public List<ProductStoreDO> makeProductStoreList(Integer productId, Integer minShoeCode, Integer maxShoeCode){
		
		List<ProductStoreDO> productStoreDOs = new ArrayList<ProductStoreDO>();
		while(maxShoeCode - minShoeCode >= 0){
			
			ProductStoreDO productStoreDO = new ProductStoreDO();
			productStoreDO.setProductId(productId);
			productStoreDO.setShoeCode(maxShoeCode);
			productStoreDO.setShoeNum(0);
			productStoreDOs.add(productStoreDO);
			
			maxShoeCode = maxShoeCode - 1;
		}
		return productStoreDOs;
	}

	@Override
	public void delete(Integer productId) {

		ValidateUtil.notNull(productId, "商品id不能为空");
		productMysqlDAO.delete(productId);//删除商品表
		productStoreMysqlDAO.delete(productId);//删除商品库存表
	}
	
//	/**
//	 * 组装产品库存设置初始化数据
//	 * @param productId
//	 * @param minShoeCode
//	 * @param maxShoeCode
//	 * @return
//	 */
//	public List<ProductStoreSetDO> makeProductStoreSetList(Integer productId, Integer minShoeCode, Integer maxShoeCode){
//		
//		List<ProductStoreSetDO> productStoreSetDOs = new ArrayList<ProductStoreSetDO>();
//		while (maxShoeCode - minShoeCode >= 0) {
//			
//			ProductStoreSetDO productStoreSetDO = new ProductStoreSetDO();
//			productStoreSetDO.setProductId(productId);
//			productStoreSetDO.setShoeCode(maxShoeCode);
//			productStoreSetDO.setStoreMax(-1);
//			productStoreSetDO.setStoreMin(-1);
//			productStoreSetDOs.add(productStoreSetDO);
//			
//			maxShoeCode = maxShoeCode - 1;
//		}
//		return productStoreSetDOs;
//	}
}
