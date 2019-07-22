package com.vosung.auapi.client.dto.requestdto.orgrelation;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 组织机构隶属关系新增入参
 * @Author 彬
 * @Date 2019/4/28
 */
@Data
public class OrgRelationRequestDto implements Serializable{

    private static final long serialVersionUID = 4997951210079847241L;

    private Integer id;
    /**
     * 组织机构id
     */
    @NotNull(message = "组织机构id不能为空")
    private Integer orgId;
    /**
     * 父级id：如果是顶层组织就传0
     */
    @NotNull(message = "组织机构父级id不能为空")
    private Integer superiorOrgId;
}
