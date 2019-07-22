package com.vosung.auapi.client.dto.requestdto.roleresource;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色资源授权
 * @Author 彬
 * @Date 2019/5/16
 */
@Data
public class RoleResourceRequestDto implements Serializable{
    private static final long serialVersionUID = 7132839994106594474L;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 菜单按钮组合list
     */
    private List<MenuButton> menuButtonList;
}
