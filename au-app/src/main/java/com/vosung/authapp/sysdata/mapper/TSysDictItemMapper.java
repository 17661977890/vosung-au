package com.vosung.authapp.sysdata.mapper;

import com.vosung.auapi.client.entity.TSysDictItem;
import com.vosung.auapi.client.entity.TSysDictItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TSysDictItemMapper {
    int countByExample(TSysDictItemExample example);

    int deleteByExample(TSysDictItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysDictItem record);

    int insertSelective(TSysDictItem record);

    List<TSysDictItem> selectByExample(TSysDictItemExample example);

    TSysDictItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysDictItem record, @Param("example") TSysDictItemExample example);

    int updateByExample(@Param("record") TSysDictItem record, @Param("example") TSysDictItemExample example);

    int updateByPrimaryKeySelective(TSysDictItem record);

    int updateByPrimaryKey(TSysDictItem record);
}