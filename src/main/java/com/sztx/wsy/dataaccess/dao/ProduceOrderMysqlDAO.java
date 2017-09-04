package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.domain.request.OrderStatisticReq;
import com.sztx.wsy.core.service.domain.request.ProduceOrderListReq;
import com.sztx.wsy.dataaccess.domain.ProduceOrderDO;


public interface ProduceOrderMysqlDAO {
	
	ProduceOrderDO findByOrderNo(String orderNo);
	
	void delete(String orderNo);
	
	void add(ProduceOrderDO produceOrderDO);
	
	List<ProduceOrderDO> findOrderList(@Param("produceOrderListReq") ProduceOrderListReq produceOrderListReq,
			@Param("pageQuery") PageQuery pageQuery);
	
	Integer orderTotalCount(@Param("produceOrderListReq") ProduceOrderListReq produceOrderListReq);
	
	void update(ProduceOrderDO produceOrderDO);
	
	List<ProduceOrderDO> orderStatisticList(@Param("orderStatisticReq") OrderStatisticReq orderStatisticReq,
			@Param("pageQuery") PageQuery pageQuery);
	
	Integer orderStatisticCount(@Param("orderStatisticReq") OrderStatisticReq orderStatisticReq);
}