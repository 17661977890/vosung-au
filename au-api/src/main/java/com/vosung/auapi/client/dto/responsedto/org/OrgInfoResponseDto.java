package com.vosung.auapi.client.dto.responsedto.org;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 组织机构详情出参
 * @Author 彬
 * @Date 2019/6/11
 */
@Data
public class OrgInfoResponseDto implements Serializable {
    private static final long serialVersionUID = -2936307506670147250L;

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

    private String legalPersonName;

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
}
