package com.vosung.authapp.org.mapper;

import com.vosung.auapi.client.entity.TAuOrganization;
import com.vosung.auapi.client.entity.TAuOrganizationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuOrganizationMapper {
    int countByExample(TAuOrganizationExample example);

    int deleteByExample(TAuOrganizationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuOrganization record);

    int insertSelective(TAuOrganization record);

    List<TAuOrganization> selectByExample(TAuOrganizationExample example);

    TAuOrganization selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuOrganization record, @Param("example") TAuOrganizationExample example);

    int updateByExample(@Param("record") TAuOrganization record, @Param("example") TAuOrganizationExample example);

    int updateByPrimaryKeySelective(TAuOrganization record);

    int updateByPrimaryKey(TAuOrganization record);
}