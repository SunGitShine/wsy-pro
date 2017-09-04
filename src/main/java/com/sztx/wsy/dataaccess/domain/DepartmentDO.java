package com.sztx.wsy.dataaccess.domain;

import java.io.Serializable;

public class DepartmentDO implements Serializable {
    /**
    * 部门id
     */
    private Integer id;

    /**
    * 部门名称
     */
    private String name;

    /**
    * 部门职能
     */
    private String function;

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
     * @return function
     */
    public String getFunction() {
        return function;
    }

    /**
     * @param function
     */
    public void setFunction(String function) {
        this.function = function;
    }
}