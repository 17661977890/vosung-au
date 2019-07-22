package com.vosung.auapi.client.dto.requestdto.menu;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 菜单保存入参
 * @Author 彬
 * @Date 2019/5/16
 */
@Data
public class MenuRequestDto implements Serializable {
    private static final long serialVersionUID = -7393118703822636857L;

    private Integer id;
    /**
     * 菜单编码
     */
    @NotNull(message = "菜单编码不能为空")
    private String menuCode;
    /**
     * 菜单名称
     */
    @NotNull(message = "菜单名不能为空")
    private String menuName;
    /**
     * 菜单icon
     */
    private String menuIcon;
    /**
     * 父级菜单id（顶级菜单为0）
     */
    private Integer parentId;
    /**
     * 排序号
     */
    @NotNull(message = "排序号不能为空")
    private Integer sort;
    /**
     * 菜单等级
     */
    @NotNull(message = "菜单等级不能为空")
    private Integer level;
    /**
     * 菜单路径
     */
    @NotNull(message = "菜单路径不能为空")
    private String menuPath;
    /**
     * 页面组件
     */
    private String menuComponent;
    /**
     * 描述
     */
    private String summary;
    @NotNull(message = "是否也为可点击按钮不能为空")
    private String isMenuAndButton;
}
