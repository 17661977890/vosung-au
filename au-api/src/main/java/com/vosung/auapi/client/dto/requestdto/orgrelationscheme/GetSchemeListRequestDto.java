package com.vosung.auapi.client.dto.requestdto.orgrelationscheme;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询方案入参
 * @Author 彬
 * @Date 2019/4/28
 */
@Data
public class GetSchemeListRequestDto implements Serializable {
    private static final long serialVersionUID = -7694733978892275088L;

    private Integer id;
    //方案编码
    private String schemeCode;
    //方案名
    private String schemeName;
    //组织职能类型
    private String orgFunctionType;
    //顶层组织
    private Integer topOrgId;
    //是否默认方案
    private String isDefaultScheme;
    //生效日期
    private Date enableDate;
    //失效日期
    private Date expiryDate;
    //描述
    private String summary;
    //数据状态
    private String dataState;
    //禁用状态
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
}
