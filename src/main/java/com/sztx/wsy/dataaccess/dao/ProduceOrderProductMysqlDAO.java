package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import com.sztx.wsy.dataaccess.domain.ProduceOrderProductDO;


public interface ProduceOrderProductMysqlDAO {
	
	List<ProduceOrderProductDO> findByOrderNo(String orderNo);
	
	void add(ProduceOrderProductDO produceOrderProductDO);
	
	void delete(String orderNo);
}