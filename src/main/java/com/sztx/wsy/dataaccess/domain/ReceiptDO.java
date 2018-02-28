package com.sztx.wsy.dataaccess.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 票据实体类
 * @author qiang.xie
 *
 */
public class ReceiptDO implements Serializable{

	private static final long serialVersionUID = -9000522416224914020L;

	/**
	 * 票据id
	 */
	private Integer id;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 订单名称
	 */
	private String orderName;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 客户电话
	 */
	private String customerPhone;
	/**
	 * 收货地址
	 */
	private String deliveryAddress;
	/**
	 * 总双数
	 */
	private Integer totalNum;
	/**
	 * 总金额
	 */
	private Integer totalMoney;
	/**
	 * 结算状态，0：未结算，1：已结算
	 */
	private Integer balanceStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 制单时间
	 */
	private Date createReceiptTime;
	/**
	 * 结算时间
	 */
	private Date balanceTime;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 更新人
	 */
	private String updateUser;
	/**
	 * 票据商品
	 */
	private List<ReceiptProductDO> receiptProducts;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public List<ReceiptProductDO> getReceiptProducts() {
		return receiptProducts;
	}
	public void setReceiptProducts(List<ReceiptProductDO> receiptProducts) {
		this.receiptProducts = receiptProducts;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Integer getBalanceStatus() {
		return balanceStatus;
	}
	public void setBalanceStatus(Integer balanceStatus) {
		this.balanceStatus = balanceStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateReceiptTime() {
		return createReceiptTime;
	}
	public void setCreateReceiptTime(Date createReceiptTime) {
		this.createReceiptTime = createReceiptTime;
	}
	public Date getBalanceTime() {
		return balanceTime;
	}
	public void setBalanceTime(Date balanceTime) {
		this.balanceTime = balanceTime;
	}
}
