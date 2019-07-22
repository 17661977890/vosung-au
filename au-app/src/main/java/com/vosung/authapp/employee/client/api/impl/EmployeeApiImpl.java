package com.vosung.authapp.employee.client.api.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.EmployeeApi;
import com.vosung.auapi.client.dto.requestdto.employee.EmployeeDetailRequestDto;
import com.vosung.auapi.client.dto.requestdto.employee.EmployeeListRequest;
import com.vosung.auapi.client.dto.requestdto.employee.EmployeeRequestDto;
import com.vosung.auapi.client.dto.requestdto.employee.UpdateEmployeeStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 员工api实现类
 * @Author 彬
 * @Date 2019/5/8
 */
@Slf4j
@RestController
public class EmployeeApiImpl extends BaseApiImpl implements EmployeeApi {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 保存员工信息
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse saveEmployee(@RequestBody @Valid RestRequest<EmployeeRequestDto> restRequest) {
        log.info("保存员工信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.saveEmployee(restRequest.getBody());
        log.info("保存员工信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据id查看员工详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getEmployeeInfoById(@RequestParam("id") Integer id) {
        log.info("查看员工详情入参 ："+ id);
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.getEmployeeInfoById(id);
        log.info("查看员工详情出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查员工列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getEmployeeList(@RequestBody  RestRequest<EmployeeListRequest> restRequest) {
        log.info("查询员工列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.getEmployeeList(restRequest);
        log.info("查询员工列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 提交员工
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse commitEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        log.info("提交员工入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.commitEmployee(restRequest.getBody());
        log.info("提交员工出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 撤销员工
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse abolishEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        log.info("撤销员工入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.abolishEmployee(restRequest.getBody());
        log.info("撤销员工出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 审核员工
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse auditEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        log.info("审核员工入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.auditEmployee(restRequest.getBody());
        log.info("审核员工出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反审核员工
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse reverseAuditEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        log.info("反审核员工入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.reverseAuditEmployee(restRequest.getBody());
        log.info("反审核员工出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除员工
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse deleteEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        log.info("删除员工入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.deleteEmployee(restRequest.getBody());
        log.info("删除员工出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用员工
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse forbiddenEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        log.info("禁用员工入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.forbiddenEmployee(restRequest.getBody());
        log.info("禁用员工出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用员工
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse unForbiddenEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        log.info("反禁用员工入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.unForbiddenEmployee(restRequest.getBody());
        log.info("反禁用员工出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 查看人员基本信息（详细信息）
     * @param employeeId
     * @return
     */
    @Override
    public RestResponse getEmployeeDetailedById(@RequestParam("employeeId") Integer employeeId) {
        log.info("查看人员基本信息入参 ："+ employeeId);
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.getEmployeeDetailedById(employeeId);
        log.info("查看人员基本信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 保存人员基本详细信息
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse saveEmployeeDetailed(@RequestBody @Valid RestRequest<EmployeeDetailRequestDto> restRequest) {
        log.info("修改保存人员基本信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.saveEmployeeDetailed(restRequest.getBody());
        log.info("修改保存人员基本信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据员工id和组织id 查询任岗list
     * @param employeeId
     * @param orgId
     * @return
     */
    @Override
    public RestResponse getPostListByEmployeeIdOrgId(Integer employeeId, Integer orgId) {
        log.info("查看任岗list入参 =>员工id:"+ employeeId+"组织id: "+orgId);
        RestResponse restResponse = new RestResponse();
        restResponse = employeeService.getPostListByEmployeeIdOrgId(employeeId,orgId);
        log.info("查看任岗list入参:"+JSON.toJSONString(restResponse));
        return restResponse;
    }
}
