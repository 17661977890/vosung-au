package com.vosung.authapp.permission.mapper;

import com.vosung.auapi.client.dto.responsedto.common.BoVo;
import com.vosung.auapi.client.entity.TAuPermissionObjectItem;
import com.vosung.auapi.client.entity.TAuPermissionObjectItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuPermissionObjectItemMapper {
    int countByExample(TAuPermissionObjectItemExample example);

    int deleteByExample(TAuPermissionObjectItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuPermissionObjectItem record);

    int insertSelective(TAuPermissionObjectItem record);

    List<TAuPermissionObjectItem> selectByExample(TAuPermissionObjectItemExample example);

    TAuPermissionObjectItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuPermissionObjectItem record, @Param("example") TAuPermissionObjectItemExample example);

    int updateByExample(@Param("record") TAuPermissionObjectItem record, @Param("example") TAuPermissionObjectItemExample example);

    int updateByPrimaryKeySelective(TAuPermissionObjectItem record);

    int updateByPrimaryKey(TAuPermissionObjectItem record);

    /**
     * 根据权限对象id 查 权限对象 权限项id 权限组code
     * @param id
     * @return
     */
    List<BoVo> getPermissionObjectItemList(Integer id);
}