package com.vosung.authapp.sysdata.mapper;

import com.vosung.auapi.client.entity.TSysDict;
import com.vosung.auapi.client.entity.TSysDictExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysDictMapper {
    int countByExample(TSysDictExample example);

    int deleteByExample(TSysDictExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysDict record);

    int insertSelective(TSysDict record);

    List<TSysDict> selectByExample(TSysDictExample example);

    TSysDict selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysDict record, @Param("example") TSysDictExample example);

    int updateByExample(@Param("record") TSysDict record, @Param("example") TSysDictExample example);

    int updateByPrimaryKeySelective(TSysDict record);

    int updateByPrimaryKey(TSysDict record);
}