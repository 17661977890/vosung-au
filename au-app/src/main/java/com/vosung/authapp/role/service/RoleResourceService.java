package com.vosung.authapp.role.service;

import com.vosung.auapi.client.dto.requestdto.roleresource.ButtonResourceRequestDto;
import com.vosung.auapi.client.dto.requestdto.roleresource.RoleResourceRequestDto;
import com.vosung.auapi.client.restparam.RestResponse;

/**
 * 角色资源授权业务层
 * @Author 彬
 * @Date 2019/5/16
 */
public interface RoleResourceService {

    /**
     * 批量保存  角色资源授权信息
     * @param roleResourceRequestDto
     * @return
     */
    RestResponse saveRoleResource(RoleResourceRequestDto roleResourceRequestDto);

    /**
     * 获取某角色下的有效有权菜单和按钮（权限平台，菜单授权出用）
     * @param roleId
     * @return
     */
    RestResponse getMenuResourceListByRoleId(Integer roleId);

    /**
     * 查询用户拥有的菜单（一个用户多个角色，查询拥有的菜单 ksf平台用--树）
     * @param roleIds
     * @return
     */
    RestResponse getMenuTreeByRoleIds(String roleIds);

    /**
     * 根据角色id 菜单id 查询按钮列表（无角色id：有效 有角色：有权会标识----ksf用）
     * @param buttonResourceRequestDto
     * @return
     */
    RestResponse getButtonResourceListByRoleIdMenuId(ButtonResourceRequestDto buttonResourceRequestDto);

}
