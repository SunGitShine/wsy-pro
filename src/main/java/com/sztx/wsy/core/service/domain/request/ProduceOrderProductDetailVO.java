package com.sztx.wsy.core.service.domain.request;

import java.io.Serializable;
import java.util.List;

import com.sztx.wsy.dataaccess.domain.ProduceOrderProductDistributeDO;

public class ProduceOrderProductDetailVO implements Serializable{

	private Integer id;

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 生产订单产品id
	 */
	private Integer produceOrderProductId;

	/**
	 * 鞋码
	 */
	private Integer shoeCode;

	/**
	 * 双数
	 */
	private Integer shoeNum;
	
	private List<ProduceOrderProductDistributeDO> produceOrderProductDistributeDOs;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return produce_order_product_id
	 */
	public Integer getProduceOrderProductId() {
		return produceOrderProductId;
	}

	/**
	 * @param produceOrderProductId
	 */
	public void setProduceOrderProductId(Integer produceOrderProductId) {
		this.produceOrderProductId = produceOrderProductId;
	}

	/**
	 * @return shoe_code
	 */
	public Integer getShoeCode() {
		return shoeCode;
	}

	/**
	 * @param shoeCode
	 */
	public void setShoeCode(Integer shoeCode) {
		this.shoeCode = shoeCode;
	}

	/**
	 * @return shoe_num
	 */
	public Integer getShoeNum() {
		return shoeNum;
	}

	/**
	 * @param shoeNum
	 */
	public void setShoeNum(Integer shoeNum) {
		this.shoeNum = shoeNum;
	}

	public List<ProduceOrderProductDistributeDO> getProduceOrderProductDistributeDOs() {
		return produceOrderProductDistributeDOs;
	}

	public void setProduceOrderProductDistributeDOs(
			List<ProduceOrderProductDistributeDO> produceOrderProductDistributeDOs) {
		this.produceOrderProductDistributeDOs = produceOrderProductDistributeDOs;
	}
}
