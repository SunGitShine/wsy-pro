package com.sztx.wsy.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.common.domain.Result;
import com.sztx.wsy.core.service.ProductService;
import com.sztx.wsy.dataaccess.domain.ProductDO;
import com.sztx.wsy.util.LoginUtil;
import com.sztx.wsy.util.ParameterUtil;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends BaseController{

	@Autowired
	private ProductService productService;
	
	private static final Logger log = LoggerFactory.getLogger(ProductDO.class);
	
	@RequestMapping(value = "/upload")
	public Result upload(@RequestParam("upload") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	    String filePath = productService.uploadFile(file, request);  
	    log.info("filePath:" + filePath);
	    return ParameterUtil.commonSuccessResult("picPath", filePath);
	}
	
	@RequestMapping(value = "/add")
	public Result add(){
		
		ProductDO productDO = ParameterUtil.parseObject(ProductDO.class);
		productDO.setCreateUser(LoginUtil.currentRealname());
		productDO.setUpdateUser(LoginUtil.currentRealname());
		productService.add(productDO);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/update")
	public Result update(){
		
		ProductDO productDO = ParameterUtil.parseObject(ProductDO.class);
		productDO.setUpdateUser(LoginUtil.currentRealname());
		productService.update(productDO);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/findById")
	public Result findById(){
		
		Integer productId = ParameterUtil.getInteger("productId");
		ProductDO productDO = productService.findById(productId);
		return ParameterUtil.commonSuccessResult("productDO", productDO);
	}
	
	@RequestMapping(value = "/findByPage")
	public Result findByPage(){
		
		ProductDO productDO = ParameterUtil.parseObject(ProductDO.class);
		PageQuery pageQuery = getPageQuery();
		
		Integer totalCount = productService.totalCount(productDO);
		List<ProductDO> productDOs = productService.findByPage(productDO, pageQuery);
		
		return ParameterUtil.pageSuccessResult(totalCount, productDOs);
	}
	
	@RequestMapping(value = "/delete")
	public Result delete(){
		
		Integer productId = ParameterUtil.getInteger("productId");
		productService.delete(productId);
		
		return ParameterUtil.commonSuccessResult();
	}
}
