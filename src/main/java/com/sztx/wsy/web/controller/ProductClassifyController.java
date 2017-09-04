package com.sztx.wsy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.common.domain.Result;
import com.sztx.wsy.core.service.ProductClassifyService;
import com.sztx.wsy.dataaccess.domain.ProductClassifyDO;
import com.sztx.wsy.util.ParameterUtil;

@Controller
@RequestMapping(value = "/classify")
public class ProductClassifyController extends BaseController{

	@Autowired
	private ProductClassifyService productClassifyService;
	
	@RequestMapping("/add")
	public Result add(){
		
		ProductClassifyDO productClassifyDO = ParameterUtil.parseObject(ProductClassifyDO.class);
		productClassifyService.add(productClassifyDO);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping("/delete")
	public Result delete(){
		
		Integer classifyId = ParameterUtil.getInteger("classifyId");
		productClassifyService.delete(classifyId);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping("/update")
	public Result update(){
		
		ProductClassifyDO productClassifyDO = ParameterUtil.parseObject(ProductClassifyDO.class);
		productClassifyService.update(productClassifyDO);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping("/findById")
	public Result findById(){
		
		Integer classifyId = ParameterUtil.getInteger("classifyId");
		ProductClassifyDO productClassifyDO = productClassifyService.findById(classifyId);
		return ParameterUtil.commonSuccessResult("productClassifyDO", productClassifyDO);
	}
	
	@RequestMapping("/findByPage")
	public Result findByPage(){
		
		PageQuery pageQuery = getPageQuery();
		Integer totalCount = productClassifyService.totalCount();
		List<ProductClassifyDO> productClassifyDOs = productClassifyService.findByPage(pageQuery);
		return ParameterUtil.pageSuccessResult(totalCount, productClassifyDOs);
	}
}
