package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.menu.MenuRequest;
import com.vosung.auapi.client.dto.requestdto.menu.MenuRequestDto;
import com.vosung.auapi.client.dto.requestdto.menu.UpdateMenuStatusDto;
import com.vosung.auapi.client.hystrix.MenuApiHystrix;
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
 * 菜单api
 * @Author 彬
 * @Date 2019/5/16
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = MenuApiHystrix.class)
public interface MenuApi {

    /**
     * 菜单保存
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/menuController/saveMenu",method = RequestMethod.POST)
    RestResponse saveMenu(@RequestBody @Valid RestRequest<MenuRequestDto> restRequest);

    /**
     * 查看菜单详情
     * @param id
     * @return
     */
    @RequestMapping(value = "api/menuController/getMenuInfoById",method = RequestMethod.POST)
    RestResponse getMenuInfoById(@RequestParam("id") Integer id);

    @RequestMapping(value = "api/menuController/commitMenu",method = RequestMethod.POST)
    RestResponse commitMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest);

    @RequestMapping(value = "api/menuController/abolishMenu",method = RequestMethod.POST)
    RestResponse abolishMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest);

    @RequestMapping(value = "api/menuController/auditMenu",method = RequestMethod.POST)
    RestResponse auditMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest);

    @RequestMapping(value = "api/menuController/reverseAuditMenu",method = RequestMethod.POST)
    RestResponse reverseAuditMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest);

    @RequestMapping(value = "api/menuController/deleteMenu",method = RequestMethod.POST)
    RestResponse deleteMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest);

    @RequestMapping(value = "api/menuController/forbiddenMenu",method = RequestMethod.POST)
    RestResponse forbiddenMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest);

    @RequestMapping(value = "api/menuController/unForbiddenMenu",method = RequestMethod.POST)
    RestResponse unForbiddenMenu(@RequestBody @Valid RestRequest<UpdateMenuStatusDto> restRequest);

    /**
     * 展示所有菜单--树
     * @return
     */
    @RequestMapping(value = "api/menuController/getAllMenuTree",method = RequestMethod.POST)
    RestResponse getAllMenuTree();

    /**
     * 菜单列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/menuController/getMenuList",method = RequestMethod.POST)
    RestResponse getMenuList(@RequestBody @Valid RestRequest<MenuRequest> restRequest);
}
