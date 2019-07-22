package com.vosung.authapp.dept.mapper;

import com.vosung.auapi.client.entity.TAuDeptGroup;

import java.util.List;

import com.vosung.auapi.client.entity.TAuDeptGroupExample;
import org.apache.ibatis.annotations.Param;

public interface TAuDeptGroupMapper {
    int countByExample(TAuDeptGroupExample example);

    int deleteByExample(TAuDeptGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuDeptGroup record);

    int insertSelective(TAuDeptGroup record);

    List<TAuDeptGroup> selectByExample(TAuDeptGroupExample example);

    TAuDeptGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuDeptGroup record, @Param("example") TAuDeptGroupExample example);

    int updateByExample(@Param("record") TAuDeptGroup record, @Param("example") TAuDeptGroupExample example);

    int updateByPrimaryKeySelective(TAuDeptGroup record);

    int updateByPrimaryKey(TAuDeptGroup record);
}