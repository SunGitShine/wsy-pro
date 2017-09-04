package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.dataaccess.domain.ProductStoreOperateDetailDO;


public interface ProductStoreOperateDetailMysqlDAO {
	
	List<ProductStoreOperateDetailDO> findByProductStoreOperateId(Integer productStoreOperateId);
	
	void batchAdd(@Param("productStoreOperateDetailList") List<ProductStoreOperateDetailDO> productStoreOperateDetailList);
}