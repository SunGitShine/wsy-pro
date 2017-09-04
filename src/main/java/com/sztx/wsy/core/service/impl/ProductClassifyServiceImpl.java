package com.sztx.wsy.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.sztx.wsy.common.domain.BusinessException;
import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.ProductClassifyService;
import com.sztx.wsy.dataaccess.dao.ProductClassifyMysqlDAO;
import com.sztx.wsy.dataaccess.domain.ProductClassifyDO;
import com.sztx.wsy.util.ValidateUtil;

@Service("productClassifyService")
public class ProductClassifyServiceImpl implements ProductClassifyService{

	@Autowired
	private ProductClassifyMysqlDAO productClassifyMysqlDAO;
	
	@Override
	public void add(ProductClassifyDO productClassifyDO) {
		
		ValidateUtil.isNotBlank(productClassifyDO.getClassifyName(), "鞋类名称不能为空");
		try {
			productClassifyMysqlDAO.add(productClassifyDO);
		} catch (DuplicateKeyException e) {
			throw new BusinessException("产品分类名称已经存在");
		}
	}

	@Override
	public void delete(Integer id) {
		
		ValidateUtil.notNull(id, "鞋类id不能为空");
		productClassifyMysqlDAO.delete(id);
	}

	@Override
	public void update(ProductClassifyDO productClassifyDO) {
		
		ValidateUtil.notNull(productClassifyDO.getId(), "鞋类id不能为空");
		ValidateUtil.isNotBlank(productClassifyDO.getClassifyName(), "鞋类名称不能为空");
		try {
			productClassifyMysqlDAO.update(productClassifyDO);
		} catch (DuplicateKeyException e) {
			throw new BusinessException("产品分类名称已经存在");
		}
	}

	@Override
	public ProductClassifyDO findById(Integer id) {
		ValidateUtil.notNull(id, "鞋类id不能为空");
		return productClassifyMysqlDAO.findById(id);
	}

	@Override
	public Integer totalCount() {
		return productClassifyMysqlDAO.totalCount();
	}

	@Override
	public List<ProductClassifyDO> findByPage(PageQuery pageQuery) {
		return productClassifyMysqlDAO.findByPage(pageQuery);
	}

}
