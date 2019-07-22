package com.vosung.authapp.dept.service;

import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupRequestDto;
import com.vosung.auapi.client.restparam.RestResponse;

/**
 * 部门分组业务接口
 * @Author 彬
 * @Date 2019/4/26
 */
public interface DeptGroupService {

    /**
     * 保存部门分组信息
     * @param deptGroupRequestDto
     * @return
     */
    RestResponse saveNewDeptGroup(DeptGroupRequestDto deptGroupRequestDto);

    /**
     * 根据分组id查看详情
     * @param deptGroupInfoRequestDto
     * @return
     */
    RestResponse getDeptGroupById(DeptGroupInfoRequestDto deptGroupInfoRequestDto);

    /**
     * 部门分组树形结构展示
     * @return
     */
    RestResponse getDeptGroupTree();
}
