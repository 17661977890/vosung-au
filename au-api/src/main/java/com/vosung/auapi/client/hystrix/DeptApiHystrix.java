package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.DeptApi;
import com.vosung.auapi.client.dto.requestdto.dept.DeptInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.dept.DeptRequestDto;
import com.vosung.auapi.client.dto.requestdto.dept.GetDeptListRequestDto;
import com.vosung.auapi.client.dto.requestdto.dept.UpdateDeptDataStateDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 部门接口服务调用熔断机制处理
 * @Author 彬
 * @Date 2019/4/26
 */
@Slf4j
@Component
public class DeptApiHystrix extends BaseHystrix implements DeptApi {


    @Override
    public RestResponse saveNewDept(@RequestBody @Valid RestRequest<DeptRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getDeptList(@RequestBody @Valid RestRequest<GetDeptListRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getDeptInfoById(@RequestBody @Valid RestRequest<DeptInfoRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse unForbiddenDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse forbiddenDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse deleteDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse reverseAuditDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse auditDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse abolishDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse commitDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        return getError();
    }
}
