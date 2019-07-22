package com.vosung.auapi.client.dto.responsedto.roleresource;

import lombok.Data;

import java.io.Serializable;

/**
 * 查看角色下有权菜单的按钮list出参
 * @Author 彬
 * @Date 2019/5/23
 */
@Data
public class ButtonResourceResponseDto implements Serializable{
    private static final long serialVersionUID = 6487646730860637625L;
    /**
     * 按钮id
     */
    private Integer id;
    /**
     * 按钮编码
     */
    private String buttonCode;
    /**
     * 按钮名称
     */
    private String buttonName;
    /**
     * 按钮类型
     */
    private String buttonType;
    /**
     * 按钮icon
     */
    private String buttonIcon;
    /**
     * 所属菜单id
     */
    private Integer menuId;
    /***
     * 是否角色拥有（默认未false）
     */
    private Boolean isRoleHas = false;
}
