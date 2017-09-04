package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.dataaccess.domain.ProductDO;


public interface ProductMysqlDAO {
	
	void add(ProductDO productDO);
	
	ProductDO findById(Integer id);
	
	ProductDO findByName(String name);
	
	void update(ProductDO productDO);
	
	Integer totalCount(@Param("productDO") ProductDO productDO);
	
	List<ProductDO> findByPage(@Param("productDO") ProductDO productDO,
			@Param("pageQuery") PageQuery pageQuery);
	
	void delete(Integer productId);
}