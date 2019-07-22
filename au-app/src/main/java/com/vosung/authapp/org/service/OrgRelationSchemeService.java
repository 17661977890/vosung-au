package com.vosung.authapp.org.service;

import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.GetSchemeInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.GetSchemeListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.OrgRelationSchemeRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.UpdateSchemeRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;

import javax.validation.Valid;

/**
 * 组织机构隶属关系方案业务层
 * @Author 彬
 * @Date 2019/4/26
 */
public interface OrgRelationSchemeService {

    /**
     * 隶属关系方案保存
     * @param relationSchemeRequestDto
     * @return
     */
    RestResponse saveOrgRelationScheme(OrgRelationSchemeRequestDto relationSchemeRequestDto);

    /**
     * 根据方案id获取方案详情，--包含组织关系树
     * @param getSchemeInfoRequestDto
     * @return
     */
    RestResponse getSchemeInfoById(GetSchemeInfoRequestDto getSchemeInfoRequestDto);

    /**
     * 获取组织机构方案列表
     * @param restRequest
     * @return
     */
    RestResponse getOrgSchemeList(RestRequest<GetSchemeListRequestDto> restRequest);

    /**
     * 反禁用组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    RestResponse unForbiddenScheme(UpdateSchemeRequestDto updateSchemeRequestDto);
    /**
     * 禁用组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    RestResponse forbiddenScheme(UpdateSchemeRequestDto updateSchemeRequestDto);
    /**
     * 删除组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    RestResponse deleteScheme(UpdateSchemeRequestDto updateSchemeRequestDto);
    /**
     * 反审核组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    RestResponse reverseAuditScheme(UpdateSchemeRequestDto updateSchemeRequestDto);
    /**
     * 提交组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    RestResponse commitScheme(UpdateSchemeRequestDto updateSchemeRequestDto);
    /**
     * 撤销组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    RestResponse abolishScheme(UpdateSchemeRequestDto updateSchemeRequestDto);
    /**
     * 审核组织隶属方案
     * @param updateSchemeRequestDto
     * @return
     */
    RestResponse auditScheme(UpdateSchemeRequestDto updateSchemeRequestDto);
}
