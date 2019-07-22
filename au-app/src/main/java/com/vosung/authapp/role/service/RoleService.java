package com.vosung.authapp.role.service;

import com.vosung.auapi.client.dto.requestdto.role.RoleListRequestDto;
import com.vosung.auapi.client.dto.requestdto.role.RoleRequestDto;
import com.vosung.auapi.client.dto.requestdto.role.UpdateRoleStatusRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 角色管理业务层
 * @Author 彬
 * @Date 2019/5/5
 */
public interface RoleService {

    /**
     * 保存角色（新增/修改）
     * @param roleRequestDto
     * @return
     */
    RestResponse saveRole(RoleRequestDto roleRequestDto);

    /**
     * 根据角色id查看角色详情
     * @param id
     * @return
     */
    RestResponse getRoleInfoById(Integer id);

    /**
     * 角色列表查询
     * @param restRequest
     * @return
     */
    RestResponse getRoleList(RestRequest<RoleListRequestDto> restRequest);

    /**
     * 修改角色状态（删除）
     * @param updateRoleStatusRequestDto
     * @return
     */
    RestResponse deleteRole(UpdateRoleStatusRequestDto updateRoleStatusRequestDto);

    /**
     * 修改角色状态（禁用）
     * @param updateRoleStatusRequestDto
     * @return
     */
    RestResponse forbiddenRole(UpdateRoleStatusRequestDto updateRoleStatusRequestDto);

    /**
     * 修改角色状态（反禁用）
     * @param updateRoleStatusRequestDto
     * @return
     */
    RestResponse unForbiddenRole(UpdateRoleStatusRequestDto updateRoleStatusRequestDto);
}
