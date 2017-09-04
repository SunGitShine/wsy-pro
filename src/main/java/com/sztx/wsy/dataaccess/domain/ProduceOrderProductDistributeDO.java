package com.sztx.wsy.dataaccess.domain;

import java.io.Serializable;

public class ProduceOrderProductDistributeDO implements Serializable {
    private Integer id;

    /**
    * 生产订单产品详情id
     */
    private Integer produceOrderProductDetailId;

    /**
    * 生产订单产品id
     */
    private Integer produceOrderProductId;

    /**
    * 生产订单编号
     */
    private String produceOrderNo;

    /**
    * 鞋码
     */
    private Integer shoeCode;

    /**
    * 双数
     */
    private Integer shoeNum;

    /**
    * 工号
     */
    private String employeeNo;

    /**
    * 员工姓名
     */
    private String employeeName;

    /**
    * 分配类型，1：上案，2：下案
     */
    private Integer type;

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

    /**
     * @return produce_order_product_detail_id
     */
    public Integer getProduceOrderProductDetailId() {
        return produceOrderProductDetailId;
    }

    /**
     * @param produceOrderProductDetailId
     */
    public void setProduceOrderProductDetailId(Integer produceOrderProductDetailId) {
        this.produceOrderProductDetailId = produceOrderProductDetailId;
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

    public String getProduceOrderNo() {
		return produceOrderNo;
	}

	public void setProduceOrderNo(String produceOrderNo) {
		this.produceOrderNo = produceOrderNo;
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

    public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	/**
     * @return employee_name
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }
}