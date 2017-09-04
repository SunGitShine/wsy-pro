package com.sztx.wsy.core.service.domain.request;

import java.io.Serializable;

public class ProductStoreDetailVO implements Serializable{

	/**
	 * 鞋码
	 */
	private Integer shoeCode;

	/**
	 * 数量
	 */
	private Integer shoeNum;

	public Integer getShoeCode() {
		return shoeCode;
	}

	public void setShoeCode(Integer shoeCode) {
		this.shoeCode = shoeCode;
	}

	public Integer getShoeNum() {
		return shoeNum;
	}

	public void setShoeNum(Integer shoeNum) {
		this.shoeNum = shoeNum;
	}
}
