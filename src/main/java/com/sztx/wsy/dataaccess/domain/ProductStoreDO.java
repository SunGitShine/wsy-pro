package com.sztx.wsy.dataaccess.domain;

import java.io.Serializable;

public class ProductStoreDO implements Serializable,Comparable<ProductStoreDO>{
	
    private Integer id;

    /**
    * 产品id
     */
    private Integer productId;

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
    
    /**
     * @return store_max
     */
    public Integer getStoreMax() {
        return storeMax;
    }

    /**
     * @param storeMax
     */
    public void setStoreMax(Integer storeMax) {
        this.storeMax = storeMax;
    }

    /**
     * @return store_min
     */
    public Integer getStoreMin() {
        return storeMin;
    }

    /**
     * @param storeMin
     */
    public void setStoreMin(Integer storeMin) {
        this.storeMin = storeMin;
    }

	@Override
	public int compareTo(ProductStoreDO o) {
		if(shoeCode > o.getShoeCode()){
			return 1;
		}else if(shoeCode < o.getShoeCode()){
			return -1;
		}else{
			return 0;
		}
	}
}