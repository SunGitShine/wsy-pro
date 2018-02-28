package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import com.sztx.wsy.dataaccess.domain.ReceiptProductDO;

public interface ReceiptProductMysqlDAO {

	void batchAdd(List<ReceiptProductDO> receiptProducts);
	
	void batchUpdate(List<ReceiptProductDO> receiptProducts);
	
	void deleteByOrderNo(String orderNo);
}
