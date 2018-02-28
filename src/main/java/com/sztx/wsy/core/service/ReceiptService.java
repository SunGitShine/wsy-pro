package com.sztx.wsy.core.service;

import java.util.List;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.domain.request.ReceiptPageReq;
import com.sztx.wsy.dataaccess.domain.ReceiptDO;

public interface ReceiptService {

	void createReceipt(ReceiptDO receiptDO);
	
	ReceiptDO getReceipt(String orderNo);
	
	void updateBalanceStatus(String orderNo);
	
	void print(String orderNo);
	
	Integer totalCount(ReceiptPageReq receiptPageReq);
	
	List<ReceiptDO> findByPage(ReceiptPageReq receiptPageReq, PageQuery pageQuery);
}