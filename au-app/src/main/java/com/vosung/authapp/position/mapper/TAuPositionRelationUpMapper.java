package com.vosung.authapp.position.mapper;

import com.vosung.auapi.client.entity.TAuPositionRelationUp;
import com.vosung.auapi.client.entity.TAuPositionRelationUpExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuPositionRelationUpMapper {
    int countByExample(TAuPositionRelationUpExample example);

    int deleteByExample(TAuPositionRelationUpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuPositionRelationUp record);

    int insertSelective(TAuPositionRelationUp record);

    List<TAuPositionRelationUp> selectByExample(TAuPositionRelationUpExample example);

    TAuPositionRelationUp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuPositionRelationUp record, @Param("example") TAuPositionRelationUpExample example);

    int updateByExample(@Param("record") TAuPositionRelationUp record, @Param("example") TAuPositionRelationUpExample example);

    int updateByPrimaryKeySelective(TAuPositionRelationUp record);

    int updateByPrimaryKey(TAuPositionRelationUp record);

    List<Integer> selectBySuperiorPositionId(@Param("superiorPositionId") Integer id);

    Integer updateSuperIdByPositionId(@Param("positionId") Integer positionId, @Param("superId") Integer superId);
}