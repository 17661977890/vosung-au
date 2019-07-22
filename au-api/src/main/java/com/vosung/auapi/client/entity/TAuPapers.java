package com.vosung.auapi.client.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 员工证件
 */
@ToString
@Table(name = "T_AU_PAPERS")
public class TAuPapers implements Serializable{
    private static final long serialVersionUID = 9036920254191230337L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer employeeId;

    private String country;

    private String papersType;

    private String papersNo;

    private String isMainPapers;

    private String isEffect;

    private Date effectiveDate;

    private Date expiryDate;

    private String summary;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getPapersType() {
        return papersType;
    }

    public void setPapersType(String papersType) {
        this.papersType = papersType == null ? null : papersType.trim();
    }

    public String getPapersNo() {
        return papersNo;
    }

    public void setPapersNo(String papersNo) {
        this.papersNo = papersNo == null ? null : papersNo.trim();
    }

    public String getIsMainPapers() {
        return isMainPapers;
    }

    public void setIsMainPapers(String isMainPapers) {
        this.isMainPapers = isMainPapers == null ? null : isMainPapers.trim();
    }

    public String getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(String isEffect) {
        this.isEffect = isEffect == null ? null : isEffect.trim();
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }
}