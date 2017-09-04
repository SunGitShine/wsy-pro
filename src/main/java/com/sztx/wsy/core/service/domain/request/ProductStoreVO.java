package com.sztx.wsy.core.service.domain.request;

import java.io.Serializable;

public class ProductStoreVO implements Serializable {

	/**
	 * 库存id
	 */
	private Integer id;
	
	/**
	 * 鞋码
	 */
	private Integer shoeCode;

	/**
	 * 数量
	 */
	private Integer shoeNum;

	/**
	 * 库存上限，-1表示不限
	 */
	private Integer storeMax;

	/**
	 * 库存下限，-1表示不限制
	 */
	private Integer storeMin;
	
	/**
	 * 库存操作数量
	 */
	private Integer operateNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getStoreMax() {
		return storeMax;
	}

	public void setStoreMax(Integer storeMax) {
		this.storeMax = storeMax;
	}

	public Integer getStoreMin() {
		return storeMin;
	}

	public void setStoreMin(Integer storeMin) {
		this.storeMin = storeMin;
	}

	public Integer getOperateNum() {
		return operateNum;
	}

	public void setOperateNum(Integer operateNum) {
		this.operateNum = operateNum;
	}
}
