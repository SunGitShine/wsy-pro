package com.sztx.wsy.dataaccess.domain;

import java.io.Serializable;
import java.util.Date;

public class ProductStoreOperateDO implements Serializable{
    /**
    * 库存明细id
     */
    private Integer id;
    
    /**
	 * 产品名称
	 */
	private String productName;

    /**
    * 产品id
     */
    private Integer productId;

    /**
    * 类型：1，出库，2：入库
     */
    private Integer type;

    /**
    * 操作前库存数量
     */
    private Integer storeBeforeNum;

    /**
    * 操作后库存数量
     */
    private Integer storeAfterNum;

    /**
    * 库存操作数量
     */
    private Integer storeOperateNum;

    /**
    * 备注
     */
    private String remak;

    /**
    * 经办人
     */
    private String createUser;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 操作前库存
     */
    private String storeBefore;

    /**
    * 操作后库存
     */
    private String storeAfter;

    /**
    * 库存设置
     */
    private String storeSet;

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
     * @return product_id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
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

    /**
     * @return store_before_num
     */
    public Integer getStoreBeforeNum() {
        return storeBeforeNum;
    }

    /**
     * @param storeBeforeNum
     */
    public void setStoreBeforeNum(Integer storeBeforeNum) {
        this.storeBeforeNum = storeBeforeNum;
    }

    /**
     * @return store_after_num
     */
    public Integer getStoreAfterNum() {
        return storeAfterNum;
    }

    /**
     * @param storeAfterNum
     */
    public void setStoreAfterNum(Integer storeAfterNum) {
        this.storeAfterNum = storeAfterNum;
    }

    /**
     * @return store_operate_num
     */
    public Integer getStoreOperateNum() {
        return storeOperateNum;
    }

    /**
     * @param storeOperateNum
     */
    public void setStoreOperateNum(Integer storeOperateNum) {
        this.storeOperateNum = storeOperateNum;
    }

    /**
     * @return remak
     */
    public String getRemak() {
        return remak;
    }

    /**
     * @param remak
     */
    public void setRemak(String remak) {
        this.remak = remak;
    }

    /**
     * @return create_user
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return store_before
     */
    public String getStoreBefore() {
        return storeBefore;
    }

    /**
     * @param storeBefore
     */
    public void setStoreBefore(String storeBefore) {
        this.storeBefore = storeBefore;
    }

    /**
     * @return store_after
     */
    public String getStoreAfter() {
        return storeAfter;
    }

    /**
     * @param storeAfter
     */
    public void setStoreAfter(String storeAfter) {
        this.storeAfter = storeAfter;
    }

    /**
     * @return store_set
     */
    public String getStoreSet() {
        return storeSet;
    }

    /**
     * @param storeSet
     */
    public void setStoreSet(String storeSet) {
        this.storeSet = storeSet;
    }

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}