package com.vosung.authapp.org.service;

import com.vosung.auapi.client.dto.requestdto.orgrelation.DeleteOrgRelationRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelation.OrgRelationRequestDto;
import com.vosung.auapi.client.restparam.RestResponse;

/**
 * 组织机构隶属关系
 * @Author 彬
 * @Date 2019/4/28
 */
public interface OrgRelationService {

    /**
     * 新增组织关系
     * @param orgRelationRequestDto
     * @return
     */
    RestResponse addOrgRelation(OrgRelationRequestDto orgRelationRequestDto);

    /**
     * 根据主键id 删除组织关系（如果删除的组织关系id的组织id 是父节点，那子节点也都删除）
     * @param deleteOrgRelationRequestDto
     * @return
     */
    RestResponse deleteOrgRelation(DeleteOrgRelationRequestDto deleteOrgRelationRequestDto);



}
