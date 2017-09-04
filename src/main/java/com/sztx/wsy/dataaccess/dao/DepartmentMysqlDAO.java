package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.dataaccess.domain.DepartmentDO;


public interface DepartmentMysqlDAO{
    
	DepartmentDO findById(Integer id);
	
	void delete(Integer id);
	
	void save(DepartmentDO departmentDO);
	
	void update(DepartmentDO departmentDO);
	
	Integer listCount();
	
	List<DepartmentDO> listPage(@Param("pageQuery") PageQuery pageQuery);
}