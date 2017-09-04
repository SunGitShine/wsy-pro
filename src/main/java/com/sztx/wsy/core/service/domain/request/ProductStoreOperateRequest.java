package com.sztx.wsy.core.service.domain.request;

import java.io.Serializable;
import java.util.List;

public class ProductStoreOperateRequest implements Serializable{

	/**
	 * 产品id
	 */
	private Integer productId;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 操作前库存总量
	 */
	private Integer storeBeforeNum;
	
	/**
	 * 库存操作类型，1：出库，2：入库
	 */
	private Integer type;
	
	/**
	 * 备注
	 */
	private String remak;
	
	/**
	 * 经办人
	 */
	private String createUser;
	
	/**
	 * 库存操作详情
	 */
	private List<ProductStoreVO> productStoreVOs;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getStoreBeforeNum() {
		return storeBeforeNum;
	}

	public void setStoreBeforeNum(Integer storeBeforeNum) {
		this.storeBeforeNum = storeBeforeNum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemak() {
		return remak;
	}

	public void setRemak(String remak) {
		this.remak = remak;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public List<ProductStoreVO> getProductStoreVOs() {
		return productStoreVOs;
	}

	public void setProductStoreVOs(List<ProductStoreVO> productStoreVOs) {
		this.productStoreVOs = productStoreVOs;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
