package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.domain.request.ReceiptPageReq;
import com.sztx.wsy.dataaccess.domain.ReceiptDO;

public interface ReceiptMysqlDAO {

	void add(ReceiptDO receiptDO);
	
	void update(ReceiptDO receiptDO);
	
	void deleteByOrderNo(String orderNo);
	
	ReceiptDO findByOrderNo(String orderNo);
	
	Integer totalCount(ReceiptPageReq receiptPageReq);
	
	List<ReceiptDO> findByPage(ReceiptPageReq receiptPageReq, PageQuery pageQuery);
}
