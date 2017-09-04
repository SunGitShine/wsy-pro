package com.sztx.wsy.dataaccess.domain;

import java.io.Serializable;

public class ProductClassifyDO implements Serializable{
    /**
    * 产品分类id
     */
    private Integer id;

    /**
    * 分类名称
     */
    private String classifyName;

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
}