package com.vosung.authapp.org.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.OrgRelationSchemeApi;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.GetSchemeInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.GetSchemeListRequestDto;
import com.vosung.auapi.client.dto.requestdto.orgrelationscheme.OrgRelationSchemeRequestDto;
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
 * 组织机构隶属方案关系 web
 * @Author 彬
 * @Date 2019/4/26
 */
@Slf4j
@RestController
@RequestMapping("auth/OrgRelationSchemeController")
public class OrgRelationSchemeController extends BaseController{

    @Autowired
    private OrgRelationSchemeApi orgRelationSchemeApi;

    /**
     * 保存组织机构隶属方案关系
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/saveOrgRelationScheme",method = RequestMethod.POST)
    public RestResponse saveOrgRelationScheme(@RequestBody @Valid RestRequest<OrgRelationSchemeRequestDto> restRequest){
        log.info("保存组织机构隶属方案关系入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationSchemeApi.saveOrgRelationScheme(restRequest);
        log.info("保存组织机构隶属方案关系出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查询组织机构方案
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getOrgSchemeList",method = RequestMethod.POST)
    public RestResponse getOrgSchemeList(@RequestBody RestRequest<GetSchemeListRequestDto> restRequest) {
        log.info("查询组织机构方案列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationSchemeApi.getOrgSchemeList(restRequest);
        log.info("查询组织机构方案列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 提交组织机构隶属方案----同步修改组织关系的数据状态
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/commitScheme",method = RequestMethod.POST)
    public RestResponse commitScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        log.info("提交组织机构隶属方案入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationSchemeApi.commitScheme(restRequest);
        log.info("提交组织机构隶属方案出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 撤销组织机构隶属方案----同步修改组织关系的数据状态
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/abolishScheme",method = RequestMethod.POST)
    public RestResponse abolishScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        log.info("撤销组织机构隶属方案入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationSchemeApi.abolishScheme(restRequest);
        log.info("撤销组织机构隶属方案出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 审核组织机构隶属方案----同步修改组织关系的数据状态
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/auditScheme",method = RequestMethod.POST)
    public RestResponse auditScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        log.info("审核组织机构隶属方案入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationSchemeApi.auditScheme(restRequest);
        log.info("审核组织机构隶属方案出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反审核组织机构隶属方案----同步修改组织关系的数据状态
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/reverseAuditScheme",method = RequestMethod.POST)
    public RestResponse reverseAuditScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        log.info("反审核组织机构隶属方案入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationSchemeApi.reverseAuditScheme(restRequest);
        log.info("反审核组织机构隶属方案出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除组织机构隶属方案----同步修改组织关系的数据状态
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/deleteScheme",method = RequestMethod.POST)
    public RestResponse deleteScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        log.info("删除组织机构隶属方案入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationSchemeApi.deleteScheme(restRequest);
        log.info("删除组织机构隶属方案出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用组织机构隶属方案----同步修改组织关系的数据状态
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/forbiddenScheme",method = RequestMethod.POST)
    public RestResponse forbiddenScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        log.info("禁用组织机构隶属方案入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationSchemeApi.forbiddenScheme(restRequest);
        log.info("禁用组织机构隶属方案出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用组织机构隶属方案----同步修改组织关系的数据状态
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/unForbiddenScheme",method = RequestMethod.POST)
    public RestResponse unForbiddenScheme(@RequestBody @Valid RestRequest<UpdateSchemeRequestDto> restRequest) {
        log.info("反禁用组织机构隶属方案入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationSchemeApi.unForbiddenScheme(restRequest);
        log.info("反禁用组织机构隶属方案出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据id查看组织方案详情
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getSchemeInfoById",method = RequestMethod.POST)
    public RestResponse getSchemeInfoById(@RequestBody @Valid RestRequest<GetSchemeInfoRequestDto> restRequest) {
        log.info("根据id查询组织关系方案入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = orgRelationSchemeApi.getSchemeInfoById(restRequest);
        log.info("根据id查询组织关系方案出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
}
