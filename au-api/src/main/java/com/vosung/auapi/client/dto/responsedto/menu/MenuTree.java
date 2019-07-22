package com.vosung.auapi.client.dto.responsedto.menu;

import com.vosung.auapi.client.dto.responsedto.roleresource.ButtonResourceResponseDto;
import com.vosung.auapi.client.entity.TAuButton;
import com.vosung.auapi.client.entity.TAuMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 菜单树出参类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuTree extends TAuMenu {
    /**
     * 菜单下按钮
     */
    private List<ButtonResourceResponseDto> buttonList;

    /**
     * 扩展属性 父级名称
     **/
    private String parentName;
    /**
     * 扩展属性 子级列表
     **/
    private List<MenuTree> children;
    /**
     *  是否找到当前父节点，MenuTreeUtil类遍历节点的时候用，保证所有父节点只会被查找一次，不会重复查询
     */
    private boolean findParentNode;

}