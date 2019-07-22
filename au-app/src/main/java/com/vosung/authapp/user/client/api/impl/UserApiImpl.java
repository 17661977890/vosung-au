package com.vosung.authapp.user.client.api.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.UserApi;
import com.vosung.auapi.client.dto.requestdto.orguserrole.UserOrgRoleRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.ResetPasswordRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UpdateUserStatusRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UserListRequestDto;
import com.vosung.auapi.client.dto.requestdto.user.UserRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户管理api 实现类
 * @Author 彬
 * @Date 2019/4/29
 */
@Slf4j
@RestController
public class UserApiImpl extends BaseApiImpl implements UserApi{
    @Autowired
    private UserService userService;

    /**
     * 保存用户信息
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse saveUser(@RequestBody @Valid RestRequest<UserRequestDto> restRequest) {
        log.info("保存用户信息入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = userService.saveUser(restRequest.getBody());
        log.info("保存用户信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据用户id 查用户详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getUserInfoById(@RequestParam("id") Integer id) {
        log.info("查询用户详情入参："+ id);
        RestResponse restResponse = new RestResponse();
        restResponse = userService.getUserInfoById(id);
        log.info("查询用户详情出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 获取用户列表
     * @return
     */
    @Override
    public RestResponse getUserList(RestRequest<UserListRequestDto>  restRequest) {
        log.info("查询用户列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader()==null || restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = userService.getUserList(restRequest);
        log.info("查询用户列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据用户id 禁用
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse forbiddenUser(@RequestBody @Valid RestRequest<UpdateUserStatusRequestDto> restRequest) {
        log.info("禁用用户状态入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = userService.forbiddenUser(restRequest.getBody());
        log.info("禁用用户状态出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据用户id 反禁用
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse unForbiddenUser(@RequestBody @Valid RestRequest<UpdateUserStatusRequestDto> restRequest) {
        log.info("反禁用用户状态入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = userService.unForbiddenUser(restRequest.getBody());
        log.info("反禁用用户状态出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 用户分配角色（新增数据）
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse addUserOrgRole(@RequestBody @Valid RestRequest<UserOrgRoleRequestDto> restRequest) {
        log.info("用户分配角色入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = userService.addUserOrgRole(restRequest.getBody());
        log.info("用户分配角色出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 重置密码入参
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse resetPassword(@RequestBody @Valid RestRequest<ResetPasswordRequestDto> restRequest) {
        log.info("重置密码入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = userService.resetPassword(restRequest.getBody());
        log.info("重置密码出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看所属部门
     * @return
     */
    @Override
    public RestResponse getBelongDept() {
        RestResponse restResponse = new RestResponse();
        restResponse = userService.getBelongDept();
        log.info("查看所属部门出参："+ JSON.toJSONString(restResponse));
        return restResponse;
    }
}
