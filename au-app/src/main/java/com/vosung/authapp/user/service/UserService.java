package com.vosung.authapp.user.service;

import com.vosung.auapi.client.dto.requestdto.orguserrole.UserOrgRoleRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.ResetPasswordRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UpdateUserStatusRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UserListRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UserRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import io.swagger.models.auth.In;

/**
 * 用户管理业务层
 * @Author 彬
 * @Date 2019/4/29
 */
public interface UserService {

    /**
     * 保存用户信息
     * @return
     */
    RestResponse saveUser(UserRequestDto userRequestDto);

    /**
     * 根据用户id查询用户详情
     * @param id
     * @return
     */
    RestResponse getUserInfoById(Integer id);

    /**
     * 获取用户列表（暂时查所有）
     * @return
     */
    RestResponse getUserList(RestRequest<UserListRequestDto>  restRequest);

    /**
     * 根据用户id 反禁用
     * @param requestDto
     * @return
     */
    RestResponse unForbiddenUser(UpdateUserStatusRequestDto requestDto);
    /**
     * 根据用户id 禁用
     * @param requestDto
     * @return
     */
    RestResponse forbiddenUser(UpdateUserStatusRequestDto requestDto);

    /**
     * 分配角色（新增数据）
     * @param userOrgRoleRequestDto
     * @return
     */
    RestResponse addUserOrgRole(UserOrgRoleRequestDto userOrgRoleRequestDto);

    /**
     * 重置密码入参
     * @param resetPasswordRequestDto
     * @return
     */
    RestResponse resetPassword(ResetPasswordRequestDto resetPasswordRequestDto);

    /**
     * 获取用户所属部门
     * @return
     */
    RestResponse getBelongDept();
}
