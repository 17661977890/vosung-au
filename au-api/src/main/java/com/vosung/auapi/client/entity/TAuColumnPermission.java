package com.vosung.auapi.client.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 字段权限
 */
@Table(name = "T_AU_COLUMN_PERMISSION")
public class TAuColumnPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer businessDomainId;

    private Integer subsystemId;

    private Integer businessObjectId;

    private String tableName;

    private String columnName;

    private Integer roleId;

    private String viewRights;

    private String editRights;

    private String dataState;

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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getViewRights() {
        return viewRights;
    }

    public void setViewRights(String viewRights) {
        this.viewRights = viewRights == null ? null : viewRights.trim();
    }

    public String getEditRights() {
        return editRights;
    }

    public void setEditRights(String editRights) {
        this.editRights = editRights == null ? null : editRights.trim();
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState == null ? null : dataState.trim();
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