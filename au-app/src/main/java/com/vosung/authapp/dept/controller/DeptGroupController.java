package com.vosung.authapp.dept.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.DeptGroupApi;
import com.vosung.auapi.client.dto.requestdto.dept.DeptInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 部门分组web层
 * @Author 彬
 * @Date 2019/4/26
 */
@Slf4j
@RestController
@RequestMapping("auth/deptGroupController")
public class DeptGroupController extends BaseController {
    @Autowired
    private DeptGroupApi deptGroupApi;

    /**
     * 保存部门分组信息
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/saveNewDeptGroup",method = RequestMethod.POST)
    public RestResponse saveNewDeptGroup(@RequestBody @Valid RestRequest<DeptGroupRequestDto> restRequest){
        log.info("保存部门信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptGroupApi.saveNewDeptGroup(restRequest);
        log.info("保存部门信息出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据id查看部门分组详情
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getDeptGroupById",method = RequestMethod.POST)
    public RestResponse getDeptGroupById(@RequestBody @Valid RestRequest<DeptGroupInfoRequestDto> restRequest) {
        log.info("根据id查看部门分组详情入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptGroupApi.getDeptGroupById(restRequest);
        log.info("根据id查看部门分组详情出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 部门分组树形结构展示
     * @return
     */
    @RequestMapping(value = "/getDeptGroupTree",method = RequestMethod.POST)
    public RestResponse getDeptGroupTree(){
        log.info("==================部门分组树形结构展示==================");
        RestResponse restResponse = new RestResponse();
        restResponse = deptGroupApi.getDeptGroupTree();
        return restResponse;
    }
}
