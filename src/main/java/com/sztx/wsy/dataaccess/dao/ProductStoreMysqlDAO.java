package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.dataaccess.domain.ProductStoreDO;


public interface ProductStoreMysqlDAO {
	
	List<ProductStoreDO> findByProductId(@Param("productId")Integer productId);
	
	void batchAdd(@Param("productStoreList") List<ProductStoreDO> productStoreList);
	
	void batchUpdate(@Param("productStoreList") List<ProductStoreDO> productStoreList);
	
	void batchUpdateStoreSet(@Param("productStoreList") List<ProductStoreDO> productStoreList);
	
	void delete(Integer productId);
}