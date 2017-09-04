package com.sztx.wsy.dataaccess.domain;

import java.io.Serializable;

public class AccountDO implements Serializable {
    /**
    * 账号id
     */
    private Integer id;

    /**
    * 账号
     */
    private String username;

    /**
    * 真实姓名
     */
    private String realname;

    /**
    * 密码
     */
    private String password;
    
    /**
     * 手机号
     */
    private String mobile;
    
    /**
    * 账号类型，1：超级管理员，2：上案管理员，3：下案管理员，4：质检管理员，5：库存管理员
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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return realname
     */
    public String getRealname() {
        return realname;
    }

    /**
     * @param realname
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}