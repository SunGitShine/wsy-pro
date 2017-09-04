package com.sztx.wsy.dataaccess.domain;

import java.io.Serializable;
import java.util.Date;

public class EmployeeDO implements Serializable{
    /**
    * 职员id
     */
    private Integer id;

    /**
    * 部门id
     */
    private Integer departmentId;

    /**
    * 部门名称
     */
    private String departmentName;

    /**
    * 职员工号
     */
    private String employeeNo;

    /**
    * 职员姓名
     */
    private String name;

    /**
    * 手机号
     */
    private String mobile;

    /**
    * 入职时间
     */
    private Date entryTime;

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
     * @return department_id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return department_name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return employee_no
     */
    public String getEmployeeNo() {
        return employeeNo;
    }

    /**
     * @param employeeNo
     */
    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
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
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return entry_time
     */
    public Date getEntryTime() {
        return entryTime;
    }

    /**
     * @param entryTime
     */
    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }
}