package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupRequestDto;
import com.vosung.auapi.client.hystrix.DeptApiHystrix;
import com.vosung.auapi.client.hystrix.DeptGroupHystrix;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * 部门分组API
 * @Author 彬
 * @Date 2019/4/26
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = DeptGroupHystrix.class)
public interface DeptGroupApi {

    /**
     * 保存报部门分组信息（新增/修改）
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/deptGroupController/saveNewDeptGroup",method = RequestMethod.POST)
    RestResponse saveNewDeptGroup(@RequestBody @Valid RestRequest<DeptGroupRequestDto> restRequest);

    /**
     * 根据id查看组织机构详情
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/deptGroupController/getDeptGroupById",method = RequestMethod.POST)
    RestResponse getDeptGroupById(@RequestBody @Valid RestRequest<DeptGroupInfoRequestDto> restRequest);

    /**
     * 部门分组属性结构展示
     * @return
     */
    @RequestMapping(value = "api/deptGroupController/getDeptGroupTree",method = RequestMethod.POST)
    RestResponse getDeptGroupTree();
}
