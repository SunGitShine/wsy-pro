package com.sztx.wsy.core.service;

import java.util.List;
import java.util.Map;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.domain.request.ReceiptPageReq;
import com.sztx.wsy.dataaccess.domain.ProduceOrderDO;
import com.sztx.wsy.dataaccess.domain.ReceiptDO;

public interface ReceiptService {

	void createReceipt(ReceiptDO receiptDO);
	
	void updateReceipt(ReceiptDO receiptDO);
	
	ReceiptDO getReceipt(String orderNo);
	
	void updateBalanceStatus(String orderNo);
	
	void print(String orderNo);
	
	Integer totalCount(ReceiptPageReq receiptPageReq);
	
	List<ReceiptDO> findByPage(ReceiptPageReq receiptPageReq, PageQuery pageQuery);
	
	Map<String, Integer> getSumMsg(ReceiptPageReq receiptPageReq);
	
	ProduceOrderDO findByOrderNo(String orderNo);
	
	void sendMsg(String orderNo);
}
