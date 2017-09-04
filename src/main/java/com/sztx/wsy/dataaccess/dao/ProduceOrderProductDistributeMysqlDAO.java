package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.dataaccess.domain.ProduceOrderProductDistributeDO;


public interface ProduceOrderProductDistributeMysqlDAO{
	
	List<ProduceOrderProductDistributeDO> findByDetailId(@Param("produceOrderProductDetailId")Integer produceOrderProductDetailId,
			@Param("type")Integer type);
	
	void batchAdd(@Param("produceOrderProductDistributeList") List<ProduceOrderProductDistributeDO> produceOrderProductDistributeList);
	
	void delete(@Param("orderNo") String orderNo, @Param("type") Integer type);
}