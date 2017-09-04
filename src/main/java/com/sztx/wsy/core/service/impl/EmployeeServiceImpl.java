package com.sztx.wsy.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.sztx.wsy.common.domain.BusinessException;
import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.EmployeeService;
import com.sztx.wsy.dataaccess.dao.EmployeeMysqlDAO;
import com.sztx.wsy.dataaccess.domain.EmployeeDO;
import com.sztx.wsy.util.StringUtil;
import com.sztx.wsy.util.ValidateUtil;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired 
	private EmployeeMysqlDAO employeeMysqlDAO;

	@Override
	public void add(EmployeeDO employeeDO) {
		
		chackParam(employeeDO);
		EmployeeDO existEmployee = employeeMysqlDAO.findByNo(employeeDO.getEmployeeNo());
		if(existEmployee != null){
			throw new BusinessException("该工号已经存在");
		}
		employeeMysqlDAO.add(employeeDO);
	}

	@Override
	public void delete(Integer employeeId) {
		
		ValidateUtil.notNull(employeeId, "成员id不能为空");
		employeeMysqlDAO.delete(employeeId);
	}

	@Override
	public void update(EmployeeDO employeeDO) {
		chackParam(employeeDO);
		ValidateUtil.notNull(employeeDO.getId(), "成员id不能为空");
		try {
			employeeMysqlDAO.update(employeeDO);
		} catch (DuplicateKeyException e) {
			throw new BusinessException("该工号已经存在");
		}
	}

	@Override
	public EmployeeDO findById(Integer employeeId) {
		ValidateUtil.notNull(employeeId, "成员id不能为空");
		return employeeMysqlDAO.findById(employeeId);
	}

	@Override
	public Integer totalCount(EmployeeDO employeeDO) {
		return employeeMysqlDAO.totalCount(employeeDO);
	}

	@Override
	public List<EmployeeDO> findByPage(EmployeeDO employeeDO,
			PageQuery pageQuery) {
		return employeeMysqlDAO.findByPage(employeeDO, pageQuery);
	}
	
	public void chackParam(EmployeeDO employeeDO){
		if(employeeDO.getDepartmentId() == null || StringUtil.isEmpty(employeeDO.getDepartmentName()) 
				|| StringUtil.isEmpty(employeeDO.getEmployeeNo()) || StringUtil.isEmpty(employeeDO.getMobile())
				|| StringUtil.isEmpty(employeeDO.getName()) || StringUtil.isEmpty(employeeDO.getEntryTime().toString())){
			throw new BusinessException("参数异常");
		}
	}

}
