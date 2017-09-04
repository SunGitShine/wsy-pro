package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.dataaccess.domain.ProduceOrderProductDetailDO;


public interface ProduceOrderProductDetailMysqlDAO{
	
	List<ProduceOrderProductDetailDO> findByProductId(@Param("produceOrderProductId")Integer produceOrderProductId);
	
	void batchAdd(@Param("orderProductDetailList") List<ProduceOrderProductDetailDO> orderProductDetailList);
	
	void delete(String orderNo);
}