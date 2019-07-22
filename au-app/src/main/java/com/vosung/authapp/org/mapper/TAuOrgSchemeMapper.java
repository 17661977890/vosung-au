package com.vosung.authapp.org.mapper;

import com.vosung.auapi.client.entity.TAuOrgScheme;
import com.vosung.auapi.client.entity.TAuOrgSchemeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuOrgSchemeMapper {
    int countByExample(TAuOrgSchemeExample example);

    int deleteByExample(TAuOrgSchemeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuOrgScheme record);

    int insertSelective(TAuOrgScheme record);

    List<TAuOrgScheme> selectByExample(TAuOrgSchemeExample example);

    TAuOrgScheme selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuOrgScheme record, @Param("example") TAuOrgSchemeExample example);

    int updateByExample(@Param("record") TAuOrgScheme record, @Param("example") TAuOrgSchemeExample example);

    int updateByPrimaryKeySelective(TAuOrgScheme record);

    int updateByPrimaryKey(TAuOrgScheme record);
}