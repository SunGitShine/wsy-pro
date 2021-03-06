package com.sztx.wsy.core.service.domain.request;

import java.io.Serializable;
import java.util.List;

import com.sztx.wsy.dataaccess.domain.ProduceOrderProductDetailDO;

public class ProduceOrderProductVO implements Serializable{
    /**
    * 生产产品id
     */
    private Integer id;

    /**
    * 订单编号
     */
    private String orderNo;
    
    /**
     * 产品单价
     */
    private Integer producePrice;
    
    /**
     * 产品金额
     */
    private Long produceAmount;
    
    /**
     * 产品双数
     */
    private Integer produceNum;

    /**
    * 产品名称
     */
    private String productName;

    /**
     * 产品url
     */
    private String productUrl;

    /**
     * 生产要求
     */
    private String produceAsk;

    /**
    * 生产备注
     */
    private String remark;
    
    /**
     * 产品鞋码列表
     */
    private List<ProduceOrderProductDetailDO> produceOrderProductDetailDOs;
    
    /**
     * 产品鞋码列表
     */
    private List<ProduceOrderProductDetailVO> produceOrderProductDetailVOs;

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
     * @return product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return product_url
     */
    public String getProductUrl() {
        return productUrl;
    }

    /**
     * @param productUrl
     */
    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    /**
     * @return produce_ask
     */
    public String getProduceAsk() {
        return produceAsk;
    }

    /**
     * @param produceAsk
     */
    public void setProduceAsk(String produceAsk) {
        this.produceAsk = produceAsk;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

	public Integer getProducePrice() {
		return producePrice;
	}

	public void setProducePrice(Integer producePrice) {
		this.producePrice = producePrice;
	}

	public Long getProduceAmount() {
		return produceAmount;
	}

	public void setProduceAmount(Long produceAmount) {
		this.produceAmount = produceAmount;
	}

	public Integer getProduceNum() {
		return produceNum;
	}

	public void setProduceNum(Integer produceNum) {
		this.produceNum = produceNum;
	}

	public List<ProduceOrderProductDetailDO> getProduceOrderProductDetailDOs() {
		return produceOrderProductDetailDOs;
	}

	public void setProduceOrderProductDetailDOs(
			List<ProduceOrderProductDetailDO> produceOrderProductDetailDOs) {
		this.produceOrderProductDetailDOs = produceOrderProductDetailDOs;
	}

	public List<ProduceOrderProductDetailVO> getProduceOrderProductDetailVOs() {
		return produceOrderProductDetailVOs;
	}

	public void setProduceOrderProductDetailVOs(
			List<ProduceOrderProductDetailVO> produceOrderProductDetailVOs) {
		this.produceOrderProductDetailVOs = produceOrderProductDetailVOs;
	}
}
