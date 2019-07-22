package com.vosung.authapp.org.service;

import com.vosung.auapi.client.dto.requestdto.org.GetOrgListRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.OrgInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.OrgRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.UpdateOrgDataStateDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;

import javax.validation.Valid;

/**
 * 组织机构业务曾接口
 * @Author bin
 */
public interface OrgService {

    /**
     * 保存组织机构----（入参有id修改，无id新增）
     * @param orgRequestDto
     * @return body: Integer
     */
    RestResponse saveNewOrg(OrgRequestDto orgRequestDto);

    /**
     * 获取组织机构列表---分页（包含总数）
     * @param restRequest
     * @return body: OrgResponse
     */
    RestResponse getOrgList(RestRequest<GetOrgListRequestDto> restRequest) throws ClassNotFoundException;

    /**
     * 根据id查看组织机构详情
     * @param orgInfoRequestDto
     * @return body:TAuOrganization
     */
    RestResponse getOrgInfoById(OrgInfoRequestDto orgInfoRequestDto);

    /**
     * 获取可选组织（新增组织关系方案初显数据，不分页）---已审核，未删除，未禁止
     * @return
     */
    RestResponse getSelectableOrgList();

    /**
     * 获取法人组织（去重所属法人）
     * @return
     */
    RestResponse getLegalPersonOrgList();

    /**
     * 提交组织
     * @param restRequest
     * @return
     */
    RestResponse commitOrg(UpdateOrgDataStateDto restRequest);

    /**
     * 撤销组织
     * @param restRequest
     * @return
     */
    RestResponse abolishOrg(UpdateOrgDataStateDto restRequest);

    /**
     * 审核组织
     * @param restRequest
     * @return
     */
    RestResponse auditOrg(UpdateOrgDataStateDto restRequest);

    /**
     * 反审核组织
     * @param restRequest
     * @return
     */
    RestResponse reverseAuditOrg(UpdateOrgDataStateDto restRequest);

    /**
     * 删除组织
     * @param restRequest
     * @return
     */
    RestResponse deleteOrg(UpdateOrgDataStateDto restRequest);

    /**
     * 禁用组织
     * @param restRequest
     * @return
     */
    RestResponse forbiddenOrg(UpdateOrgDataStateDto restRequest);

    /**
     * 反禁用组织
     * @param restRequest
     * @return
     */
    RestResponse unForbiddenOrg(UpdateOrgDataStateDto restRequest);
}
