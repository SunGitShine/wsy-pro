package com.sztx.wsy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.common.domain.Result;
import com.sztx.wsy.core.service.ProductService;
import com.sztx.wsy.core.service.ProductStoreService;
import com.sztx.wsy.dataaccess.domain.ProductDO;
import com.sztx.wsy.dataaccess.domain.ProductStoreDO;
import com.sztx.wsy.util.ParameterUtil;

@Controller
@RequestMapping(value = "/mobile/store")
public class ProductMobileStoreController extends BaseController{

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductStoreService productStoreService;
	
	@RequestMapping(value = "/findStoreList")
	public Result findStoreList(){
		
		ProductDO productDO = ParameterUtil.parseObject(ProductDO.class);
		PageQuery pageQuery = getPageQuery();
		
		Integer totalCount = productService.totalCount(productDO);
		List<ProductDO> productDOs = productService.findByPage(productDO, pageQuery);
		return ParameterUtil.pageSuccessResult(totalCount, productDOs);
	}
	
	@RequestMapping(value = "/findStoreDetail")
	public Result findStoreDetail(){
		
		Integer productId = ParameterUtil.getInteger("productId");
		List<ProductStoreDO> productStoreDOs = productStoreService.findProductStoreDetail(productId);
		return ParameterUtil.commonSuccessResult("productStoreDOs", productStoreDOs);
	}
}
