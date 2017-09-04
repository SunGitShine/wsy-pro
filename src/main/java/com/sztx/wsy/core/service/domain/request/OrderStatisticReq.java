package com.sztx.wsy.core.service.domain.request;

import java.io.Serializable;
import java.util.Date;

public class OrderStatisticReq implements Serializable{

	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 订单名称
	 */
	private String orderName;
	/**
	 * 机车工号
	 */
	private String vampEmployeeNo;
	/**
	 * 底工工号
	 */
	private String soleEmployeeNo;
	/**
	 * sql类型，1：没有机车和底工工号，2：有机车工号没有底工工号，3有底工工号没有机车工号，4：有机车工号和底工工号
	 */
	private Integer sqlType;
	
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
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getVampEmployeeNo() {
		return vampEmployeeNo;
	}
	public void setVampEmployeeNo(String vampEmployeeNo) {
		this.vampEmployeeNo = vampEmployeeNo;
	}
	public String getSoleEmployeeNo() {
		return soleEmployeeNo;
	}
	public void setSoleEmployeeNo(String soleEmployeeNo) {
		this.soleEmployeeNo = soleEmployeeNo;
	}
	public Integer getSqlType() {
		return sqlType;
	}
	public void setSqlType(Integer sqlType) {
		this.sqlType = sqlType;
	}
}
