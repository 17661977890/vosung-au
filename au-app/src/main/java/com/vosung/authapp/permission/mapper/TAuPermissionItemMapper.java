package com.vosung.authapp.permission.mapper;

import com.vosung.auapi.client.entity.TAuPermissionItem;
import com.vosung.auapi.client.entity.TAuPermissionItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuPermissionItemMapper {
    int countByExample(TAuPermissionItemExample example);

    int deleteByExample(TAuPermissionItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuPermissionItem record);

    int insertSelective(TAuPermissionItem record);

    List<TAuPermissionItem> selectByExample(TAuPermissionItemExample example);

    TAuPermissionItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuPermissionItem record, @Param("example") TAuPermissionItemExample example);

    int updateByExample(@Param("record") TAuPermissionItem record, @Param("example") TAuPermissionItemExample example);

    int updateByPrimaryKeySelective(TAuPermissionItem record);

    int updateByPrimaryKey(TAuPermissionItem record);
}