package com.sztx.wsy.core.service;

import java.util.List;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.dataaccess.domain.EmployeeDO;

public interface EmployeeService {

	void add(EmployeeDO employeeDO);
	
	void delete(Integer employeeId);
	
	void update(EmployeeDO employeeDO);
	
	EmployeeDO findById(Integer employeeId);
	
	Integer totalCount(EmployeeDO employeeDO);
	
	List<EmployeeDO> findByPage(EmployeeDO employeeDO, PageQuery pageQuery);
}
