package com.vosung.authapp.common.constant;

import com.vosung.auapi.client.dto.responsedto.deptgroup.DeptGroupTreeDto;

import java.util.ArrayList;
import java.util.List;


/**
 * 部门组树结构生成工具类
 * @Author 彬
 * @Date 2019/4/26
 */
public class DeptGroupTreeUtils {

    public List<DeptGroupTreeDto> menuCommon;
    public List<Object> list = new ArrayList<Object>();

    public List<Object> treeMenu(List<DeptGroupTreeDto> menu){
        this.menuCommon = menu;
        for (DeptGroupTreeDto treeDto : menu) {
            if(treeDto.getParentGroupId() == null){
                //先添加父级节点
                setTreeDto(treeDto);
                list.add(treeDto);
            }
        }
        return list;
    }

    public List<DeptGroupTreeDto> menuChild(Integer id, String parentName){
        List<DeptGroupTreeDto> lists = new ArrayList<DeptGroupTreeDto>();
        for(DeptGroupTreeDto treeDto:menuCommon){
            if(id.equals(treeDto.getParentGroupId())){
                //此父节点有孩子,添加子节点
                lists.add(treeDto);
                //为子节点赋值，并再看子节点石否仍有子节点（孙）
                // 此方法会让menuChild方法称为一个n层嵌套方法,最先进入的最后返回设置父级名
                setTreeDto(treeDto);
                treeDto.setParentGroupName(parentName);

            }
        }
        return lists;
    }

    private void setTreeDto(DeptGroupTreeDto treeDto){
        //找子节点
        List<DeptGroupTreeDto> childrens = menuChild(treeDto.getId(),treeDto.getDeptGroupName());
        if(childrens.size()>0){
            treeDto.setHasChildrens(true);
        }else{
            treeDto.setHasChildrens(false);
        }
        treeDto.setChildren(childrens);
    }
}
