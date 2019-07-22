package com.vosung.auapi.client.dto.requestdto.org;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 获取组织机构列表入参类
 * @Author 彬
 * @Date 2019/4/22
 */
@Data
public class GetOrgListRequestDto implements Serializable {

    private static final long serialVersionUID = 7683875704619041961L;

    private Integer id;

    private String orgCode;

    private String orgName;

    private String orgFormId;

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

    private String zipCode;

}
