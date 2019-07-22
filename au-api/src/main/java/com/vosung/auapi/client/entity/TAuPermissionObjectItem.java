package com.vosung.auapi.client.entity;

import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 权限对象 权限项关联
 */
@ToString
@Table(name = "T_AU_PERMISSION_OBJECT_ITEM")
public class TAuPermissionObjectItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer permissionObjectId;

    private Integer permissionItemId;

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