package com.vosung.auapi.client.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户实体
 */
@Table(name = "T_AU_USER")
public class TAuUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String userType;

    private String name;

    private Integer createOrgId;

    private Integer permitOrgId;

    private Integer belongDeptId;

    private Integer belongStationId;

    private String email;

    private String sex;

    private String phone;

    private Integer contactStaff;

    private String contactStaffType;

    private String summary;

    private String image;

    private Integer liftBanBy;

    private Date liftBanTime;

    private String dataState;

    private String prohibitState;

    private Integer reviewUser;

    private Date reviewTime;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(Integer createOrgId) {
        this.createOrgId = createOrgId;
    }

    public Integer getPermitOrgId() {
        return permitOrgId;
    }

    public void setPermitOrgId(Integer permitOrgId) {
        this.permitOrgId = permitOrgId;
    }

    public Integer getBelongDeptId() {
        return belongDeptId;
    }

    public void setBelongDeptId(Integer belongDeptId) {
        this.belongDeptId = belongDeptId;
    }

    public Integer getBelongStationId() {
        return belongStationId;
    }

    public void setBelongStationId(Integer belongStationId) {
        this.belongStationId = belongStationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getContactStaff() {
        return contactStaff;
    }

    public void setContactStaff(Integer contactStaff) {
        this.contactStaff = contactStaff;
    }

    public String getContactStaffType() {
        return contactStaffType;
    }

    public void setContactStaffType(String contactStaffType) {
        this.contactStaffType = contactStaffType == null ? null : contactStaffType.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getLiftBanBy() {
        return liftBanBy;
    }

    public void setLiftBanBy(Integer liftBanBy) {
        this.liftBanBy = liftBanBy;
    }

    public Date getLiftBanTime() {
        return liftBanTime;
    }

    public void setLiftBanTime(Date liftBanTime) {
        this.liftBanTime = liftBanTime;
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