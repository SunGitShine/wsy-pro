package com.sztx.wsy.dataaccess.domain;

import java.io.Serializable;
import java.util.Date;

public class ProductDO implements Serializable {
    /**
    * 产品id
     */
    private Integer id;

    /**
    * 产品名称
     */
    private String name;

    /**
    * 产品图片
     */
    private String url;

    /**
    * 产品颜色
     */
    private String colour;

    /**
    * 分类id
     */
    private Integer classifyId;

    /**
    * 分类名称
     */
    private String classifyName;

    /**
    * 适用性别，1：男，2：女，3：通用
     */
    private Integer applySex;

    /**
    * 适用人群，1：成人，2：儿童，3：通用
     */
    private Integer applyCrowd;

    /**
    * 最大码
     */
    private Integer maxCode;

    /**
    * 最小码
     */
    private Integer minCode;

    /**
    * 鞋面材料
     */
    private String vampMaterial;

    /**
    * 鞋底材料
     */
    private String soleMaterial;

    /**
    * 花型
     */
    private String flowerType;

    /**
    * 备注
     */
    private String remark;

    /**
    * 库存状态，1：正常，2：异常
     */
    private Integer status;

    /**
    * 库存总数
     */
    private Integer storeTotalNum;

    /**
    * 创建人
     */
    private String createUser;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 修改人
     */
    private String updateUser;

    /**
    * 修改时间
     */
    private Date updateTime;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * @param colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * @return classify_id
     */
    public Integer getClassifyId() {
        return classifyId;
    }

    /**
     * @param classifyId
     */
    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    /**
     * @return classify_name
     */
    public String getClassifyName() {
        return classifyName;
    }

    /**
     * @param classifyName
     */
    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    /**
     * @return apply_sex
     */
    public Integer getApplySex() {
        return applySex;
    }

    /**
     * @param applySex
     */
    public void setApplySex(Integer applySex) {
        this.applySex = applySex;
    }

    /**
     * @return apply_crowd
     */
    public Integer getApplyCrowd() {
        return applyCrowd;
    }

    /**
     * @param applyCrowd
     */
    public void setApplyCrowd(Integer applyCrowd) {
        this.applyCrowd = applyCrowd;
    }

    /**
     * @return max_code
     */
    public Integer getMaxCode() {
        return maxCode;
    }

    /**
     * @param maxCode
     */
    public void setMaxCode(Integer maxCode) {
        this.maxCode = maxCode;
    }

    /**
     * @return min_code
     */
    public Integer getMinCode() {
        return minCode;
    }

    /**
     * @param minCode
     */
    public void setMinCode(Integer minCode) {
        this.minCode = minCode;
    }

    /**
     * @return vamp_material
     */
    public String getVampMaterial() {
        return vampMaterial;
    }

    /**
     * @param vampMaterial
     */
    public void setVampMaterial(String vampMaterial) {
        this.vampMaterial = vampMaterial;
    }

    /**
     * @return sole_material
     */
    public String getSoleMaterial() {
        return soleMaterial;
    }

    /**
     * @param soleMaterial
     */
    public void setSoleMaterial(String soleMaterial) {
        this.soleMaterial = soleMaterial;
    }

    /**
     * @return flower_type
     */
    public String getFlowerType() {
        return flowerType;
    }

    /**
     * @param flowerType
     */
    public void setFlowerType(String flowerType) {
        this.flowerType = flowerType;
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

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return store_total_num
     */
    public Integer getStoreTotalNum() {
        return storeTotalNum;
    }

    /**
     * @param storeTotalNum
     */
    public void setStoreTotalNum(Integer storeTotalNum) {
        this.storeTotalNum = storeTotalNum;
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
}