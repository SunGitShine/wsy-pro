package com.sztx.wsy.core.service;

import java.util.List;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.dataaccess.domain.DepartmentDO;

public interface DepartmentService {

	DepartmentDO findById(Integer departmentId);
	
	void add(DepartmentDO departmentDO);
	
	void update(DepartmentDO departmentDO);
	
	void delete(Integer departmentId);
	
	List<DepartmentDO> findByPage(PageQuery pageQuery);
	
	Integer totalCount();
}
