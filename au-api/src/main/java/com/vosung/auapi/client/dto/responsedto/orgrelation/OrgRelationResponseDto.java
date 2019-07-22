package com.vosung.auapi.client.dto.responsedto.orgrelation;

import lombok.Data;

import java.io.Serializable;

/**
 * 组织关系
 * @Author 彬
 * @Date 2019/4/28
 */
@Data
public class OrgRelationResponseDto extends TreeDto<OrgRelationResponseDto> implements Serializable{

    private static final long serialVersionUID = 8479061383009615290L;
    private Integer id;

    private Integer orgId;

    private String orgName;
    /**
     * 上级组织
     */
    private Integer superiorOrgId;
    /**
     * 方案id
     */
    private Integer schemeId;
}
