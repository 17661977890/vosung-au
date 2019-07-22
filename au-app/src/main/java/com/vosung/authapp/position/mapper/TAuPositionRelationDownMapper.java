package com.vosung.authapp.position.mapper;

import com.vosung.auapi.client.entity.TAuPosition;
import com.vosung.auapi.client.entity.TAuPositionRelationDown;
import com.vosung.auapi.client.entity.TAuPositionRelationDownExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuPositionRelationDownMapper {
    int countByExample(TAuPositionRelationDownExample example);

    int deleteByExample(TAuPositionRelationDownExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuPositionRelationDown record);

    int insertSelective(TAuPositionRelationDown record);

    List<TAuPositionRelationDown> selectByExample(TAuPositionRelationDownExample example);

    TAuPositionRelationDown selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuPositionRelationDown record, @Param("example") TAuPositionRelationDownExample example);

    int updateByExample(@Param("record") TAuPositionRelationDown record, @Param("example") TAuPositionRelationDownExample example);

    int updateByPrimaryKeySelective(TAuPositionRelationDown record);

    int updateByPrimaryKey(TAuPositionRelationDown record);


}