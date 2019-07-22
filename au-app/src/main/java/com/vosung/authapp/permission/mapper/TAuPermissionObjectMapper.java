package com.vosung.authapp.permission.mapper;

import com.vosung.auapi.client.entity.TAuPermissionObject;
import com.vosung.auapi.client.entity.TAuPermissionObjectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuPermissionObjectMapper {
    int countByExample(TAuPermissionObjectExample example);

    int deleteByExample(TAuPermissionObjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuPermissionObject record);

    int insertSelective(TAuPermissionObject record);

    List<TAuPermissionObject> selectByExample(TAuPermissionObjectExample example);

    TAuPermissionObject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuPermissionObject record, @Param("example") TAuPermissionObjectExample example);

    int updateByExample(@Param("record") TAuPermissionObject record, @Param("example") TAuPermissionObjectExample example);

    int updateByPrimaryKeySelective(TAuPermissionObject record);

    int updateByPrimaryKey(TAuPermissionObject record);
}