package com.sztx.wsy.core.service.domain.request;

import java.io.Serializable;
import java.util.Date;

public class ProductStoreOperateListReq implements Serializable {

	/**
	 * 产品id
	 */
	private Integer productId;
	/**
	 * 类型：1，出库，2：入库
	 */
	private Integer type;
	/**
	 * 经办人
	 */
	private String createUser;

	/**
	 * 开始时间
	 */
	private Date startTime;

	/**
	 * 结束时间
	 */
	private Date endTime;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

}
