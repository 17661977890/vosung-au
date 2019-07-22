package com.vosung.auapi.client.entity;

import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 员工任岗实体
 */
@ToString
@Table(name = "T_AU_EMPLOYEE_POSITION")
public class TAuEmployeePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer employeeId;

    private Integer workOrgId;

    private Integer belongDeptId;

    private Integer directorPositionId;

    private Date directorPositionStartDate;

    private String isMainPosition;

    private String isDelete;

    private String dataState;

    private String prohibitState;

    private Integer prohibitUser;

    private Date prohibitTime;

    private Integer reviewUser;

    private Date reviewTime;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getWorkOrgId() {
        return workOrgId;
    }

    public void setWorkOrgId(Integer workOrgId) {
        this.workOrgId = workOrgId;
    }

    public Integer getBelongDeptId() {
        return belongDeptId;
    }

    public void setBelongDeptId(Integer belongDeptId) {
        this.belongDeptId = belongDeptId;
    }

    public Integer getDirectorPositionId() {
        return directorPositionId;
    }

    public void setDirectorPositionId(Integer directorPositionId) {
        this.directorPositionId = directorPositionId;
    }

    public Date getDirectorPositionStartDate() {
        return directorPositionStartDate;
    }

    public void setDirectorPositionStartDate(Date directorPositionStartDate) {
        this.directorPositionStartDate = directorPositionStartDate;
    }

    public String getIsMainPosition() {
        return isMainPosition;
    }

    public void setIsMainPosition(String isMainPosition) {
        this.isMainPosition = isMainPosition == null ? null : isMainPosition.trim();
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState == null ? null : dataState.trim();
    }

    public String getProhibitState() {
        return prohibitState;
    }

    public void setProhibitState(String prohibitState) {
        this.prohibitState = prohibitState == null ? null : prohibitState.trim();
    }

    public Integer getProhibitUser() {
        return prohibitUser;
    }

    public void setProhibitUser(Integer prohibitUser) {
        this.prohibitUser = prohibitUser;
    }

    public Date getProhibitTime() {
        return prohibitTime;
    }

    public void setProhibitTime(Date prohibitTime) {
        this.prohibitTime = prohibitTime;
    }

    public Integer getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(Integer reviewUser) {
        this.reviewUser = reviewUser;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}