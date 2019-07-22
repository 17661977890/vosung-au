package com.vosung.authapp.org.client.api.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.OrgApi;
import com.vosung.auapi.client.dto.requestdto.org.GetOrgListRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.OrgInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.OrgRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.UpdateOrgDataStateDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.org.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 组织机构API实现类
 * @Author 彬
 * @Date 2019/4/19
 */
@Slf4j
@RestController
public class OrgApiImpl extends BaseApiImpl implements OrgApi {

    @Autowired
    private OrgService orgService;

    /**
     * 保存组织机构
     * @param restRequest
     * @return body:Integer
     */
    @Override
    public RestResponse saveNewOrg(@RequestBody @Valid RestRequest<OrgRequestDto> restRequest) {
        log.info("保存组织机构入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgService.saveNewOrg(restRequest.getBody());
        log.info("保存组织机构出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 获取组织结构列表--分页，含总数
     * @param restRequest
     * @return body:OrgResponse
     */
    @Override
    public RestResponse getOrgList(@RequestBody @Valid RestRequest<GetOrgListRequestDto> restRequest) throws ClassNotFoundException {
        log.info("查询组织机构列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgService.getOrgList(restRequest);
        log.info("查询组织机构列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 提交组织机构
     * @param restRequest
     * @return
     */
    public RestResponse commitOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("提交组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgService.commitOrg(restRequest.getBody());
        log.info("提交组织机构出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 撤销组织机构
     * @param restRequest
     * @return
     */
    public RestResponse abolishOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("撤销组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgService.abolishOrg(restRequest.getBody());
        log.info("撤销组织机构出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 审核组织机构
     * @param restRequest
     * @return
     */
    public RestResponse auditOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("审核组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgService.auditOrg(restRequest.getBody());
        log.info("审核组织机构出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反审核组织机构
     * @param restRequest
     * @return
     */
    public RestResponse reverseAuditOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("反审核组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgService.reverseAuditOrg(restRequest.getBody());
        log.info("反审核组织机构出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除组织机构
     * @param restRequest
     * @return
     */
    public RestResponse deleteOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("删除组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgService.deleteOrg(restRequest.getBody());
        log.info("删除组织机构出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用组织机构
     * @param restRequest
     * @return
     */
    public RestResponse forbiddenOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("禁用组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgService.forbiddenOrg(restRequest.getBody());
        log.info("禁用组织机构出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用组织机构
     * @param restRequest
     * @return
     */
    public RestResponse unForbiddenOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("反禁用组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgService.unForbiddenOrg(restRequest.getBody());
        log.info("反禁用组织机构出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据id查看组织机构详情
     * @param restRequest
     * @return body:TAuOrganization
     */
    @Override
    public RestResponse getOrgInfoById(@RequestBody @Valid RestRequest<OrgInfoRequestDto> restRequest) {
        log.info("查看组织机构详情入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgService.getOrgInfoById(restRequest.getBody());
        log.info("查看组织机构详情出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查询可选组织（新增方案初显，不分页）
     * @return
     */
    @Override
    public RestResponse getSelectableOrgList() {
        RestResponse restResponse = new RestResponse();
        restResponse = orgService.getSelectableOrgList();
        log.info("查看可选组织出参："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查询法人组织
     * @return
     */
    @Override
    public RestResponse getLegalPersonOrgList() {
        RestResponse restResponse = new RestResponse();
        restResponse = orgService.getLegalPersonOrgList();
        log.info("查看法人组织出参："+ JSON.toJSONString(restResponse));
        return restResponse;
    }
}
