package com.vosung.authapp.employee.service;

import com.vosung.auapi.client.dto.requestdto.employee.EmployeeDetailRequestDto;
import com.vosung.auapi.client.dto.requestdto.employee.EmployeeListRequest;
import com.vosung.auapi.client.dto.requestdto.employee.EmployeeRequestDto;
import com.vosung.auapi.client.dto.requestdto.employee.UpdateEmployeeStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;

import javax.validation.Valid;

/**
 * 员工管理业务层
 * @Author 彬
 * @Date 2019/5/8
 */
public interface EmployeeService {

    /**
     * 保存员工信息
     * @param employeeRequestDto
     * @return
     */
    RestResponse saveEmployee(EmployeeRequestDto employeeRequestDto);

    /**
     * 根据员工id 查询员工详情（含任岗列表，财务列表）
     * @param id
     * @return
     */
    RestResponse getEmployeeInfoById(Integer id);

    /**
     * 查看员工list
     * @param restRequest
     * @return
     */
    RestResponse getEmployeeList(RestRequest<EmployeeListRequest> restRequest);


    /**
     * 根据员工id查看人员基本信息（详细信息）
     * @param employeeId
     * @return
     */
    RestResponse getEmployeeDetailedById(Integer employeeId);

    /**
     * 修改保存人员基本详细信息
     * @param employeeDetailRequestDto
     * @return
     */
    RestResponse saveEmployeeDetailed(EmployeeDetailRequestDto employeeDetailRequestDto);

    /**
     * 根据员工id 和 工作组织id 查询任岗列表---------新增任岗信息时，选完员工，返现调接口查数据
     * @param employeeId
     * @param orgId
     * @return
     */
    RestResponse getPostListByEmployeeIdOrgId(Integer employeeId,Integer orgId);

    /**
     * 提交员工
     * @param body
     * @return
     */
    RestResponse commitEmployee(UpdateEmployeeStatusDto body);

    /**
     * 撤销员工
     * @param body
     * @return
     */
    RestResponse abolishEmployee(UpdateEmployeeStatusDto body);

    /**
     * 审核员工
     * @param body
     * @return
     */
    RestResponse auditEmployee(UpdateEmployeeStatusDto body);

    /**
     * 反审核员工
     * @param body
     * @return
     */
    RestResponse reverseAuditEmployee( UpdateEmployeeStatusDto body);

    /**
     * 删除员工
     * @param body
     * @return
     */
    RestResponse deleteEmployee(UpdateEmployeeStatusDto body);

    /**
     * 禁用员工
     * @param body
     * @return
     */
    RestResponse forbiddenEmployee(UpdateEmployeeStatusDto body);

    /**
     * 反禁员工
     * @param body
     * @return
     */
    RestResponse unForbiddenEmployee(UpdateEmployeeStatusDto body);
}
