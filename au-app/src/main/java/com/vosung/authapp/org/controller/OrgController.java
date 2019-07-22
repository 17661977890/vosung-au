package com.vosung.authapp.org.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.OrgApi;
import com.vosung.auapi.client.dto.requestdto.org.GetOrgListRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.OrgInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.OrgRequestDto;
import com.vosung.auapi.client.dto.requestdto.org.UpdateOrgDataStateDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.UpdateSchemeRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 组织机构web层
 * @Author 彬
 * @Date 2019/4/19
 */
@RestController
@RequestMapping("auth/orgController")
@Slf4j
public class OrgController extends BaseController{

    @Autowired
    private OrgApi orgApi;

    /**
     * 测试全局异常处理
     */
    @RequestMapping("/testEx")
    public void getEx(){
        throw new BusinessException("1000101","抛出异常");
    }

    /***
     * 保存组织机构----（入参有id修改，无id新增）
     * @param restRequest
     * @return body: Integer
     */
    @RequestMapping(value = "/saveNewOrg",method = RequestMethod.POST)
    public RestResponse saveNewOrg(@RequestBody @Valid RestRequest<OrgRequestDto> restRequest){
        log.info("保存组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgApi.saveNewOrg(restRequest);
        log.info("保存组织机构出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 多条件查询组织机构列表---分页，含总数
     *
     * @param restRequest
     * @return  body:OrgResponse
     */
    @RequestMapping(value = "/getOrgList",method = RequestMethod.POST)
    public RestResponse getOrgList(@RequestBody @Valid RestRequest<GetOrgListRequestDto> restRequest) throws ClassNotFoundException {
        log.info("查询组织机构列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader()==null || restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgApi.getOrgList(restRequest);
        log.info("查询组织机构列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 提交组织机构
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/commitOrg",method = RequestMethod.POST)
    public RestResponse commitOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("提交组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgApi.commitOrg(restRequest);
        log.info("提交组织机构出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 撤销组织机构
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/abolishOrg",method = RequestMethod.POST)
    public RestResponse abolishOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("撤销组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgApi.abolishOrg(restRequest);
        log.info("撤销组织机构出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 审核组织机构
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/auditOrg",method = RequestMethod.POST)
    public RestResponse auditOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("审核组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgApi.auditOrg(restRequest);
        log.info("审核组织机构出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反审核组织机构
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/reverseAuditOrg",method = RequestMethod.POST)
    public RestResponse reverseAuditOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("反审核组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgApi.reverseAuditOrg(restRequest);
        log.info("反审核组织机构出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除组织机构
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/deleteOrg",method = RequestMethod.POST)
    public RestResponse deleteOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("删除组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgApi.deleteOrg(restRequest);
        log.info("删除组织机构出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用组织机构
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/forbiddenOrg",method = RequestMethod.POST)
    public RestResponse forbiddenOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("禁用组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgApi.forbiddenOrg(restRequest);
        log.info("禁用组织机构出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用组织机构
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/unForbiddenOrg",method = RequestMethod.POST)
    public RestResponse unForbiddenOrg(@RequestBody @Valid RestRequest<UpdateOrgDataStateDto> restRequest) {
        log.info("反禁用组织机构入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgApi.unForbiddenOrg(restRequest);
        log.info("反禁用组织机构出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据id查看组织机构详情
     * @param restRequest
     * @return body:TAuOrganization
     */
    @RequestMapping(value = "/getOrgInfoById",method = RequestMethod.POST)
    public RestResponse getOrgInfoById(@RequestBody @Valid RestRequest<OrgInfoRequestDto> restRequest){
        log.info("查看组织机构详情入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgApi.getOrgInfoById(restRequest);
        log.info("查看组织机构详情出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看可选组织（新增组织方案初显数据，不分页）
     * @return
     */
    @RequestMapping(value = "/getSelectableOrgList",method = RequestMethod.POST)
    public RestResponse getSelectableOrgList() {
        RestResponse restResponse = new RestResponse();
        restResponse = orgApi.getSelectableOrgList();
        log.info("查看可选组织出参："+ JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 查询法人组织
     * @return
     */
    @RequestMapping(value = "/getLegalPersonOrgList",method = RequestMethod.POST)
    public RestResponse getLegalPersonOrgList() {
        RestResponse restResponse = new RestResponse();
        restResponse = orgApi.getLegalPersonOrgList();
        log.info("查看法人组织出参："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

}
