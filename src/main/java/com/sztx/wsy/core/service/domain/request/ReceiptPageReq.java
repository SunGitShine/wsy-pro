package com.sztx.wsy.core.service.domain.request;

import java.io.Serializable;
import java.util.Date;

/**
 * 分页查询票据请求参数
 * @author qiang.xie
 *
 */
public class ReceiptPageReq implements Serializable{

	private String orderNo;
	private String orderName;
	private String customerName;
	private Date startTime;
	private Date endTime;
	private Integer balanceStatus;
	
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getBalanceStatus() {
		return balanceStatus;
	}
	public void setBalanceStatus(Integer balanceStatus) {
		this.balanceStatus = balanceStatus;
	}
}
