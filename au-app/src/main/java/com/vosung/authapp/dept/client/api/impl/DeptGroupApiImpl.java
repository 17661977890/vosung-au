package com.vosung.authapp.dept.client.api.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.DeptGroupApi;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.dept.service.DeptGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 部门分组接口api实现类
 * @Author 彬
 * @Date 2019/4/26
 */
@Slf4j
@RestController
public class DeptGroupApiImpl extends BaseApiImpl implements DeptGroupApi{
    @Autowired
    private DeptGroupService deptGroupService;

    /**
     * 保存部门分组信息
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse saveNewDeptGroup(@RequestBody @Valid RestRequest<DeptGroupRequestDto> restRequest) {
        log.info("保存部门信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptGroupService.saveNewDeptGroup(restRequest.getBody());
        log.info("保存部门信息出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    @Override
    public RestResponse getDeptGroupById(@RequestBody @Valid RestRequest<DeptGroupInfoRequestDto> restRequest) {
        log.info("根据id查看部门分组详情入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptGroupService.getDeptGroupById(restRequest.getBody());
        log.info("根据id查看部门分组详情出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 部门分组树形结构展示
     * @return
     */
    @Override
    public RestResponse getDeptGroupTree() {
        RestResponse restResponse = new RestResponse();
        restResponse = deptGroupService.getDeptGroupTree();
        return restResponse;
    }
}
