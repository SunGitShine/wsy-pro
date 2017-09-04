package com.sztx.wsy.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.sztx.wsy.common.domain.BusinessException;
import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.DepartmentService;
import com.sztx.wsy.dataaccess.dao.DepartmentMysqlDAO;
import com.sztx.wsy.dataaccess.domain.DepartmentDO;
import com.sztx.wsy.util.ValidateUtil;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentMysqlDAO departmentMysqlDAO;

	@Override
	public DepartmentDO findById(Integer departmentId) {
		ValidateUtil.notNull(departmentId, "部门id不能为空");
		return departmentMysqlDAO.findById(departmentId);
	}

	@Override
	public void add(DepartmentDO departmentDO) {
		ValidateUtil.notNull(departmentDO, "部门不能为空");
		ValidateUtil.isNotBlank(departmentDO.getName(), "部门名称不能为空");
		ValidateUtil.isNotBlank(departmentDO.getFunction(), "部门职能不能为空");
		try {
			departmentMysqlDAO.save(departmentDO);
		} catch (DuplicateKeyException e) {
			throw new BusinessException("该部门名称已存在");
		}
	}

	@Override
	public void update(DepartmentDO departmentDO) {
		ValidateUtil.notNull(departmentDO.getId(), "部门id不能为空");
		try {
			departmentMysqlDAO.update(departmentDO);
		} catch (DuplicateKeyException e) {
			throw new BusinessException("该部门名称已存在");
		}
	}

	@Override
	public void delete(Integer departmentId) {
		ValidateUtil.notNull(departmentId, "部门id不能为空");
		departmentMysqlDAO.delete(departmentId);
	}

	@Override
	public List<DepartmentDO> findByPage(PageQuery pageQuery) {
		return departmentMysqlDAO.listPage(pageQuery);
	}

	@Override
	public Integer totalCount() {
		return departmentMysqlDAO.listCount();
	}
}
