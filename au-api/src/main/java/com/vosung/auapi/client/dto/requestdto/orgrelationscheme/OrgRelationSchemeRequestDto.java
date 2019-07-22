package com.vosung.auapi.client.dto.requestdto.orgrelationscheme;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 组织机构隶属关系方案 保存入参类
 * @Author 彬
 * @Date 2019/4/26
 */
@Data
public class OrgRelationSchemeRequestDto implements Serializable{
    private static final long serialVersionUID = -2723173200402538349L;

    private Integer id;
    /**
     * 方案编码
     */
    @NotNull(message = "隶属方案编码不能为空")
    private String schemeCode;
    /**
     * 方案名称
     */
    @NotNull(message = "隶属方案名称不能为空")
    private String schemeName;
    /**
     * 职能类型
     */
    @NotNull(message = "职能类型不能为空")
    private String orgFunctionType;
    /**
     * 顶层组织id
     */
    @NotNull(message = "顶层组织id不能为空")
    private Integer topOrgId;
    /**
     * 是否默认方案
     */
    private String isDefaultScheme;
    /**
     * 生效日期
     */
    @NotNull(message = "隶属方案生效日期不能为空")
    private Date enableDate;
    /**
     * 失效日期
     */
    @NotNull(message = "隶属方案失效日期不能为空")
    private Date expiryDate;
    /**
     * 描述
     */
    private String summary;
    /**
     * 隶属关系表主键id 串（，号分隔）
     */
    private String relationIds;
    /**
     * 组织关系树形结构json串
     */
    private JSONObject orgRelationJsonTree;
}
