package com.sztx.wsy.dataaccess.domain;

import java.io.Serializable;

public class ProduceOrderProductDetailDO implements Serializable{
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
}