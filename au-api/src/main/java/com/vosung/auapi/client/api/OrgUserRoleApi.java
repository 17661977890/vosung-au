package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.orguserrole.GetUserListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orguserrole.GetUserRoleListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orguserrole.OrgUserRequestDto;
import com.vosung.auapi.client.hystrix.OrgUserRoleHystrix;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * 组织用户维护api
 * @Author 彬
 * @Date 2019/4/30
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = OrgUserRoleHystrix.class)
public interface OrgUserRoleApi {

    /**
     * 根据组织id查询用户角色列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/OrgUserRoleController/getUserRoleListByOrgId",method = RequestMethod.POST)
    RestResponse getUserRoleListByOrgId(@RequestBody RestRequest<GetUserRoleListRequestDto> restRequest);

    /**
     * 根据用户组织id 查询角色列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/OrgUserRoleController/getRoleListByOrgUserId",method = RequestMethod.POST)
    RestResponse getRoleListByOrgUserId(@RequestBody RestRequest<GetUserRoleListRequestDto> restRequest);

    /**
     * 保存组织用户维护
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/OrgUserRoleController/saveOrgUserRole",method = RequestMethod.POST)
    RestResponse saveOrgUserRole(@RequestBody RestRequest<OrgUserRequestDto> restRequest);

    /**
     * 查询用户（授权处）
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/OrgUserRoleController/getUserListBy",method = RequestMethod.POST)
    RestResponse getUserListBy(@RequestBody @Valid RestRequest<GetUserListRequestDto> restRequest);
}
