package com.vosung.authapp.common.constant;
import com.vosung.auapi.client.dto.responsedto.menu.MenuTree;

import java.util.ArrayList;
import java.util.List;


/**
 * 菜单树结构生成工具类
 * @Author 彬
 * @Date 2019/4/26
 */
public class MenuTreeUtils {

    public List<MenuTree> menuCommon;
    public List<Object> list = new ArrayList<Object>();

    public List<Object> treeMenu(List<MenuTree> menu){
        this.menuCommon = menu;
        for (MenuTree treeDto : menu) {
            if(treeDto.getParentId() == 0){
                //先添加父级节点
                setTreeDto(treeDto);
                list.add(treeDto);
            }
        }
        return list;
    }

    public List<MenuTree> menuChild(Integer id, String parentName){
        List<MenuTree> lists = new ArrayList<MenuTree>();
        for(MenuTree treeDto:menuCommon){
            if(id.equals(treeDto.getParentId())){
                //此父节点有孩子,添加子节点
                lists.add(treeDto);
                //为子节点赋值，并再看子节点石否仍有子节点（孙）
                // 此方法会让menuChild方法称为一个n层嵌套方法,最先进入的最后返回设置父级名
                setTreeDto(treeDto);
                treeDto.setParentName(parentName);

            }
        }
        return lists;
    }

    private void setTreeDto(MenuTree treeDto){
        //找子节点
        List<MenuTree> childrens = menuChild(treeDto.getId(),treeDto.getMenuName());
        if(childrens.size()>0){
            treeDto.setFindParentNode(true);
        }else{
            treeDto.setFindParentNode(false);
        }
        treeDto.setChildren(childrens);
    }
}
