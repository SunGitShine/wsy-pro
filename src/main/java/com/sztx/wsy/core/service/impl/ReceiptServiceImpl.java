package com.sztx.wsy.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.ReceiptService;
import com.sztx.wsy.core.service.domain.request.ReceiptPageReq;
import com.sztx.wsy.dataaccess.dao.ReceiptMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ReceiptProductMysqlDAO;
import com.sztx.wsy.dataaccess.domain.ReceiptDO;
import com.sztx.wsy.dataaccess.domain.ReceiptProductDO;

@Service("receiptService")
public class ReceiptServiceImpl implements ReceiptService{
	
	@Autowired
	private ReceiptMysqlDAO receiptMysqlDAO;
	
	@Autowired
	private ReceiptProductMysqlDAO receiptProductMysqlDAO;
	
	@Override
	public void createReceipt(ReceiptDO receiptDO) {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String orderNo = sf.format(new Date());
		
		makeReceiptParam(receiptDO, orderNo);
		receiptDO.setBalanceStatus(0);
		
		receiptMysqlDAO.add(receiptDO);
		receiptProductMysqlDAO.batchAdd(receiptDO.getProducts());
	}
	
	//组装票据数据
	public ReceiptDO makeReceiptParam(ReceiptDO receiptDO, String orderNo){
		
		Integer totalNum = 0;//产品总双数
		Integer totalMoney = 0;//产品总金额
		receiptDO.setOrderNo(orderNo);
		List<ReceiptProductDO> receiptProducts = receiptDO.getProducts();
		
		for(ReceiptProductDO receiptProductDO : receiptProducts){
			
			Integer productNum = receiptProductDO.getProductNum();//双数
			Integer productPrice = receiptProductDO.getProductPrice();//单价
			
			receiptProductDO.setOrderNo(orderNo);
			totalNum = totalNum + productNum;
			if(productPrice != null){
				Integer money = productNum * productPrice;//该商品总价
				totalMoney = totalMoney + money;
				receiptProductDO.setTotalMoney(money);
			}
		}
		
		receiptDO.setTotalNum(totalNum);
		receiptDO.setTotalMoney(totalMoney);
		return receiptDO;
	}

	@Override
	public ReceiptDO getReceipt(String orderNo) {
		
		ReceiptDO receiptDO = receiptMysqlDAO.findByOrderNo(orderNo);
		List<ReceiptProductDO> products = receiptProductMysqlDAO.findByOrderNo(orderNo);
		receiptDO.setProducts(products);
		return receiptDO;
	}

	@Override
	public void updateBalanceStatus(String orderNo) {
		
		ReceiptDO receiptDO = new ReceiptDO();
		receiptDO.setBalanceStatus(1);
		receiptDO.setOrderNo(orderNo);
		receiptDO.setBalanceTime(new Date());
		
		receiptMysqlDAO.update(receiptDO);
	}

	@Override
	public void print(String orderNo) {
		
		ReceiptDO receiptDO = new ReceiptDO();
		receiptDO.setOrderNo(orderNo);
		receiptDO.setCreateReceiptTime(new Date());
		
		receiptMysqlDAO.update(receiptDO);
	}

	@Override
	public Integer totalCount(ReceiptPageReq receiptPageReq) {
		return receiptMysqlDAO.totalCount(receiptPageReq);
	}

	@Override
	public List<ReceiptDO> findByPage(ReceiptPageReq receiptPageReq, PageQuery pageQuery) {
		return receiptMysqlDAO.findByPage(receiptPageReq, pageQuery);
	}

	@Override
	public void updateReceipt(ReceiptDO receiptDO) {
		
		receiptProductMysqlDAO.deleteByOrderNo(receiptDO.getOrderNo());//删除票据商品表数据
		makeReceiptParam(receiptDO, receiptDO.getOrderNo());
		receiptMysqlDAO.update(receiptDO);//更新票据表
		receiptProductMysqlDAO.batchAdd(receiptDO.getProducts());//批量插入票据商品表
	}

	@Override
	public Map<String, Integer> getSumMsg(ReceiptPageReq receiptPageReq) {
		return receiptMysqlDAO.getSumMsg(receiptPageReq);
	}
}
