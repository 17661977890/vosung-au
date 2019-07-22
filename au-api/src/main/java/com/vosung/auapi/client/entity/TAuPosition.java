package com.vosung.auapi.client.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 岗位实体
 */
@Table(name = "T_AU_POSITION")
public class TAuPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String positionCode;

    private String positionName;

    private String mnemonicCode;

    private Integer belongDeptId;

    private String isHr;

    private String isResponsiblePosition;

    private Date effectDate;

    private Date abateDate;

    private String dataState;

    private String summary;

    private String isFromSHr;

    private Integer createOrgId;

    private Integer useOrgId;

    private String prohibitState;

    private Integer prohibitUser;

    private Date prohibitTime;

    private Integer reviewUser;

    private Date reviewTime;

    private String isDelete;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;

    @Transient
    private String orgName;
    @Transient
    private String deptName;
    @Transient
    private String deptFullName;
    @Transient
    private boolean hasChildren;

    public String getDeptName() {
        return deptName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptFullName() {
        return deptFullName;
    }

    public void setDeptFullName(String deptFullName) {
        this.deptFullName = deptFullName;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode == null ? null : mnemonicCode.trim();
    }

    public Integer getBelongDeptId() {
        return belongDeptId;
    }

    public void setBelongDeptId(Integer belongDeptId) {
        this.belongDeptId = belongDeptId;
    }

    public String getIsHr() {
        return isHr;
    }

    public void setIsHr(String isHr) {
        this.isHr = isHr == null ? null : isHr.trim();
    }

    public String getIsResponsiblePosition() {
        return isResponsiblePosition;
    }

    public void setIsResponsiblePosition(String isResponsiblePosition) {
        this.isResponsiblePosition = isResponsiblePosition == null ? null : isResponsiblePosition.trim();
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Date getAbateDate() {
        return abateDate;
    }

    public void setAbateDate(Date abateDate) {
        this.abateDate = abateDate;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState == null ? null : dataState.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getIsFromSHr() {
        return isFromSHr;
    }

    public void setIsFromSHr(String isFromSHr) {
        this.isFromSHr = isFromSHr == null ? null : isFromSHr.trim();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TAuPosition that = (TAuPosition) o;
        return hasChildren == that.hasChildren &&
                Objects.equals(id, that.id) &&
                Objects.equals(positionCode, that.positionCode) &&
                Objects.equals(positionName, that.positionName) &&
                Objects.equals(mnemonicCode, that.mnemonicCode) &&
                Objects.equals(belongDeptId, that.belongDeptId) &&
                Objects.equals(isHr, that.isHr) &&
                Objects.equals(isResponsiblePosition, that.isResponsiblePosition) &&
                Objects.equals(effectDate, that.effectDate) &&
                Objects.equals(abateDate, that.abateDate) &&
                Objects.equals(dataState, that.dataState) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(isFromSHr, that.isFromSHr) &&
                Objects.equals(createOrgId, that.createOrgId) &&
                Objects.equals(useOrgId, that.useOrgId) &&
                Objects.equals(prohibitState, that.prohibitState) &&
                Objects.equals(prohibitUser, that.prohibitUser) &&
                Objects.equals(prohibitTime, that.prohibitTime) &&
                Objects.equals(reviewUser, that.reviewUser) &&
                Objects.equals(reviewTime, that.reviewTime) &&
                Objects.equals(isDelete, that.isDelete) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createUser, that.createUser) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(updateUser, that.updateUser) &&
                Objects.equals(orgName, that.orgName) &&
                Objects.equals(deptName, that.deptName) &&
                Objects.equals(deptFullName, that.deptFullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, positionCode, positionName, mnemonicCode, belongDeptId, isHr, isResponsiblePosition, effectDate, abateDate, dataState, summary, isFromSHr, createOrgId, useOrgId, prohibitState, prohibitUser, prohibitTime, reviewUser, reviewTime, isDelete, createTime, createUser, updateTime, updateUser, orgName, deptName, deptFullName, hasChildren);
    }

    @Override
    public String toString() {
        return "TAuPosition{" +
                "id=" + id +
                ", positionCode='" + positionCode + '\'' +
                ", positionName='" + positionName + '\'' +
                ", mnemonicCode='" + mnemonicCode + '\'' +
                ", belongDeptId=" + belongDeptId +
                ", isHr='" + isHr + '\'' +
                ", isResponsiblePosition='" + isResponsiblePosition + '\'' +
                ", effectDate=" + effectDate +
                ", abateDate=" + abateDate +
                ", dataState='" + dataState + '\'' +
                ", summary='" + summary + '\'' +
                ", isFromSHr='" + isFromSHr + '\'' +
                ", createOrgId=" + createOrgId +
                ", useOrgId=" + useOrgId +
                ", prohibitState='" + prohibitState + '\'' +
                ", prohibitUser=" + prohibitUser +
                ", prohibitTime=" + prohibitTime +
                ", reviewUser=" + reviewUser +
                ", reviewTime=" + reviewTime +
                ", isDelete='" + isDelete + '\'' +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", orgName='" + orgName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", deptFullName='" + deptFullName + '\'' +
                ", hasChildren=" + hasChildren +
                '}';
    }
}