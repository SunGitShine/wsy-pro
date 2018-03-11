package com.sztx.wsy.dataaccess.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.domain.request.ReceiptPageReq;
import com.sztx.wsy.dataaccess.domain.ReceiptDO;

public interface ReceiptMysqlDAO {

	void add(ReceiptDO receiptDO);
	
	void update(ReceiptDO receiptDO);
	
	void deleteByOrderNo(@Param("orderNo")String orderNo);
	
	ReceiptDO findByOrderNo(@Param("orderNo")String orderNo);
	
	Integer totalCount(@Param("receiptPageReq")ReceiptPageReq receiptPageReq);
	
	List<ReceiptDO> findByPage(@Param("receiptPageReq")ReceiptPageReq receiptPageReq, 
			@Param("pageQuery")PageQuery pageQuery);
	
	Map<String, Integer> getSumMsg(@Param("receiptPageReq")ReceiptPageReq receiptPageReq);
	
	void print(@Param("orderNo")String orderNo);
}
