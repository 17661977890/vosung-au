package com.vosung.auapi.client.dto.requestdto.orguserrole;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 组织用户维护保存入参
 * @Author 彬
 * @Date 2019/5/11
 */
@Data
public class OrgUserRequestDto implements Serializable {
    private static final long serialVersionUID = 3679031395243405115L;
    /**
     * 组织id
     */
    private Integer orgId;
    /**
     * 用户对应角色关系list
     */
    private List<UserRoleDto> userRoleDtoList;
}
