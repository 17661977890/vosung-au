package com.vosung.auapi.client.dto.requestdto.orguserrole;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 用户分配角色入参类
 * @Author 彬
 * @Date 2019/5/5
 */
@Data
public class UserOrgRoleRequestDto implements Serializable{
    private static final long serialVersionUID = -5614773598117099887L;
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userId;
    /**
     * 组织角色对应关系list
     */
    @NotNull(message = "组织角色对应关系集合可以为空，不能为null")
    private List<OrgRoleDto> orgRoleDtoList;
}
