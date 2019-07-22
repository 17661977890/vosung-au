package com.vosung.authapp.role.mapper;

import com.vosung.auapi.client.entity.TAuRole;
import com.vosung.auapi.client.entity.TAuRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuRoleMapper {
    int countByExample(TAuRoleExample example);

    int deleteByExample(TAuRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuRole record);

    int insertSelective(TAuRole record);

    List<TAuRole> selectByExample(TAuRoleExample example);

    TAuRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuRole record, @Param("example") TAuRoleExample example);

    int updateByExample(@Param("record") TAuRole record, @Param("example") TAuRoleExample example);

    int updateByPrimaryKeySelective(TAuRole record);

    int updateByPrimaryKey(TAuRole record);
}