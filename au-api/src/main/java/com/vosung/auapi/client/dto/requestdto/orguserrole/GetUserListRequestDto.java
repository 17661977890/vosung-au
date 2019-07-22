package com.vosung.auapi.client.dto.requestdto.orguserrole;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询用户（业务对象子系统授权处的接口入参）
 * @Author 彬
 * @Date 2019/7/1
 */
@Data
public class GetUserListRequestDto implements Serializable {
    private static final long serialVersionUID = -6548848580222569901L;
    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空")
    private Integer roleId;
    /**
     * 组织机构id
     */
    private Integer orgId;
}
