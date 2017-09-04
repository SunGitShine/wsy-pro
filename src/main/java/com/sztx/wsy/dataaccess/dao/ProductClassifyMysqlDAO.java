package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.dataaccess.domain.ProductClassifyDO;


public interface ProductClassifyMysqlDAO {
	
	void add(ProductClassifyDO productClassifyDO);
	
	void delete(Integer id);
	
	void update(ProductClassifyDO productClassifyDO);
	
	ProductClassifyDO findById(Integer id);
	
	Integer totalCount();
	
	List<ProductClassifyDO> findByPage(@Param("pageQuery") PageQuery pageQuery);
}