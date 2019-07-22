package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.orguserrole.UserOrgRoleRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.ResetPasswordRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UpdateUserStatusRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UserListRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UserRequestDto;
import com.vosung.auapi.client.hystrix.UserApiHystrix;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 用户管理api
 * @Author 彬
 * @Date 2019/4/29
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = UserApiHystrix.class)
public interface UserApi {

    /**
     * 保存用户（新增/修改）
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/userController/saveUser",method = RequestMethod.POST)
    RestResponse saveUser(@RequestBody @Valid RestRequest<UserRequestDto> restRequest);

    /**
     * 根据用户id查询用户详情
     * @param id
     * @return
     */
    @RequestMapping(value = "api/userController/getUserInfoById",method = RequestMethod.POST)
    RestResponse getUserInfoById(@RequestParam("id") Integer id);

    /**
     * 获取用户列表（暂时查所有）
     * @return
     */
    @RequestMapping(value = "api/userController/getUserList",method = RequestMethod.POST)
    RestResponse getUserList(@RequestBody RestRequest<UserListRequestDto>  restRequest);

    /**
     * 用户分配角色（新增）
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/userController/addUserOrgRole",method = RequestMethod.POST)
    RestResponse addUserOrgRole(@RequestBody @Valid RestRequest<UserOrgRoleRequestDto> restRequest);

    /**
     * 重置密码
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/userController/resetPassword",method = RequestMethod.POST)
    RestResponse resetPassword(@RequestBody @Valid RestRequest<ResetPasswordRequestDto> restRequest);

    /**
     * 展示所属部门
     * @return
     */
    @RequestMapping(value = "api/userController/getBelongDept",method = RequestMethod.POST)
    RestResponse getBelongDept();

    /**
     * 禁用用户
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/userController/forbiddenUser",method = RequestMethod.POST)
    RestResponse forbiddenUser(@RequestBody @Valid RestRequest<UpdateUserStatusRequestDto> restRequest);

    /**
     * 反禁用用户
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/userController/unForbiddenUser",method = RequestMethod.POST)
    RestResponse unForbiddenUser(@RequestBody @Valid RestRequest<UpdateUserStatusRequestDto> restRequest);
}
