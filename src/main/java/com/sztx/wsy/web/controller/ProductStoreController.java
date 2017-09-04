package com.sztx.wsy.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.common.domain.Result;
import com.sztx.wsy.core.service.ProductService;
import com.sztx.wsy.core.service.ProductStoreService;
import com.sztx.wsy.core.service.domain.request.ProductStoreOperateListReq;
import com.sztx.wsy.core.service.domain.request.ProductStoreOperateListRsp;
import com.sztx.wsy.core.service.domain.request.ProductStoreOperateRequest;
import com.sztx.wsy.core.service.domain.request.ProductStoreVO;
import com.sztx.wsy.dataaccess.domain.ProductDO;
import com.sztx.wsy.dataaccess.domain.ProductStoreDO;
import com.sztx.wsy.util.LoginUtil;
import com.sztx.wsy.util.ParameterUtil;

@Controller
@RequestMapping(value = "/store")
public class ProductStoreController extends BaseController{

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
	
	@RequestMapping(value = "/inputOrOutput")
	public Result inputOrOutput(HttpServletRequest request){
		
		String data = request.getParameter("d");
		JSONObject json = JSONObject.parseObject(data);
		ProductStoreOperateRequest storeOperateRequset = ParameterUtil.parseObject(data, ProductStoreOperateRequest.class);
		List<ProductStoreVO> productStoreVOs = JSONObject.parseArray(json.getString("productStoreVOs"), ProductStoreVO.class);
		storeOperateRequset.setProductStoreVOs(productStoreVOs);
		storeOperateRequset.setCreateUser(LoginUtil.currentRealname());
		
		productStoreService.productOutputOrInput(storeOperateRequset);
		
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/inputOrOutputList")
	public Result inputOrOutputList(){
		
		ProductStoreOperateListReq request = ParameterUtil.parseObject(ProductStoreOperateListReq.class);
		PageQuery pageQuery = getPageQuery();
		
		Integer totalCount = productStoreService.totalCount(request);
		List<ProductStoreOperateListRsp> productStoreOperateDOs = productStoreService.productOutputOrInputList(request, pageQuery);
		return ParameterUtil.pageSuccessResult(totalCount, productStoreOperateDOs);
	}
	
	@RequestMapping(value = "/updateStoreSet")
	public Result updateStoreSet(HttpServletRequest request){
		
		String data = request.getParameter("d");
		JSONObject json = JSONObject.parseObject(data);
		Integer productId = json.getInteger("productId");
		List<ProductStoreDO> productStoreDOs = JSONObject.parseArray(json.getString("productStoreDOs"), ProductStoreDO.class);
		productStoreService.updateSotreSet(productStoreDOs, productId);
		return ParameterUtil.commonSuccessResult();
	}
}
