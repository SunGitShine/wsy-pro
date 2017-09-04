package com.sztx.wsy.core.service.domain.request;

import java.io.Serializable;

public class ProduceOrderListReq implements Serializable{

	/**
	 * 订单编号
	 */
	private String orderNo;
	
	/**
	 * 订单名称
	 */
	private String orderName;
	
	/**
	 * 是否加急，1：是，2：否
	 */
	private Integer isUrgent;
	
	/**
	 * 裁剪状态，0：未处理，1：裁料完成，2：已分配，3：已完成
	 */
	private Integer tailorStatus;

	/**
	 * 上案状态，0：未处理，1：裁料完成，2：已分配，3：已完成
	 */
	private Integer vampStatus;

	/**
	 * 下案状态，0：未处理，1：处理中，2：已完成
	 */
	private Integer soleStatus;

	/**
	 * 质检状态，0：未处理，1：处理中，2：已完成
	 */
	private Integer qcStatus;

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

	public Integer getIsUrgent() {
		return isUrgent;
	}

	public void setIsUrgent(Integer isUrgent) {
		this.isUrgent = isUrgent;
	}

	public Integer getTailorStatus() {
		return tailorStatus;
	}

	public void setTailorStatus(Integer tailorStatus) {
		this.tailorStatus = tailorStatus;
	}

	public Integer getVampStatus() {
		return vampStatus;
	}

	public void setVampStatus(Integer vampStatus) {
		this.vampStatus = vampStatus;
	}

	public Integer getSoleStatus() {
		return soleStatus;
	}

	public void setSoleStatus(Integer soleStatus) {
		this.soleStatus = soleStatus;
	}

	public Integer getQcStatus() {
		return qcStatus;
	}

	public void setQcStatus(Integer qcStatus) {
		this.qcStatus = qcStatus;
	}
}
