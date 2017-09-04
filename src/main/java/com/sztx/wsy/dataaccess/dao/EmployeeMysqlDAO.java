package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.dataaccess.domain.EmployeeDO;

public interface EmployeeMysqlDAO {
	
	void add(EmployeeDO employeeDO);
	 
    void delete(@Param("id") Integer employeeId);
    
    void update(EmployeeDO employeeDO);
    
    EmployeeDO findById(@Param("id") Integer employeeId);
    
    EmployeeDO findByNo(@Param("employeeNo") String employeeNo);
    
    Integer totalCount(@Param("employeeDO") EmployeeDO employeeDO);
    
    List<EmployeeDO> findByPage(@Param("employeeDO") EmployeeDO employeeDO,
    		@Param("pageQuery") PageQuery pageQuery);
}