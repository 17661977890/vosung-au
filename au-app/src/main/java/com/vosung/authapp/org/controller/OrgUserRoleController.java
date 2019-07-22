package com.vosung.authapp.org.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.OrgUserRoleApi;
import com.vosung.auapi.client.dto.requestdto.orguserrole.GetUserListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orguserrole.GetUserRoleListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orguserrole.OrgUserRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 组织用户维护web层
 * @Author 彬
 * @Date 2019/4/30
 */
@Slf4j
@RequestMapping("auth/orgUserRoleController")
@RestController
public class OrgUserRoleController extends BaseController{
    @Autowired
    private OrgUserRoleApi orgUserRoleApi;

    /**
     * 获取组织下的用户角色列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getUserRoleListByOrgId",method = RequestMethod.POST)
    public RestResponse getUserRoleListByOrgId(@RequestBody RestRequest<GetUserRoleListRequestDto> restRequest) {
        log.info("查询组织下的用户角色关系列表入参："+JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getBody().getOrgId() == null ){
            throw new BusinessException("3011111152",getMessage("3011111152"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgUserRoleApi.getUserRoleListByOrgId(restRequest);
        log.info("查询组织下的用户角色关系列表出参"+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据组织和用户id 获取角色id
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getRoleListByOrgUserId",method = RequestMethod.POST)
    public RestResponse getRoleListByOrgUserId(@RequestBody  RestRequest<GetUserRoleListRequestDto> restRequest) {
        log.info("根据组织用户id查询角色关系列表入参："+JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getBody().getOrgId() == null ){
            throw new BusinessException("3011111152",getMessage("3011111152"));
        }
        if(restRequest.getBody().getUserId() == null){
            throw new BusinessException("3011111153",getMessage("3011111153"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgUserRoleApi.getRoleListByOrgUserId(restRequest);
        log.info("根据组织用户id查询角色关系列表出参"+ JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 保存组织用户维护
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/saveOrgUserRole",method = RequestMethod.POST)
    public RestResponse saveOrgUserRole(@RequestBody RestRequest<OrgUserRequestDto> restRequest) {
        log.info("保存组织用户维护入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getBody().getOrgId()==null){
            throw new BusinessException("3011111118",getMessage("3011111118"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgUserRoleApi.saveOrgUserRole(restRequest);
        log.info("保存组织用户维护出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * （授权处）查询某角色用户列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getUserListBy",method = RequestMethod.POST)
    public RestResponse getUserListBy(@RequestBody @Valid RestRequest<GetUserListRequestDto> restRequest) {
        log.info("（授权处）查询某角色用户列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader()==null || restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgUserRoleApi.getUserListBy(restRequest);
        log.info("（授权处）查询某角色用户列表出参："+ JSON.toJSONString(restResponse));
        return restResponse;
    }
}
