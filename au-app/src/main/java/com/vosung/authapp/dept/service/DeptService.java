package com.vosung.authapp.dept.service;


import com.vosung.auapi.client.dto.requestdto.dept.DeptInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.dept.DeptRequestDto;
import com.vosung.auapi.client.dto.requestdto.dept.GetDeptListRequestDto;
import com.vosung.auapi.client.dto.requestdto.dept.UpdateDeptDataStateDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;

import javax.validation.Valid;

/**
 * 部门服务业务层
 * @Author 彬
 * @Date 2019/4/24
 */
public interface DeptService {

    /**
     * 保存部门(有id:修改 无id:保存)
     * @param deptRequestDto
     * @return
     */
    RestResponse saveNewDept(DeptRequestDto deptRequestDto);

    /**
     * 部门列表的分页展示
     * @param restRequest
     * @return body:DeptResponseDto
     */
    RestResponse getDeptList(RestRequest<GetDeptListRequestDto> restRequest);


    /**
     * 根据id查看部门详情
     * @param deptInfoRequestDto
     * @return
     */
    RestResponse getDeptInfoById(DeptInfoRequestDto deptInfoRequestDto);

    /**
     * 提交部门
     * @param body
     * @return
     */
    RestResponse commitDept(UpdateDeptDataStateDto body);
    /**
     * 撤销部门
     * @param body
     * @return
     */
    RestResponse abolishDept(UpdateDeptDataStateDto body);
    /**
     * 审核部门
     * @param body
     * @return
     */
    RestResponse auditDept(UpdateDeptDataStateDto body);
    /**
     * 反审核部门
     * @param body
     * @return
     */
    RestResponse reverseAuditDept(UpdateDeptDataStateDto body);
    /**
     * 删除部门： 已被未删除岗位和用户使用的不可以删除 | 数据状态某情况不能删除
     * @param body
     * @return
     */
    RestResponse deleteDept(UpdateDeptDataStateDto body);
    /**
     * 禁用部门：已被未删除未禁用的岗位和用户使用的不可以删除
     * @param body
     * @return
     */
    RestResponse forbiddenDept(UpdateDeptDataStateDto body);
    /**
     * 反禁用部门
     * @param body
     * @return
     */
    RestResponse unForbiddenDept(UpdateDeptDataStateDto body);
}
