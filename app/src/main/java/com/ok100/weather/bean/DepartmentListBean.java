package com.ok100.weather.bean;

/**
 * Created by Administrator on 2017-2-15.
 */

public class DepartmentListBean {

    /**
     * departmentId : 9f63766fe7a44b55a8d3d07a480de983
     * departmentName : 行政公告
     */

    private String departmentId;
    private String departmentName;

    public DepartmentListBean(String departmentName) {
        this.departmentName = departmentName;
    }

    public DepartmentListBean() {
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
