package com.vosung.auapi.client.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 角色实体
 */
@Table(name = "T_AU_ROLE")
public class TAuRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleCode;

    private String roleName;

    private String roleType;

    private String property;

    private Integer createOrgId;

    private Integer useOrgId;

    private String prohibitState;

    private Integer prohibitUser;

    private Date prohibitTime;

    private String isDelete;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    private String summary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property == null ? null : property.trim();
    }

    public Integer getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(Integer createOrgId) {
        this.createOrgId = createOrgId;
    }

    public Integer getUseOrgId() {
        return useOrgId;
    }

    public void setUseOrgId(Integer useOrgId) {
        this.useOrgId = useOrgId;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }
}