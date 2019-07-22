package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.RoleBuObjApi;
import com.vosung.auapi.client.dto.requestdto.rolepermission.RoleBuObjRequest;
import com.vosung.auapi.client.dto.requestdto.rolepermission.RoleBuObjRequestDto;
import com.vosung.auapi.client.dto.requestdto.rolepermission.UserPermissionRequest;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @Author 彬
 * @Date 2019/5/28
 */
@Component
public class RoleBuObjApiHystrix extends BaseHystrix implements RoleBuObjApi{

    @Override
    public RestResponse saveRoleBuObj(@RequestBody @Valid RestRequest<RoleBuObjRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getBuObjListByRoleId(@RequestParam("roleId") Integer roleId) {
        return getError();
    }

    @Override
    public RestResponse getAllBuObjList(@RequestParam("roleId")Integer roleId) {
        return getError();
    }

    @Override
    public RestResponse getBuObjPermissionListByRoleId(@RequestBody @Valid RestRequest<RoleBuObjRequest> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getUserPermissionByUserId(@Valid RestRequest<UserPermissionRequest> restRequest) {
        return getError();
    }
}
