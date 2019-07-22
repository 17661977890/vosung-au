package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.RoleSubsystemApi;
import com.vosung.auapi.client.dto.requestdto.rolesubsystem.RoleSubsystemRequest;
import com.vosung.auapi.client.dto.requestdto.rolesubsystem.RoleSubsystemRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author 彬
 * @Date 2019/6/24
 */
@Component
public class RoleSubsystemApiHystrix extends BaseHystrix implements RoleSubsystemApi{
    @Override
    public RestResponse saveRoleSubsystem(@RequestBody RestRequest<RoleSubsystemRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getSubsystemPermissionListByRoleId(Integer roleId) {
        return getError();
    }

    @Override
    public RestResponse getAllSubsystemList(Integer roleId) {
        return getError();
    }

    @Override
    public RestResponse getSubsystemPermissionReportByRoleId(@Valid RestRequest<RoleSubsystemRequest> restRequest) {
        return getError();
    }
}
