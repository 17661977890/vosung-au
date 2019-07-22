package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.RoleResourceApi;
import com.vosung.auapi.client.dto.requestdto.roleresource.ButtonResourceRequestDto;
import com.vosung.auapi.client.dto.requestdto.roleresource.RoleResourceRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author 彬
 * @Date 2019/5/16
 */
@Component
public class RoleResourceApiHystrix extends BaseHystrix implements RoleResourceApi{
    @Override
    public RestResponse saveRoleResource(@RequestBody @Valid RestRequest<RoleResourceRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getMenuResourceListByRoleId(Integer roleId) {
        return getError();
    }

    @Override
    public RestResponse getMenuTreeByRoleIds(String roleIds) {
        return getError();
    }

    @Override
    public RestResponse getButtonResourceListByRoleIdMenuId(@RequestBody @Valid RestRequest<ButtonResourceRequestDto> restRequest) {
        return getError();
    }
}
