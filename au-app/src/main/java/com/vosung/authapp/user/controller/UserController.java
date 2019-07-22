package com.vosung.authapp.user.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.UserApi;
import com.vosung.auapi.client.dto.requestdto.orguserrole.UserOrgRoleRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.ResetPasswordRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UpdateUserStatusRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UserListRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UserRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户管理web层
 * @Author 彬
 * @Date 2019/4/29
 */
@Slf4j
@RestController
@RequestMapping("auth/userController")
public class UserController extends BaseController{
    @Autowired
    private UserApi userApi;

    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    public RestResponse saveUser(@RequestBody @Valid RestRequest<UserRequestDto> restRequest) {
        log.info("保存用户信息入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = userApi.saveUser(restRequest);
        log.info("保存用户信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据用户id查询用户详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getUserInfoById",method = RequestMethod.POST)
    public RestResponse getUserInfoById(@RequestParam("id") Integer id) {
        log.info("查询用户详情入参 ："+ id);
        RestResponse restResponse = new RestResponse();
        restResponse = userApi.getUserInfoById(id);
        log.info("查询用户详情出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public RestResponse getUserList(@RequestBody RestRequest<UserListRequestDto>  restRequest) {
        log.info("查询用户列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader()==null || restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = userApi.getUserList(restRequest);
        log.info("查询用户列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据用户id 禁用
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/forbiddenUser",method = RequestMethod.POST)
    public RestResponse forbiddenUser(@RequestBody @Valid RestRequest<UpdateUserStatusRequestDto> restRequest) {
        log.info("禁用用户状态入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = userApi.forbiddenUser(restRequest);
        log.info("禁用用户状态出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据用户id 反禁用
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/unForbiddenUser",method = RequestMethod.POST)
    public RestResponse unForbiddenUser(@RequestBody @Valid RestRequest<UpdateUserStatusRequestDto> restRequest) {
        log.info("反禁用用户状态入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = userApi.unForbiddenUser(restRequest);
        log.info("反禁用用户状态出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 用户分配角色(含批量)
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/addUserOrgRole",method = RequestMethod.POST)
    public RestResponse addUserOrgRole(@RequestBody @Valid RestRequest<UserOrgRoleRequestDto> restRequest) {
        log.info("用户分配角色入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = userApi.addUserOrgRole(restRequest);
        log.info("用户分配角色出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 重置密码
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    public RestResponse resetPassword(@RequestBody @Valid RestRequest<ResetPasswordRequestDto> restRequest) {
        log.info("重置密码入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = userApi.resetPassword(restRequest);
        log.info("重置密码出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 查看所属部门
     * @return
     */
        @RequestMapping(value = "/getBelongDept",method = RequestMethod.POST)
    public RestResponse getBelongDept() {
        RestResponse restResponse = new RestResponse();
        restResponse = userApi.getBelongDept();
        log.info("查看所属部门出参："+ JSON.toJSONString(restResponse));
        return restResponse;
    }
}
