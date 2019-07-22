package com.vosung.authapp.permission.mapper;

import com.vosung.auapi.client.entity.TAuPermissionGroup;
import com.vosung.auapi.client.entity.TAuPermissionGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuPermissionGroupMapper {
    int countByExample(TAuPermissionGroupExample example);

    int deleteByExample(TAuPermissionGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuPermissionGroup record);

    int insertSelective(TAuPermissionGroup record);

    List<TAuPermissionGroup> selectByExample(TAuPermissionGroupExample example);

    TAuPermissionGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuPermissionGroup record, @Param("example") TAuPermissionGroupExample example);

    int updateByExample(@Param("record") TAuPermissionGroup record, @Param("example") TAuPermissionGroupExample example);

    int updateByPrimaryKeySelective(TAuPermissionGroup record);

    int updateByPrimaryKey(TAuPermissionGroup record);
}