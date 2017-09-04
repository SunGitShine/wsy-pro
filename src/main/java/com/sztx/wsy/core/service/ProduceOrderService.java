package com.sztx.wsy.core.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.domain.request.OrderStatisticReq;
import com.sztx.wsy.core.service.domain.request.ProduceOrderListReq;
import com.sztx.wsy.core.service.domain.request.ProduceOrderVO;
import com.sztx.wsy.dataaccess.domain.ProduceOrderDO;
import com.sztx.wsy.dataaccess.domain.ProduceOrderProductDistributeDO;
import com.sztx.wsy.util.LoginUtil;

public interface ProduceOrderService {

	void add(ProduceOrderVO produceOrderVO, Integer smsIsOpen);
	
	List<ProduceOrderVO> findOrderList(ProduceOrderListReq req, PageQuery pageQuery);
	
	Integer orderTotalCount(ProduceOrderListReq req);
	
	void orderDistribute(List<ProduceOrderProductDistributeDO> produceOrderProductDistributeDOs, Integer type, String orderNo, String updateUser);
	
	ProduceOrderVO findByOrderNo(String orderNo, Integer type);
	
	void delete(String orderNo);
	
	void update(ProduceOrderVO produceOrderVO);
	
	void updateStatus(ProduceOrderDO produceOrderDO, Integer smsIsOpen);
	
	void updateDistribute(List<ProduceOrderProductDistributeDO> produceOrderProductDistributeDOs, Integer type, String orderNo, String operator);
	
	List<ProduceOrderDO> orderStatisticList(@Param("orderStatisticReq") OrderStatisticReq orderStatisticReq,
			@Param("pageQuery") PageQuery pageQuery);
	
	Integer orderStatisticCount(@Param("orderStatisticReq") OrderStatisticReq orderStatisticReq);
}
