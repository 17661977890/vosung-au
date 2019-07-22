package com.vosung.auapi.client.dto.requestdto.role;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 保存角色入参类
 * @Author 彬
 * @Date 2019/5/5
 */
@Data
public class RoleRequestDto implements Serializable{
    private static final long serialVersionUID = 5908380226439723302L;

    private Integer id;
    /**
     * 角色编码
     */
    @NotNull(message = "角色编码不能为空")
    private String roleCode;
    /**
     * 角色名称
     */
    @NotNull(message = "角色名称不能为空")
    private String roleName;
    /**
     * 角色类型(普通角色：COMMON_ROLE | 系统管理员：SYS_ROLE | 供应商协同角色：SUPPLIER_ROLE)
     */
    @NotNull(message = "角色类型不能为空")
    private String roleType;
    /**
     * 角色属性(公有：PUBLIC | 私有：PRIVATE)
     */
    @NotNull(message = "角色属性不能为空")
    private String property;
    /**
     * 所属组织（私有角色才会有此值）
     */
    private Integer useOrgId;
    /**
     * 描述
     */
    private String summary;
}
