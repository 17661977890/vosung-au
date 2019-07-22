package com.vosung.authapp.role.mapper;

import com.vosung.auapi.client.entity.TAuRoleResource;
import com.vosung.auapi.client.entity.TAuRoleResourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuRoleResourceMapper {
    int countByExample(TAuRoleResourceExample example);

    int deleteByExample(TAuRoleResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuRoleResource record);

    int insertSelective(TAuRoleResource record);

    List<TAuRoleResource> selectByExample(TAuRoleResourceExample example);

    TAuRoleResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuRoleResource record, @Param("example") TAuRoleResourceExample example);

    int updateByExample(@Param("record") TAuRoleResource record, @Param("example") TAuRoleResourceExample example);

    int updateByPrimaryKeySelective(TAuRoleResource record);

    int updateByPrimaryKey(TAuRoleResource record);

    /**
     * 根据角色id集合查询对应菜单lsit
     * @param roleIds
     * @return
     */
    List<TAuRoleResource> getMenuResourceByRoleIds(List<Integer> roleIds);
}