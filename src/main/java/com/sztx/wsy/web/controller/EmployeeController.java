package com.sztx.wsy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.common.domain.Result;
import com.sztx.wsy.core.service.EmployeeService;
import com.sztx.wsy.dataaccess.domain.EmployeeDO;
import com.sztx.wsy.util.DateUtil;
import com.sztx.wsy.util.ParameterUtil;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController extends BaseController{
	
	@Autowired 
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/add")
	public Result add(){
		
		EmployeeDO employeeDO = ParameterUtil.parseObject(EmployeeDO.class);
		employeeService.add(employeeDO);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/delete")
	public Result delete(){
		
		Integer employeeId = ParameterUtil.getInteger("employeeId");
		employeeService.delete(employeeId);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/update")
	public Result update(){
		
		EmployeeDO employeeDO = ParameterUtil.parseObject(EmployeeDO.class);
		employeeService.update(employeeDO);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/findById")
	public Result findById(){
		
		Integer employeeId = ParameterUtil.getInteger("employeeId");
		EmployeeDO employeeDO = employeeService.findById(employeeId);
		Result result = ParameterUtil.commonSuccessResult("employeeDO", employeeDO);
		result.setDateFormat(DateUtil.SHORT_DATE_FORMAT_STR);
		return result;
	}
	
	@RequestMapping(value = "/findByPage")
	public Result findByPage(){
		
		PageQuery pageQuery = getPageQuery();
		EmployeeDO employeeDO = ParameterUtil.parseObject(EmployeeDO.class);
		Integer totalCount = employeeService.totalCount(employeeDO);
		List<EmployeeDO> employeeDOs = employeeService.findByPage(employeeDO, pageQuery);
		Result result = ParameterUtil.pageSuccessResult(totalCount, employeeDOs);
		result.setDateFormat(DateUtil.SHORT_DATE_FORMAT_STR);
		return result;
	}
	
}
