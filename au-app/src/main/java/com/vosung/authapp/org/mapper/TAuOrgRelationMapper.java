package com.vosung.authapp.org.mapper;

import com.vosung.auapi.client.entity.TAuOrgRelation;
import com.vosung.auapi.client.entity.TAuOrgRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuOrgRelationMapper {
    int countByExample(TAuOrgRelationExample example);

    int deleteByExample(TAuOrgRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuOrgRelation record);

    int insertSelective(TAuOrgRelation record);

    List<TAuOrgRelation> selectByExample(TAuOrgRelationExample example);

    TAuOrgRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuOrgRelation record, @Param("example") TAuOrgRelationExample example);

    int updateByExample(@Param("record") TAuOrgRelation record, @Param("example") TAuOrgRelationExample example);

    int updateByPrimaryKeySelective(TAuOrgRelation record);

    int updateByPrimaryKey(TAuOrgRelation record);
}