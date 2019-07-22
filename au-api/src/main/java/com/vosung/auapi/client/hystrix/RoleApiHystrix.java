package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.RoleApi;
import com.vosung.auapi.client.dto.requestdto.role.RoleListRequestDto;
import com.vosung.auapi.client.dto.requestdto.role.RoleRequestDto;
import com.vosung.auapi.client.dto.requestdto.role.UpdateRoleStatusRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 角色api熔断
 * @Author 彬
 * @Date 2019/5/5
 */
@Component
public class RoleApiHystrix extends BaseHystrix implements RoleApi{
    @Override
    public RestResponse saveRole(@RequestBody @Valid RestRequest<RoleRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getRoleInfoById(@RequestParam("id") Integer id) {
        return getError();
    }

    @Override
    public RestResponse getRoleList(RestRequest<RoleListRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse deleteRole(@RequestBody @Valid RestRequest<UpdateRoleStatusRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse forbiddenRole(@RequestBody @Valid RestRequest<UpdateRoleStatusRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse unForbiddenRole(@RequestBody @Valid RestRequest<UpdateRoleStatusRequestDto> restRequest) {
        return getError();
    }

}
