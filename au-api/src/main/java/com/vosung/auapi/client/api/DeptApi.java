package com.vosung.auapi.client.api;


import com.vosung.auapi.client.dto.requestdto.dept.DeptInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.dept.DeptRequestDto;
import com.vosung.auapi.client.dto.requestdto.dept.GetDeptListRequestDto;
import com.vosung.auapi.client.dto.requestdto.dept.UpdateDeptDataStateDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.OrgInfoRequestDto;
import com.vosung.auapi.client.hystrix.DeptApiHystrix;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * 部门服务相关API接口
 * @Author 彬
 * @Date 2019/4/24
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = DeptApiHystrix.class)
public interface DeptApi {

    /**
     * 保存部门（新增/修改）
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/deptController/saveNewDept",method = RequestMethod.POST)
    RestResponse saveNewDept(@RequestBody @Valid RestRequest<DeptRequestDto> restRequest);

    /**
     * 部门列表分页展示
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/deptController/getDeptList",method = RequestMethod.POST)
    RestResponse getDeptList(@RequestBody @Valid RestRequest<GetDeptListRequestDto> restRequest);

    /**
     * 根据id 查询部门详情
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/deptController/getDeptInfoById",method = RequestMethod.POST)
    RestResponse getDeptInfoById(@RequestBody @Valid RestRequest<DeptInfoRequestDto> restRequest);

    /**
     * 反禁部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/deptController/unForbiddenDept",method = RequestMethod.POST)
    RestResponse unForbiddenDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest);

    /**
     * 禁用部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/deptController/forbiddenDept",method = RequestMethod.POST)
    RestResponse forbiddenDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest);

    /**
     * 删除部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/deptController/deleteDept",method = RequestMethod.POST)
    RestResponse deleteDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest);

    /**
     * 反审核部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/deptController/reverseAuditDept",method = RequestMethod.POST)
    RestResponse reverseAuditDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest);

    /**
     * 审核部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/deptController/auditDept",method = RequestMethod.POST)
    RestResponse auditDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest);

    /**
     * 撤销部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/deptController/abolishDept",method = RequestMethod.POST)
    RestResponse abolishDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest);

    /**
     * 提交部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/deptController/commitDept",method = RequestMethod.POST)
    RestResponse commitDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest);
}
