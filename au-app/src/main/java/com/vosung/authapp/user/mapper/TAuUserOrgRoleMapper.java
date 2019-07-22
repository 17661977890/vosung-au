package com.vosung.authapp.user.mapper;

import com.vosung.auapi.client.dto.requestdto.orguserrole.GetUserListRequestDto;
import com.vosung.auapi.client.dto.responsedto.orguserrole.UserListDto;
import com.vosung.auapi.client.entity.TAuUserOrgRole;
import com.vosung.auapi.client.entity.TAuUserOrgRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuUserOrgRoleMapper {
    int countByExample(TAuUserOrgRoleExample example);

    int deleteByExample(TAuUserOrgRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuUserOrgRole record);

    int insertSelective(TAuUserOrgRole record);

    List<TAuUserOrgRole> selectByExample(TAuUserOrgRoleExample example);

    TAuUserOrgRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuUserOrgRole record, @Param("example") TAuUserOrgRoleExample example);

    int updateByExample(@Param("record") TAuUserOrgRole record, @Param("example") TAuUserOrgRoleExample example);

    int updateByPrimaryKeySelective(TAuUserOrgRole record);

    int updateByPrimaryKey(TAuUserOrgRole record);

    List<UserListDto> getUserListBy(GetUserListRequestDto getUserListRequestDto);
}