package com.vosung.authapp.position.mapper;

import com.vosung.auapi.client.entity.TAuPositionRole;
import com.vosung.auapi.client.entity.TAuPositionRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuPositionRoleMapper {
    int countByExample(TAuPositionRoleExample example);

    int deleteByExample(TAuPositionRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuPositionRole record);

    int insertSelective(TAuPositionRole record);

    List<TAuPositionRole> selectByExample(TAuPositionRoleExample example);

    TAuPositionRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuPositionRole record, @Param("example") TAuPositionRoleExample example);

    int updateByExample(@Param("record") TAuPositionRole record, @Param("example") TAuPositionRoleExample example);

    int updateByPrimaryKeySelective(TAuPositionRole record);

    int updateByPrimaryKey(TAuPositionRole record);
}