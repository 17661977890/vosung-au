package com.vosung.authapp.user.mapper;

import com.vosung.auapi.client.entity.TAuUserRole;
import com.vosung.auapi.client.entity.TAuUserRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuUserRoleMapper {
    int countByExample(TAuUserRoleExample example);

    int deleteByExample(TAuUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuUserRole record);

    int insertSelective(TAuUserRole record);

    List<TAuUserRole> selectByExample(TAuUserRoleExample example);

    TAuUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuUserRole record, @Param("example") TAuUserRoleExample example);

    int updateByExample(@Param("record") TAuUserRole record, @Param("example") TAuUserRoleExample example);

    int updateByPrimaryKeySelective(TAuUserRole record);

    int updateByPrimaryKey(TAuUserRole record);
}