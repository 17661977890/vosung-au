package com.vosung.authapp.org.service;

import com.vosung.auapi.client.dto.requestdto.orguserrole.GetUserListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orguserrole.GetUserRoleListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orguserrole.OrgUserRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;

/**
 * 组织用户维护业务层
 * @Author 彬
 * @Date 2019/4/30
 */
public interface OrgUserRoleService {

    /**
     * 查询组织下的用户角色列表(用户信息中含有角色列表)
     * @param getOrgUserRequestDto
     * @return
     */
    RestResponse getUserHaveRoleListByOrgId(GetUserRoleListRequestDto getOrgUserRequestDto);

    /**
     * 根据组织和用户id获取角色列表
     * @param getUserRoleListRequestDto
     * @return
     */
    RestResponse getRoleListByOrgUserId(GetUserRoleListRequestDto getUserRoleListRequestDto);

    /**
     * 用户组织维护保存
     * @param orgUserRequestDto
     * @return
     */
    RestResponse saveOrgUserRole(OrgUserRequestDto orgUserRequestDto);

    /**
     * 查询用户（授权处接口）
     * @param restRequest
     * @return
     */
    RestResponse getUserListBy(RestRequest<GetUserListRequestDto> restRequest);


}
