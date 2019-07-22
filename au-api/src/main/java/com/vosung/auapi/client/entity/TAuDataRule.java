package com.vosung.auapi.client.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 数据规则
 */
@Table(name = "T_AU_DATA_RULE")
public class TAuDataRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dataRuleCode;

    private String dataRuleName;

    private Integer businessObjectId;

    private String isSystemInit;

    private String prohibitState;

    private Integer prohibitUser;

    private Date prohibitTime;

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

    public String getDataRuleCode() {
        return dataRuleCode;
    }

    public void setDataRuleCode(String dataRuleCode) {
        this.dataRuleCode = dataRuleCode == null ? null : dataRuleCode.trim();
    }

    public String getDataRuleName() {
        return dataRuleName;
    }

    public void setDataRuleName(String dataRuleName) {
        this.dataRuleName = dataRuleName == null ? null : dataRuleName.trim();
    }

    public Integer getBusinessObjectId() {
        return businessObjectId;
    }

    public void setBusinessObjectId(Integer businessObjectId) {
        this.businessObjectId = businessObjectId;
    }

    public String getIsSystemInit() {
        return isSystemInit;
    }

    public void setIsSystemInit(String isSystemInit) {
        this.isSystemInit = isSystemInit == null ? null : isSystemInit.trim();
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
}