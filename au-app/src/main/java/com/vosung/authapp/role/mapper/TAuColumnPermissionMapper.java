package com.vosung.authapp.role.mapper;

import com.vosung.auapi.client.entity.TAuColumnPermission;
import com.vosung.auapi.client.entity.TAuColumnPermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuColumnPermissionMapper {
    int countByExample(TAuColumnPermissionExample example);

    int deleteByExample(TAuColumnPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuColumnPermission record);

    int insertSelective(TAuColumnPermission record);

    List<TAuColumnPermission> selectByExample(TAuColumnPermissionExample example);

    TAuColumnPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuColumnPermission record, @Param("example") TAuColumnPermissionExample example);

    int updateByExample(@Param("record") TAuColumnPermission record, @Param("example") TAuColumnPermissionExample example);

    int updateByPrimaryKeySelective(TAuColumnPermission record);

    int updateByPrimaryKey(TAuColumnPermission record);
}