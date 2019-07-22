package com.vosung.auapi.client.entity;

import java.util.Date;

public class TAuRolePermissionObject {
    private Integer id;

    private Integer roleId;

    private Integer businessDomainId;

    private Integer subsystemId;

    private Integer businessObjectId;

    private Integer permissionObjectId;

    private Integer permissionItemId;

    private String url;

    private String permissionStatus;

    private Integer dataRuleId;

    private String isDelete;

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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getBusinessDomainId() {
        return businessDomainId;
    }

    public void setBusinessDomainId(Integer businessDomainId) {
        this.businessDomainId = businessDomainId;
    }

    public Integer getSubsystemId() {
        return subsystemId;
    }

    public void setSubsystemId(Integer subsystemId) {
        this.subsystemId = subsystemId;
    }

    public Integer getBusinessObjectId() {
        return businessObjectId;
    }

    public void setBusinessObjectId(Integer businessObjectId) {
        this.businessObjectId = businessObjectId;
    }

    public Integer getPermissionObjectId() {
        return permissionObjectId;
    }

    public void setPermissionObjectId(Integer permissionObjectId) {
        this.permissionObjectId = permissionObjectId;
    }

    public Integer getPermissionItemId() {
        return permissionItemId;
    }

    public void setPermissionItemId(Integer permissionItemId) {
        this.permissionItemId = permissionItemId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(String permissionStatus) {
        this.permissionStatus = permissionStatus == null ? null : permissionStatus.trim();
    }

    public Integer getDataRuleId() {
        return dataRuleId;
    }

    public void setDataRuleId(Integer dataRuleId) {
        this.dataRuleId = dataRuleId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
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