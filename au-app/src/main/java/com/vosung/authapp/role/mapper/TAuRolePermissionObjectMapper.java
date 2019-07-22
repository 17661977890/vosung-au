package com.vosung.authapp.role.mapper;

import com.vosung.auapi.client.dto.responsedto.rolepermission.BuObjPermissionItemDto;
import com.vosung.auapi.client.dto.responsedto.rolepermission.RoleBuObjListDto;
import com.vosung.auapi.client.dto.responsedto.rolepermission.UserPermissionDto;
import com.vosung.auapi.client.entity.TAuRolePermissionObject;
import com.vosung.auapi.client.entity.TAuRolePermissionObjectExample;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuRolePermissionObjectMapper {
    int countByExample(TAuRolePermissionObjectExample example);

    int deleteByExample(TAuRolePermissionObjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuRolePermissionObject record);

    int insertSelective(TAuRolePermissionObject record);

    List<TAuRolePermissionObject> selectByExample(TAuRolePermissionObjectExample example);

    TAuRolePermissionObject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuRolePermissionObject record, @Param("example") TAuRolePermissionObjectExample example);

    int updateByExample(@Param("record") TAuRolePermissionObject record, @Param("example") TAuRolePermissionObjectExample example);

    int updateByPrimaryKeySelective(TAuRolePermissionObject record);

    int updateByPrimaryKey(TAuRolePermissionObject record);

    /**
     * 查用户权限（多角色）
     * @param roleIds
     * @return
     */
    List<UserPermissionDto> getUserPermissionDtoList(List<Integer> roleIds);

    /**
     * 查角色权限
     * @param roleId
     * @return
     */
    List<RoleBuObjListDto> getRoleBuObjListDtoList(Integer roleId);

    /**
     * 根据角色和业务对象-----关联查询授权的权限项信息
     * @param roleId
     * @param businessObjectId
     * @return
     */
    List<BuObjPermissionItemDto> getBuObjPermissionByRoleAndBo(@Param("roleId") Integer roleId,@Param("businessObjectId") Integer businessObjectId);
}