package com.vosung.auapi.client.dto.responsedto.menu;

import com.vosung.auapi.client.restparam.FiledMessage;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单列表
 * @Author 彬
 * @Date 2019/5/29
 */
@Data
public class MenuResponseDto implements Serializable {
    private static final long serialVersionUID = 6169978517732697187L;

    private Integer id;
    @FiledMessage(name = "菜单编码")
    private String menuCode;
    @FiledMessage(name = "菜单名称")
    private String menuName;

    private String menuIcon;

    private Integer parentId;

    private String parentName;
    @FiledMessage(name = "菜单排序号")
    private Integer sort;
    @FiledMessage(name = "菜单等级")
    private Integer level;
    @FiledMessage(name = "菜单路径")
    private String menuPath;
    @FiledMessage(name = "页面组件")
    private String menuComponent;
    @FiledMessage(name = "描述")
    private String summary;

    private String dataState;
    @FiledMessage(name = "数据状态")
    private String dataStateName;
    @FiledMessage(name = "禁用状态")
    private String prohibitState;
}
