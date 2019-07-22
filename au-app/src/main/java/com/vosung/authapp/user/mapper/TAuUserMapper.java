package com.vosung.authapp.user.mapper;

import com.vosung.auapi.client.entity.TAuUser;
import com.vosung.auapi.client.entity.TAuUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuUserMapper {
    int countByExample(TAuUserExample example);

    int deleteByExample(TAuUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuUser record);

    int insertSelective(TAuUser record);

    List<TAuUser> selectByExample(TAuUserExample example);

    TAuUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuUser record, @Param("example") TAuUserExample example);

    int updateByExample(@Param("record") TAuUser record, @Param("example") TAuUserExample example);

    int updateByPrimaryKeySelective(TAuUser record);

    int updateByPrimaryKey(TAuUser record);
}