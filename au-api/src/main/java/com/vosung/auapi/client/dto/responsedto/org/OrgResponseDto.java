package com.vosung.auapi.client.dto.responsedto.org;

import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 组织机构出参类
 * @Author 彬
 * @Date 2019/4/22
 */
@Data
public class OrgResponseDto implements Serializable{

    private static final long serialVersionUID = -2686228664894425193L;

    private Integer id;
    /**
     * 组织机构编码
     */
    @FiledMessage(name = "组织编码")
    private String orgCode;
    /**
     * 组织机构名称
     */
    @FiledMessage(name = "组织名称")
    private String orgName;
    /**
     * 组织机构形态编号
     */
    private String orgFormId;
    /**
     * 组织形态名
     */
    @FiledMessage(name = "组织形态")
    private String orgFormName;

    /**
     * 是否核算组织
     */
    @FiledMessage(name = "是否核算组织")
    private String isAccountancyOrg;
    /**
     * 核算组织类型
     */
    private String accountancyOrgType;
    /**
     * 核算组织类型名称
     */
    @FiledMessage(name = "核算组织类型")
    private String accountancyOrgTypeName;
    /**
     * 是否业务组织
     */
    @FiledMessage(name = "是否业务组织")
    private String isOperationOrg;
    /**
     * 业务组织类型
     */
    private String orgFunctionType;
    /**
     * 所属法人（组织机构编号）
     */
    private Integer legalPerson;
    /**
     * 所属法人名称
     */
    @FiledMessage(name = "所属法人")
    private String legalPersonName;
    /**
     * 数据状态
     */
    private String dataState;
    /**
     * 数据状态名
     */
    @FiledMessage(name = "数据状态")
    private String dataStateName;
    /**
     * （是否）禁用状态
     */
    private String prohibitState;
}
