package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.employee.EmployeeDetailRequestDto;
import com.vosung.auapi.client.dto.requestdto.employee.EmployeeListRequest;
import com.vosung.auapi.client.dto.requestdto.employee.EmployeeRequestDto;
import com.vosung.auapi.client.dto.requestdto.employee.UpdateEmployeeStatusDto;
import com.vosung.auapi.client.dto.requestdto.org.UpdateOrgDataStateDto;
import com.vosung.auapi.client.hystrix.EmployeeApiHystrix;
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
 * 员工相关api
 * @Author 彬
 * @Date 2019/5/8
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = EmployeeApiHystrix.class)
public interface EmployeeApi {

    /**
     * 保存员工信息
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/employeeController/saveEmployee",method = RequestMethod.POST)
    RestResponse saveEmployee(@RequestBody @Valid RestRequest<EmployeeRequestDto> restRequest);

    /**
     * 根据员工id查看员工详情
     * @param id
     * @return
     */
    @RequestMapping(value = "api/employeeController/getEmployeeInfoById",method = RequestMethod.POST)
    RestResponse getEmployeeInfoById(@RequestParam("id") Integer id);
    /**
     * 查看员工list
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/employeeController/getEmployeeList",method = RequestMethod.POST)
    RestResponse getEmployeeList(@RequestBody RestRequest<EmployeeListRequest> restRequest);

    /**
     * 查看人员基本信息（详细信息）
     * @param employeeId
     * @return
     */
    @RequestMapping(value = "api/employeeController/getEmployeeDetailedById",method = RequestMethod.POST)
    RestResponse getEmployeeDetailedById(@RequestParam("employeeId") Integer employeeId);

    /**
     * 修改保存人员信息
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/employeeController/saveEmployeeDetailed",method = RequestMethod.POST)
    RestResponse saveEmployeeDetailed(@RequestBody @Valid RestRequest<EmployeeDetailRequestDto> restRequest);

    /**
     * 根据组织id和员工id查询任岗list
     * @param employeeId
     * @param orgId
     * @return
     */
    @RequestMapping(value = "api/employeeController/getPostListByEmployeeIdOrgId",method = RequestMethod.POST)
    RestResponse getPostListByEmployeeIdOrgId(@RequestParam("employeeId") Integer employeeId, @RequestParam("orgId") Integer orgId);

    /**
     * 提交员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/employeeController/commitEmployee",method = RequestMethod.POST)
    RestResponse commitEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest);
    /**
     * 撤销员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/employeeController/abolishEmployee",method = RequestMethod.POST)
    RestResponse abolishEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest);

    /**
     * 审核员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/employeeController/auditEmployee",method = RequestMethod.POST)
    RestResponse auditEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest);

    /**
     * 反审核员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/employeeController/reverseAuditEmployee",method = RequestMethod.POST)
    RestResponse reverseAuditEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest);

    /**
     * 删除员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/employeeController/deleteEmployee",method = RequestMethod.POST)
    RestResponse deleteEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest);

    /**
     * 禁用员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/employeeController/forbiddenEmployee",method = RequestMethod.POST)
    RestResponse forbiddenEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest);

    /**
     * 反禁员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/employeeController/unForbiddenEmployee",method = RequestMethod.POST)
    RestResponse unForbiddenEmployee(@RequestBody @Valid RestRequest<UpdateEmployeeStatusDto> restRequest);
}
