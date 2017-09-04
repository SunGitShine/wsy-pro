package com.sztx.wsy.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.common.domain.Result;
import com.sztx.wsy.common.domain.ResultCode;
import com.sztx.wsy.core.service.DepartmentService;
import com.sztx.wsy.dataaccess.domain.DepartmentDO;
import com.sztx.wsy.util.ParameterUtil;

@Controller
@RequestMapping(value = "/department")
public class DepartmentController extends BaseController{

	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(value = "/findById")
	public Result findById(HttpServletRequest request){
		
		Integer departmentId = ParameterUtil.getInteger("departmentId");
		DepartmentDO departmentDO = departmentService.findById(departmentId);
		Result result = new Result(ResultCode.COMMON_SUCCESS, true);
		result.setProperty("departmentDO", departmentDO);
		return result;
	}
	
	@RequestMapping(value = "/add")
	public Result add(HttpServletRequest request){
		
		DepartmentDO departmentDO = ParameterUtil.parseObject(DepartmentDO.class);
		departmentService.add(departmentDO);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/update")
	public Result update(){
		
		DepartmentDO departmentDO = ParameterUtil.parseObject(DepartmentDO.class);
		departmentService.update(departmentDO);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/delete")
	public Result delete(){
		
		Integer departmentId = ParameterUtil.getInteger("departmentId");
		departmentService.delete(departmentId);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/findByPage")
	public Result findByPage(){
		
		PageQuery pageQuery = getPageQuery();
		List<DepartmentDO> departments = departmentService.findByPage(pageQuery);
		Integer totalCount = departmentService.totalCount();
		return ParameterUtil.pageSuccessResult(totalCount, departments);
	}
}
