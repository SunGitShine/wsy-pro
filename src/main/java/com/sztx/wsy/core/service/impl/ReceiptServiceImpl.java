package com.sztx.wsy.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sztx.wsy.common.domain.BusinessException;
import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.ReceiptService;
import com.sztx.wsy.core.service.domain.request.ReceiptPageReq;
import com.sztx.wsy.dataaccess.dao.ProduceOrderMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ReceiptMysqlDAO;
import com.sztx.wsy.dataaccess.dao.ReceiptProductMysqlDAO;
import com.sztx.wsy.dataaccess.domain.ProduceOrderDO;
import com.sztx.wsy.dataaccess.domain.ReceiptDO;
import com.sztx.wsy.dataaccess.domain.ReceiptProductDO;
import com.sztx.wsy.util.MsgUtil;

@Service("receiptService")
public class ReceiptServiceImpl implements ReceiptService{
	
	private static final Logger log = LoggerFactory.getLogger(ReceiptServiceImpl.class);
	
	private static final String MSG_CONTENT = "尊敬的%s，您近期在舞思韵工厂有一笔订单，总双数：%s双，总金额：%s元；具体如下：%s";
	private static final String PRODUCT_CONTENT = "%s，%s元/双，%s双，%s元；\n";
	
	@Autowired
	private ReceiptMysqlDAO receiptMysqlDAO;
	
	@Autowired
	private ReceiptProductMysqlDAO receiptProductMysqlDAO;
	
	@Autowired
	private ProduceOrderMysqlDAO produceOrderMysqlDAO;
	
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
		
		receiptMysqlDAO.print(orderNo);
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

	//获取统计数量
	@Override
	public Map<String, Integer> getSumMsg(ReceiptPageReq receiptPageReq) {
		return receiptMysqlDAO.getSumMsg(receiptPageReq);
	}
	
	@Override
	public ProduceOrderDO findByOrderNo(String orderNo) {
		
		return produceOrderMysqlDAO.findByOrderNo(orderNo);
	}

	//发送短信给用户
	@Override
	public void sendMsg(String orderNo) {
		
		ReceiptDO receiptDO = receiptMysqlDAO.findByOrderNo(orderNo);
		if(receiptDO == null) {
			throw new BusinessException("不存在此送货单");
		}
		List<ReceiptProductDO> receiptProductDOs = receiptProductMysqlDAO.findByOrderNo(orderNo);
		String productMsg = "";  //商品详细信息
		for(int i = 0; i < receiptProductDOs.size(); i++) {
			ReceiptProductDO receiptProductDO = receiptProductDOs.get(i);
			if(i == 0) {
				productMsg = "\n";
				
			}
			productMsg = productMsg + String.format(PRODUCT_CONTENT, receiptProductDO.getProductName(), 
					receiptProductDO.getProductPrice()/100, receiptProductDO.getProductNum(), receiptProductDO.getTotalMoney()/100);
		}
		
		String msgContent = String.format(MSG_CONTENT, receiptDO.getCustomerName(), receiptDO.getTotalNum(), receiptDO.getTotalMoney()/100, productMsg);
		String tplValue = "#customerName#=" + receiptDO.getCustomerName() + "&#totalNum#=" + 
		receiptDO.getTotalNum() + "&#totalMoney#=" + receiptDO.getTotalMoney()/100 + "&#productMsg#=" + productMsg;
		log.info("发送的短信内容:" + msgContent);
		try {
			MsgUtil.sendSMS("66753", tplValue, receiptDO.getCustomerPhone());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("短信通知用户时，发送异常");
		}
	}
	//尊敬的#customerName#，您近期在舞思韵工厂有一笔订单，总双数：#totalNum#双，总金额：#totalMoney#元；\n具体如下：\n#productMsg#
	//通知用户订单详细信息
}
