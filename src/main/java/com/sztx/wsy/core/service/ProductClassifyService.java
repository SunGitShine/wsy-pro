package com.sztx.wsy.core.service;

import java.util.List;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.dataaccess.domain.ProductClassifyDO;

public interface ProductClassifyService {

void add(ProductClassifyDO productClassifyDO);
	
	void delete(Integer id);
	
	void update(ProductClassifyDO productClassifyDO);
	
	ProductClassifyDO findById(Integer id);
	
	Integer totalCount();
	
	List<ProductClassifyDO> findByPage(PageQuery pageQuery);
}
