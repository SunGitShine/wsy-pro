package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.dataaccess.domain.ProductStoreSetDO;


public interface ProductStoreSetMysqlDAO{
	
	List<ProductStoreSetDO> findByProductId(Integer productId);
	
	void batchAdd(@Param("productStoreSetList") List<ProductStoreSetDO> productStoreSetList);
	
}