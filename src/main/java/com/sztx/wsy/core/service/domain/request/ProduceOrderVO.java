package com.sztx.wsy.core.service.domain.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ProduceOrderVO implements Serializable,Comparable<ProduceOrderVO>{
	/**
	 * 生产订单id
	 */
	private Integer id;

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 订单名称
	 */
	private String orderName;
	
	/**
	 * 订单图片
	 */
	private String orderPic;

	/**
	 * 是否加急，1：是，2：否
	 */
	private Integer isUrgent;

	/**
	 * 交货时间
	 */
	private Date deliveryTime;
	
	/**
	 * 剩余时间（天）
	 */
	private float residueTime;

	/**
	 * 订货总双数
	 */
	private Integer orderNum;
	
	/**
	 * 订单总金额
	 */
	private Long orderAmount;

	/**
	 * 裁剪状态，0：未处理，1：裁料完成
	 */
	private Integer tailorStatus;

	/**
	 * 上案状态，0：未处理，2：已分配，3：已完成
	 */
	private Integer vampStatus;

	/**
	 * 下案状态，0：未处理，1：已分配，2：已完成
	 */
	private Integer soleStatus;

	/**
	 * 质检状态，0：未处理，1：已完成
	 */
	private Integer qcStatus;
	
	/**
	 * 裁剪处理时间
	 */
	private Date tailorFinishTime;

	/**
	 * 裁剪处理人
	 */
	private String tailorFinishUser;

	/**
	 * 上案处理时间
	 */
	private Date vampHandleTime;

	/**
	 * 上案处理人
	 */
	private String vampHandleUser;

	/**
	 * 上案完成时间
	 */
	private Date vampFinishTime;

	/**
	 * 上案完成时间
	 */
	private String vampFinishUser;

	/**
	 * 下案处理时间
	 */
	private Date soleHandleTime;

	/**
	 * 下案处理时间
	 */
	private String soleHandleUser;

	/**
	 * 下案完成时间
	 */
	private Date soleFinishTime;

	/**
	 * 下案完成人
	 */
	private String soleFinishUser;

	/**
	 * 质检完成时间
	 */
	private Date qcFinishTime;

	/**
	 * 质检完成人
	 */
	private String qcFinishUser;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 更新人
	 */
	private String updateUser;
	
	/**
	 * 订单产品列表
	 */
	private List<ProduceOrderProductVO> produceOrderProductVOs;
	
	/**
	 * 客户名称
	 */
	private String customerName;
	
	/**
	 * 客户电话
	 */
	private String customerPhone;
	
	/**
	 * 收货地址
	 */
	private String deliveryAddress;
	
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
	 * @return order_no
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return order_name
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * @param orderName
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * @return is_urgent
	 */
	public Integer getIsUrgent() {
		return isUrgent;
	}

	/**
	 * @param isUrgent
	 */
	public void setIsUrgent(Integer isUrgent) {
		this.isUrgent = isUrgent;
	}

	/**
	 * @return delivery_time
	 */
	public Date getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * @param deliveryTime
	 */
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public float getResidueTime() {
		return residueTime;
	}

	public void setResidueTime(float residueTime) {
		this.residueTime = residueTime;
	}

	/**
	 * @return order_num
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return vamp_status
	 */
	public Integer getVampStatus() {
		return vampStatus;
	}

	/**
	 * @param vampStatus
	 */
	public void setVampStatus(Integer vampStatus) {
		this.vampStatus = vampStatus;
	}

	/**
	 * @return sole_status
	 */
	public Integer getSoleStatus() {
		return soleStatus;
	}

	/**
	 * @param soleStatus
	 */
	public void setSoleStatus(Integer soleStatus) {
		this.soleStatus = soleStatus;
	}

	/**
	 * @return qc_status
	 */
	public Integer getQcStatus() {
		return qcStatus;
	}

	/**
	 * @param qcStatus
	 */
	public void setQcStatus(Integer qcStatus) {
		this.qcStatus = qcStatus;
	}

	/**
	 * @return vamp_handle_time
	 */
	public Date getVampHandleTime() {
		return vampHandleTime;
	}

	/**
	 * @param vampHandleTime
	 */
	public void setVampHandleTime(Date vampHandleTime) {
		this.vampHandleTime = vampHandleTime;
	}

	/**
	 * @return vamp_handle_user
	 */
	public String getVampHandleUser() {
		return vampHandleUser;
	}

	/**
	 * @param vampHandleUser
	 */
	public void setVampHandleUser(String vampHandleUser) {
		this.vampHandleUser = vampHandleUser;
	}

	/**
	 * @return vamp_finish_time
	 */
	public Date getVampFinishTime() {
		return vampFinishTime;
	}

	/**
	 * @param vampFinishTime
	 */
	public void setVampFinishTime(Date vampFinishTime) {
		this.vampFinishTime = vampFinishTime;
	}

	/**
	 * @return vamp_finish_user
	 */
	public String getVampFinishUser() {
		return vampFinishUser;
	}

	/**
	 * @param vampFinishUser
	 */
	public void setVampFinishUser(String vampFinishUser) {
		this.vampFinishUser = vampFinishUser;
	}

	/**
	 * @return sole_handle_time
	 */
	public Date getSoleHandleTime() {
		return soleHandleTime;
	}

	/**
	 * @param soleHandleTime
	 */
	public void setSoleHandleTime(Date soleHandleTime) {
		this.soleHandleTime = soleHandleTime;
	}

	/**
	 * @return sole_handle_user
	 */
	public String getSoleHandleUser() {
		return soleHandleUser;
	}

	/**
	 * @param soleHandleUser
	 */
	public void setSoleHandleUser(String soleHandleUser) {
		this.soleHandleUser = soleHandleUser;
	}

	/**
	 * @return sole_finish_time
	 */
	public Date getSoleFinishTime() {
		return soleFinishTime;
	}

	/**
	 * @param soleFinishTime
	 */
	public void setSoleFinishTime(Date soleFinishTime) {
		this.soleFinishTime = soleFinishTime;
	}

	/**
	 * @return sole_finish_user
	 */
	public String getSoleFinishUser() {
		return soleFinishUser;
	}

	/**
	 * @param soleFinishUser
	 */
	public void setSoleFinishUser(String soleFinishUser) {
		this.soleFinishUser = soleFinishUser;
	}

	/**
	 * @return qc_finish_time
	 */
	public Date getQcFinishTime() {
		return qcFinishTime;
	}

	/**
	 * @param qcFinishTime
	 */
	public void setQcFinishTime(Date qcFinishTime) {
		this.qcFinishTime = qcFinishTime;
	}

	/**
	 * @return qc_finish_user
	 */
	public String getQcFinishUser() {
		return qcFinishUser;
	}

	/**
	 * @param qcFinishUser
	 */
	public void setQcFinishUser(String qcFinishUser) {
		this.qcFinishUser = qcFinishUser;
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
	 * @return update_time
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return update_user
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getTailorStatus() {
		return tailorStatus;
	}

	public void setTailorStatus(Integer tailorStatus) {
		this.tailorStatus = tailorStatus;
	}

	public Date getTailorFinishTime() {
		return tailorFinishTime;
	}

	public void setTailorFinishTime(Date tailorFinishTime) {
		this.tailorFinishTime = tailorFinishTime;
	}

	public String getTailorFinishUser() {
		return tailorFinishUser;
	}

	public void setTailorFinishUser(String tailorFinishUser) {
		this.tailorFinishUser = tailorFinishUser;
	}

	public List<ProduceOrderProductVO> getProduceOrderProductVOs() {
		return produceOrderProductVOs;
	}

	public void setProduceOrderProductVOs(
			List<ProduceOrderProductVO> produceOrderProductVOs) {
		this.produceOrderProductVOs = produceOrderProductVOs;
	}

	public Long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderPic() {
		return orderPic;
	}

	public void setOrderPic(String orderPic) {
		this.orderPic = orderPic;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Override
	public int compareTo(ProduceOrderVO o) {
		
		if(isUrgent > o.getIsUrgent()){
			return 1;
		}else if (isUrgent < o.getIsUrgent()) {
			return -1;
		}else{
			if(residueTime < o.getResidueTime()){
				return -1;
			}else{
				return 1;
			}
		}
	}
}