package com.vosung.authapp.role.mapper;

import com.vosung.auapi.client.entity.TAuSubsystemPermission;
import com.vosung.auapi.client.entity.TAuSubsystemPermissionExample;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuSubsystemPermissionMapper {
    int countByExample(TAuSubsystemPermissionExample example);

    int deleteByExample(TAuSubsystemPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuSubsystemPermission record);

    int insertSelective(TAuSubsystemPermission record);

    List<TAuSubsystemPermission> selectByExample(TAuSubsystemPermissionExample example);

    TAuSubsystemPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuSubsystemPermission record, @Param("example") TAuSubsystemPermissionExample example);

    int updateByExample(@Param("record") TAuSubsystemPermission record, @Param("example") TAuSubsystemPermissionExample example);

    int updateByPrimaryKeySelective(TAuSubsystemPermission record);

    int updateByPrimaryKey(TAuSubsystemPermission record);

    List<TAuSubsystemPermission> getSubsystemPermission(@Param("list") List<Integer> subsystemIds,@Param("roleId") Integer roleId);
}