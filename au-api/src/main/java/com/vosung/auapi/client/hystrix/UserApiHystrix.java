package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.UserApi;
import com.vosung.auapi.client.dto.requestdto.orguserrole.UserOrgRoleRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.ResetPasswordRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UpdateUserStatusRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UserListRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UserRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @Author 彬
 * @Date 2019/4/29
 */
@Component
public class UserApiHystrix extends BaseHystrix implements UserApi{
    @Override
    public RestResponse saveUser(@RequestBody @Valid RestRequest<UserRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getUserInfoById(@RequestParam("id") Integer id) {
        return getError();
    }

    @Override
    public RestResponse getUserList(@RequestBody RestRequest<UserListRequestDto> restRequest) {
        return getError();
    }


    @Override
    public RestResponse addUserOrgRole(@RequestBody @Valid RestRequest<UserOrgRoleRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse resetPassword(@RequestBody @Valid RestRequest<ResetPasswordRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getBelongDept() {
        return getError();
    }

    @Override
    public RestResponse forbiddenUser(@RequestBody @Valid RestRequest<UpdateUserStatusRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse unForbiddenUser(@RequestBody @Valid RestRequest<UpdateUserStatusRequestDto> restRequest) {
        return getError();
    }
}
