package com.sztx.wsy.dataaccess.domain;

import java.io.Serializable;

public class ProductStoreOperateDetailDO implements Serializable{
    private Integer id;

    /**
    * 库存操作id
     */
    private Integer productStoreOperateId;

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

    /**
     * @return product_store_operate_id
     */
    public Integer getProductStoreOperateId() {
        return productStoreOperateId;
    }

    /**
     * @param productStoreOperateId
     */
    public void setProductStoreOperateId(Integer productStoreOperateId) {
        this.productStoreOperateId = productStoreOperateId;
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