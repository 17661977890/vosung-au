package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.org.GetOrgListRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.OrgInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.OrgRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.UpdateOrgDataStateDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.UpdateSchemeRequestDto;
import com.vosung.auapi.client.hystrix.OrgApiHystrix;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * 组织机构--API
 * @Author 彬
 * @Date 2019/4/19
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = OrgApiHystrix.class)
public interface OrgApi {

    /**
     * 保存组织机构api----（入参有id修改，无id新增）
     * @param restRequest
     * @return body:Integer
     */
    @RequestMapping(value = "api/orgController/saveNewOrg",method = RequestMethod.POST)
    RestResponse saveNewOrg(@RequestBody @Valid RestRequest<OrgRequestDto> restRequest);

    /**
     * 获取组织机构列表--分页，含总数
     * @param restRequest
     * @return body:OrgResponse
     */
    @RequestMapping(value = "api/orgController/getOrgList",method = RequestMethod.POST)
    RestResponse getOrgList(@RequestBody @Valid RestRequest<GetOrgListRequestDto> restRequest) throws ClassNotFoundException;

    /**
     * 根据id查看组织机构详情
     * @param restRequest
     * @return body:OrgResponseDto
     */
    @RequestMapping(value = "api/orgController/getOrgInfoById",method = RequestMethod.POST)
    RestResponse getOrgInfoById(@RequestBody @Valid RestRequest<OrgInfoRequestDto> restRequest);

    /**
     * 查询可选组织（新增组织关系方案初显数据）
     * @return
     */
    @RequestMapping(value = "api/orgController/getSelectableOrgList",method = RequestMethod.POST)
    RestResponse getSelectableOrgList();

    /**
     * 展示法人组织
     * @return
     */
    @RequestMapping(value = "api/orgController/getLegalPersonOrgList",method = RequestMethod.POST)
    RestResponse getLegalPersonOrgList();

    /**
     * 提交
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgController/commitOrg",method = RequestMethod.POST)
    RestResponse commitOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest);

    /**
     * 撤销
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgController/abolishOrg",method = RequestMethod.POST)
    RestResponse abolishOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest);

    /**
     * 审核
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgController/auditOrg",method = RequestMethod.POST)
    RestResponse auditOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest);

    /**
     * 反审核
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgController/reverseAuditOrg",method = RequestMethod.POST)
    RestResponse reverseAuditOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest);

    /**
     * 删除
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgController/deleteOrg",method = RequestMethod.POST)
    RestResponse deleteOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest);

    /**
     * 禁用
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgController/forbiddenOrg",method = RequestMethod.POST)
    RestResponse forbiddenOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest);

    /**
     * 反禁用
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgController/unForbiddenOrg",method = RequestMethod.POST)
    RestResponse unForbiddenOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest);
}
