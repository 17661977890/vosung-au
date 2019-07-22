package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.OrgUserRoleApi;
import com.vosung.auapi.client.dto.requestdto.orguserrole.GetUserListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orguserrole.GetUserRoleListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orguserrole.OrgUserRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 组织用户维护熔断处理
 * @Author 彬
 * @Date 2019/4/30
 */
@Component
public class OrgUserRoleHystrix extends BaseHystrix implements OrgUserRoleApi{

    @Override
    public RestResponse getUserRoleListByOrgId(@RequestBody  RestRequest<GetUserRoleListRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getRoleListByOrgUserId(@RequestBody RestRequest<GetUserRoleListRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse saveOrgUserRole(@RequestBody RestRequest<OrgUserRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getUserListBy(@Valid RestRequest<GetUserListRequestDto> restRequest) {
        return getError();
    }
}
