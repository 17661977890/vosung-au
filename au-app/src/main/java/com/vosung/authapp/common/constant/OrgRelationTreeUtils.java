package com.vosung.authapp.common.constant;

import com.vosung.auapi.client.dto.responsedto.deptgroup.DeptGroupTreeDto;
import com.vosung.auapi.client.dto.responsedto.orgrelation.OrgRelationResponseDto;

import java.util.ArrayList;
import java.util.List;


/**
 * 组织关系树结构生成工具类
 * @Author 彬
 * @Date 2019/4/26
 */
public class OrgRelationTreeUtils {

    public List<OrgRelationResponseDto> menuCommon;
    public List<Object> list = new ArrayList<Object>();

    public List<Object> treeMenu(List<OrgRelationResponseDto> menu){
        this.menuCommon = menu;
        for (OrgRelationResponseDto treeDto : menu) {
            if(treeDto.getSuperiorOrgId() == 0){
                //先添加父级节点
                setTreeDto(treeDto);
                list.add(treeDto);
            }
        }
        return list;
    }

    public List<OrgRelationResponseDto> menuChild(Integer id){
        List<OrgRelationResponseDto> lists = new ArrayList<OrgRelationResponseDto>();
        for(OrgRelationResponseDto treeDto:menuCommon){
            if(id.equals(treeDto.getSuperiorOrgId())){
                //此父节点有孩子,添加子节点
                lists.add(treeDto);
                //为子节点赋值，并再看子节点石否仍有子节点（孙）
                // 此方法会让menuChild方法称为一个n层嵌套方法,最先进入的最后返回设置父级名
                setTreeDto(treeDto);
            }
        }
        return lists;
    }

    private void setTreeDto(OrgRelationResponseDto treeDto){
        //找子节点
        List<OrgRelationResponseDto> childrens = menuChild(treeDto.getOrgId());
        if(childrens.size()>0){
            treeDto.setHasChildren(true);
        }else{
            treeDto.setHasChildren(false);
        }
        treeDto.setChildren(childrens);
    }
}
