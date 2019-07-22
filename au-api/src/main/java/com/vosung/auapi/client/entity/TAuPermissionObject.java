package com.vosung.auapi.client.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 权限对象
 */
@Table(name = "T_AU_PERMISSION_OBJECT")
public class TAuPermissionObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer subsystemId;

    private String permissionObjectCode;

    private String permissionObjectName;

    private String isSystemInit;

    private String remark;

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

    public Integer getSubsystemId() {
        return subsystemId;
    }

    public void setSubsystemId(Integer subsystemId) {
        this.subsystemId = subsystemId;
    }

    public String getPermissionObjectCode() {
        return permissionObjectCode;
    }

    public void setPermissionObjectCode(String permissionObjectCode) {
        this.permissionObjectCode = permissionObjectCode == null ? null : permissionObjectCode.trim();
    }

    public String getPermissionObjectName() {
        return permissionObjectName;
    }

    public void setPermissionObjectName(String permissionObjectName) {
        this.permissionObjectName = permissionObjectName == null ? null : permissionObjectName.trim();
    }

    public String getIsSystemInit() {
        return isSystemInit;
    }

    public void setIsSystemInit(String isSystemInit) {
        this.isSystemInit = isSystemInit == null ? null : isSystemInit.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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