package com.vosung.auapi.client.dto.requestdto.org;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 组织机构新增入参类
 * @Author 彬
 * @Date 2019/4/22
 */
@Data
public class OrgRequestDto implements Serializable {

    private static final long serialVersionUID = 7683875704619041961L;

    private Integer id;
    @NotNull(message = "组织机构编码不能为空")
    private String orgCode;
    @NotNull(message = "组织机构名称不能为空")
    private String orgName;
    @NotNull(message = "组织机构形态不能为空")
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

    private String zipCode;

}
