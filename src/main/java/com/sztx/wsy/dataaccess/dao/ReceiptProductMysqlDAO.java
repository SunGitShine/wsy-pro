package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.dataaccess.domain.ReceiptProductDO;

public interface ReceiptProductMysqlDAO {

	void batchAdd(List<ReceiptProductDO> receiptProducts);
	
	void batchUpdate(List<ReceiptProductDO> receiptProducts);
	
	void deleteByOrderNo(@Param("orderNo")String orderNo);
	
	List<ReceiptProductDO> findByOrderNo(@Param("orderNo")String orderNo);
}
