package com.sztx.wsy.dataaccess.domain;

import java.io.Serializable;

/**
 * 票据产品实体类
 * @author qiang.xie
 *
 */
public class ReceiptProductDO implements Serializable{

	private static final long serialVersionUID = 6010391747795953820L;

//	public ReceiptProductDO() {
//		productName = "";
//		productNum = null;
//		productPrice = null;
//		totalMoney = null;
//	}
	
	/**
	 * 票据产品id
	 */
	private Integer id;

	/**
	 * 订单编号
	 */
	private String orderNo;
	
	/**
	 * 产品名称 
	 */
	private String productName;
	/**
	 * 产品数量
	 */
	private Integer productNum;
	/**
	 * 产品单价
	 */
	private Integer productPrice;
	/**
	 * 产品总价
	 */
	private Integer totalMoney;
	
	/**
	 * 货号
	 */
	private String artNo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getArtNo() {
		return artNo;
	}
	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}
}
