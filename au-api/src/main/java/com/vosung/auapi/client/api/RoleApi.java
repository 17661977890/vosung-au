package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.role.RoleListRequestDto;
import com.vosung.auapi.client.dto.requestdto.role.RoleRequestDto;
import com.vosung.auapi.client.dto.requestdto.role.UpdateRoleStatusRequestDto;
import com.vosung.auapi.client.hystrix.RoleApiHystrix;
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
 * 角色api
 * @Author 彬
 * @Date 2019/5/5
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = RoleApiHystrix.class)
public interface RoleApi {

    /**
     * 保存角色
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/roleController/saveRole",method = RequestMethod.POST)
    RestResponse saveRole(@RequestBody @Valid RestRequest<RoleRequestDto> restRequest);

    /**
     * 根据角色id查看详情
     * @param id
     * @return
     */
    @RequestMapping(value = "api/roleController/getRoleInfoById",method = RequestMethod.POST)
    RestResponse getRoleInfoById(@RequestParam("id") Integer id);

    /**
     * 角色列表查询
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/roleController/getRoleList",method = RequestMethod.POST)
    RestResponse getRoleList(@RequestBody RestRequest<RoleListRequestDto> restRequest);

    /**
     * 删除角色
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/roleController/deleteRole",method = RequestMethod.POST)
    RestResponse deleteRole(@RequestBody @Valid RestRequest<UpdateRoleStatusRequestDto> restRequest);

    /**
     * 修改角色状态（禁用）
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/roleController/forbiddenRole",method = RequestMethod.POST)
    RestResponse forbiddenRole(@RequestBody @Valid RestRequest<UpdateRoleStatusRequestDto> restRequest);

    /**
     * 修改角色状态（反禁）
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/roleController/unForbiddenRole",method = RequestMethod.POST)
    RestResponse unForbiddenRole(@RequestBody @Valid RestRequest<UpdateRoleStatusRequestDto> restRequest);

}
