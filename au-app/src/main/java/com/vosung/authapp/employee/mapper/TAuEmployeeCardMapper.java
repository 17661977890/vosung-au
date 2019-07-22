package com.vosung.authapp.employee.mapper;

import com.vosung.auapi.client.entity.TAuEmployeeCard;
import com.vosung.auapi.client.entity.TAuEmployeeCardExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuEmployeeCardMapper {
    int countByExample(TAuEmployeeCardExample example);

    int deleteByExample(TAuEmployeeCardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuEmployeeCard record);

    int insertSelective(TAuEmployeeCard record);

    List<TAuEmployeeCard> selectByExample(TAuEmployeeCardExample example);

    TAuEmployeeCard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuEmployeeCard record, @Param("example") TAuEmployeeCardExample example);

    int updateByExample(@Param("record") TAuEmployeeCard record, @Param("example") TAuEmployeeCardExample example);

    int updateByPrimaryKeySelective(TAuEmployeeCard record);

    int updateByPrimaryKey(TAuEmployeeCard record);
}