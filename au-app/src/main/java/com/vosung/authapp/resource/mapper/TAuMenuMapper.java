package com.vosung.authapp.resource.mapper;

import com.vosung.auapi.client.entity.TAuMenu;
import com.vosung.auapi.client.entity.TAuMenuExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuMenuMapper {
    int countByExample(TAuMenuExample example);

    int deleteByExample(TAuMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuMenu record);

    int insertSelective(TAuMenu record);

    List<TAuMenu> selectByExample(TAuMenuExample example);

    TAuMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuMenu record, @Param("example") TAuMenuExample example);

    int updateByExample(@Param("record") TAuMenu record, @Param("example") TAuMenuExample example);

    int updateByPrimaryKeySelective(TAuMenu record);

    int updateByPrimaryKey(TAuMenu record);
}