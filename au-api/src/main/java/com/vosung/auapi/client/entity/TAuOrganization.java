package com.vosung.auapi.client.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 组织机构
 */
@Table(name = "T_AU_ORGANIZATION")
public class TAuOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String orgCode;

    private String orgName;

    private String orgFormId;

    private String zipCode;

    private String contacts;

    private String telephone;

    private String address;

    private String summary;

    private String isAccountancyOrg;

    private String accountancyOrgType;

    private String isOperationOrg;

    private String orgFunctionType;

    private Integer legalPerson;

    private String dataState;

    private String prohibitState;

    private Integer prohibitUser;

    private Date prohibitTime;

    private Integer reviewUser;

    private Date reviewTime;

    private String isDelete;

    private String isSynchronousBarter;

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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgFormId() {
        return orgFormId;
    }

    public void setOrgFormId(String orgFormId) {
        this.orgFormId = orgFormId == null ? null : orgFormId.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getIsAccountancyOrg() {
        return isAccountancyOrg;
    }

    public void setIsAccountancyOrg(String isAccountancyOrg) {
        this.isAccountancyOrg = isAccountancyOrg == null ? null : isAccountancyOrg.trim();
    }

    public String getAccountancyOrgType() {
        return accountancyOrgType;
    }

    public void setAccountancyOrgType(String accountancyOrgType) {
        this.accountancyOrgType = accountancyOrgType == null ? null : accountancyOrgType.trim();
    }

    public String getIsOperationOrg() {
        return isOperationOrg;
    }

    public void setIsOperationOrg(String isOperationOrg) {
        this.isOperationOrg = isOperationOrg == null ? null : isOperationOrg.trim();
    }

    public String getOrgFunctionType() {
        return orgFunctionType;
    }

    public void setOrgFunctionType(String orgFunctionType) {
        this.orgFunctionType = orgFunctionType == null ? null : orgFunctionType.trim();
    }

    public Integer getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(Integer legalPerson) {
        this.legalPerson = legalPerson;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    public String getIsSynchronousBarter() {
        return isSynchronousBarter;
    }

    public void setIsSynchronousBarter(String isSynchronousBarter) {
        this.isSynchronousBarter = isSynchronousBarter == null ? null : isSynchronousBarter.trim();
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