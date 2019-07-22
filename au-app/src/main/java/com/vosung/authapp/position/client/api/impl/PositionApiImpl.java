package com.vosung.authapp.position.client.api.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.PositionApi;
import com.vosung.auapi.client.dto.requestdto.position.AddPositionDto;
import com.vosung.auapi.client.dto.requestdto.position.PositionListRequestDto;
import com.vosung.auapi.client.dto.requestdto.position.PositionRequestDto;
import com.vosung.auapi.client.dto.requestdto.position.UpdatePositionStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.position.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 岗位api实现类
 * @Author 彬
 * @Date 2019/5/13
 */
@Slf4j
@RestController
public class PositionApiImpl extends BaseApiImpl implements PositionApi {
    @Autowired
    private PositionService positionService;

    /**
     * 保存岗位信息
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse savePosition(@RequestBody @Valid RestRequest<PositionRequestDto> restRequest) {
        log.info("保存岗位信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionService.savePosition(restRequest.getBody());
        log.info("保存岗位信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看岗位信息详情
     * @param positionId
     * @return
     */
    @Override
    public RestResponse getPositionInfoById(Integer positionId) {
        log.info("查看岗位信息详情入参 ："+ positionId);
        RestResponse restResponse = new RestResponse();
        restResponse = positionService.getPositionInfoById(positionId);
        log.info("查看岗位信息详情出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看岗位列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getPositionList(@RequestBody @Valid RestRequest<PositionListRequestDto> restRequest) {
        log.info("查询岗位列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionService.getPositionList(restRequest);
        log.info("查询岗位列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 提交岗位
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse commitPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("提交岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionService.commitPosition(restRequest.getBody());
        log.info("提交岗位出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 撤销岗位
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse abolishPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("撤销岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionService.abolishPosition(restRequest.getBody());
        log.info("撤销岗位出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 审核岗位
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse auditPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("审核岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionService.auditPosition(restRequest.getBody());
        log.info("审核岗位出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反审核岗位
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse reverseAuditPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("反审核岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionService.reverseAuditPosition(restRequest.getBody());
        log.info("反审核岗位出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除岗位
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse deletePosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("删除岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionService.deletePosition(restRequest.getBody());
        log.info("删除岗位出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用岗位
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse forbiddenPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("禁用岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionService.forbiddenPosition(restRequest.getBody());
        log.info("禁用岗位出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用岗位
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse unForbiddenPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("反禁用岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionService.unForbiddenPosition(restRequest.getBody());
        log.info("反禁用岗位出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 岗位汇报体系添加岗位
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse addNewPositionAndRelation(@RequestBody @Valid RestRequest<List<AddPositionDto>> restRequest) {
        log.info("添加岗位信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionService.addNewPositionAndRelation(restRequest.getBody());
        log.info("添加岗位信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 岗位树结构展示及条件搜索
     * @param reportType
     * @param positionName
     * @param id
     * @return
     */
    @Override
    public RestResponse queryPositionThreeAndSearch(String reportType, String positionName, Integer id) {
        log.info("查询岗位树结构入参："+ reportType+","+positionName+","+id);
        RestResponse restResponse = positionService.queryPositionThreeAndSearch(reportType, positionName, id);
        log.info("查询岗位树结构出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 修改岗位上级
     * @param positionId
     * @param superId
     * @return
     */
    @Override
    public RestResponse updateSuperIdByPositionId(Integer positionId, Integer superId) {
        log.info("修改上级岗位入参："+positionId+","+superId);
        RestResponse restResponse = positionService.updateSuperIdByPositionId(positionId,superId);
        log.info("修改上级岗位出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }
}
