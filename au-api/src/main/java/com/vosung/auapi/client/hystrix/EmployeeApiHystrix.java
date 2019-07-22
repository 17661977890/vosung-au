package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.EmployeeApi;
import com.vosung.auapi.client.dto.requestdto.employee.EmployeeDetailRequestDto;
import com.vosung.auapi.client.dto.requestdto.employee.EmployeeListRequest;
import com.vosung.auapi.client.dto.requestdto.employee.EmployeeRequestDto;
import com.vosung.auapi.client.dto.requestdto.employee.UpdateEmployeeStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 员工api熔断
 * @Author 彬
 * @Date 2019/5/8
 */
@Component
public class EmployeeApiHystrix extends BaseHystrix implements EmployeeApi {
    @Override
    public RestResponse saveEmployee(@RequestBody @Valid RestRequest<EmployeeRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getEmployeeInfoById(@RequestParam("id") Integer id) {
        return getError();
    }

    @Override
    public RestResponse getEmployeeList(RestRequest<EmployeeListRequest> restRequest) {
        return getError();
    }


    @Override
    public RestResponse getEmployeeDetailedById(@RequestParam("employeeId") Integer employeeId) {
        return getError();
    }

    @Override
    public RestResponse saveEmployeeDetailed(@Valid RestRequest<EmployeeDetailRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getPostListByEmployeeIdOrgId(@RequestParam("employeeId") Integer employeeId,@RequestParam("id") Integer orgId) {
        return getError();
    }

    @Override
    public RestResponse commitEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse abolishEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse auditEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse reverseAuditEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse deleteEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse forbiddenEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse unForbiddenEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest) {
        return getError();
    }
}
