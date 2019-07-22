package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.GetSchemeInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.GetSchemeListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.OrgRelationSchemeRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.UpdateSchemeRequestDto;
import com.vosung.auapi.client.hystrix.OrgRelationSchemeHystrix;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * 组织机构隶属方案关系API
 * @Author 彬
 * @Date 2019/4/26
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = OrgRelationSchemeHystrix.class)
public interface OrgRelationSchemeApi {

    /**
     * 保存组织机构隶属方案关系
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgRelationSchemeController/saveOrgRelationScheme",method = RequestMethod.POST)
    RestResponse saveOrgRelationScheme(@RequestBody @Valid RestRequest<OrgRelationSchemeRequestDto> restRequest);

    /**
     * 获取组织机构方案列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgRelationSchemeController/getOrgSchemeList",method = RequestMethod.POST)
    RestResponse getOrgSchemeList(@RequestBody RestRequest<GetSchemeListRequestDto> restRequest);

    /**
     * 提交组织隶属方案
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgRelationSchemeController/commitScheme",method = RequestMethod.POST)
    RestResponse commitScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest);
    /**
     * 撤销组织隶属方案
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgRelationSchemeController/abolishScheme",method = RequestMethod.POST)
    RestResponse abolishScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest);
    /**
     * 审核组织隶属方案
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgRelationSchemeController/auditScheme",method = RequestMethod.POST)
    RestResponse auditScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest);
    /**
     * 反审核组织隶属方案
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgRelationSchemeController/reverseAuditScheme",method = RequestMethod.POST)
    RestResponse reverseAuditScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest);
    /**
     * 删除组织隶属方案
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgRelationSchemeController/deleteScheme",method = RequestMethod.POST)
    RestResponse deleteScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest);
    /**
     * 禁用组织隶属方案
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgRelationSchemeController/forbiddenScheme",method = RequestMethod.POST)
    RestResponse forbiddenScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest);
    /**
     * 反禁用组织隶属方案
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgRelationSchemeController/unForbiddenScheme",method = RequestMethod.POST)
    RestResponse unForbiddenScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest);

    /**
     * 根据id查看组织关系方案详情
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/orgRelationSchemeController/getSchemeInfoById",method = RequestMethod.POST)
    RestResponse getSchemeInfoById(@RequestBody @Valid RestRequest<GetSchemeInfoRequestDto> restRequest);
}
