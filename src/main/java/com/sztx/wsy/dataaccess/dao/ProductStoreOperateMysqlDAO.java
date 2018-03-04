package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.domain.request.ProductStoreOperateListReq;
import com.sztx.wsy.dataaccess.domain.ProductStoreOperateDO;


public interface ProductStoreOperateMysqlDAO {
	
	ProductStoreOperateDO findByProductId(Integer productId);
	
	void add(ProductStoreOperateDO productStoreOperateDO);
	
	Integer totalCount(@Param("request") ProductStoreOperateListReq request);
	
	List<ProductStoreOperateDO> findByPage(@Param("request") ProductStoreOperateListReq request,
			@Param("pageQuery") PageQuery pageQuery);
	
	Integer getInputNum(@Param("request") ProductStoreOperateListReq request);
	
	Integer getOutputNum(@Param("request") ProductStoreOperateListReq request);
}