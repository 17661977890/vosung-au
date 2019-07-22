package com.vosung.authapp.dept.mapper;

import com.vosung.auapi.client.entity.TAuDepartemnt;
import java.util.List;

import com.vosung.auapi.client.entity.TAuDepartemntExample;
import org.apache.ibatis.annotations.Param;

public interface TAuDepartemntMapper {
    int countByExample(TAuDepartemntExample example);

    int deleteByExample(TAuDepartemntExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuDepartemnt record);

    int insertSelective(TAuDepartemnt record);

    List<TAuDepartemnt> selectByExample(TAuDepartemntExample example);

    TAuDepartemnt selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuDepartemnt record, @Param("example") TAuDepartemntExample example);

    int updateByExample(@Param("record") TAuDepartemnt record, @Param("example") TAuDepartemntExample example);

    int updateByPrimaryKeySelective(TAuDepartemnt record);

    int updateByPrimaryKey(TAuDepartemnt record);
}