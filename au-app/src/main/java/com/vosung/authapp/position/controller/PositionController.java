package com.vosung.authapp.position.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.PositionApi;
import com.vosung.auapi.client.dto.requestdto.position.AddPositionDto;
import com.vosung.auapi.client.dto.requestdto.position.PositionListRequestDto;
import com.vosung.auapi.client.dto.requestdto.position.PositionRequestDto;
import com.vosung.auapi.client.dto.requestdto.position.UpdatePositionStatusDto;
import com.vosung.auapi.client.dto.requestdto.post.UpdatePostStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 岗位web层
 * @Author 彬
 * @Date 2019/5/13
 */
@Slf4j
@RequestMapping("auth/positionController")
@RestController
public class PositionController extends BaseController{
    @Autowired
    private PositionApi positionApi;


    /**
     * 保存岗位信息
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/savePosition",method = RequestMethod.POST)
    public RestResponse savePosition(@RequestBody @Valid RestRequest<PositionRequestDto> restRequest){
        log.info("保存岗位信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionApi.savePosition(restRequest);
        log.info("保存岗位信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看岗位信息详情
     * @param positionId
     * @return
     */
    @RequestMapping(value = "/getPositionInfoById",method = RequestMethod.POST)
    public RestResponse getPositionInfoById(@RequestParam("positionId") Integer positionId) {
        log.info("查看岗位信息详情入参 ："+ positionId);
        RestResponse restResponse = new RestResponse();
        restResponse = positionApi.getPositionInfoById(positionId);
        log.info("查看岗位信息详情出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查看岗位列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getPositionList",method = RequestMethod.POST)
    public RestResponse getPositionList(@RequestBody @Valid RestRequest<PositionListRequestDto> restRequest) {
        log.info("查看岗位列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionApi.getPositionList(restRequest);
        log.info("查看岗位列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 提交岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/commitPosition",method = RequestMethod.POST)
    public RestResponse commitPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("提交岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionApi.commitPosition(restRequest);
        log.info("提交岗位出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 撤销岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/abolishPosition",method = RequestMethod.POST)
    public RestResponse abolishPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("撤销岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionApi.abolishPosition(restRequest);
        log.info("撤销岗位出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 审核岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/auditPosition",method = RequestMethod.POST)
    public RestResponse auditPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("审核岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionApi.auditPosition(restRequest);
        log.info("审核岗位出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反审核岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/reverseAuditPosition",method = RequestMethod.POST)
    public RestResponse reverseAuditPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("反审核岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionApi.reverseAuditPosition(restRequest);
        log.info("反审核岗位出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/deletePosition",method = RequestMethod.POST)
    public RestResponse deletePosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("删除岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionApi.deletePosition(restRequest);
        log.info("删除岗位出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/forbiddenPosition",method = RequestMethod.POST)
    public RestResponse forbiddenPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("禁用岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionApi.forbiddenPosition(restRequest);
        log.info("禁用岗位出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/unForbiddenPosition",method = RequestMethod.POST)
    public RestResponse unForbiddenPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        log.info("反禁用岗位入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionApi.unForbiddenPosition(restRequest);
        log.info("反禁用岗位出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 岗位汇报体系添加岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/addNewPositionAndRelation",method = RequestMethod.POST)
    public RestResponse addNewPositionAndRelation(@RequestBody @Valid RestRequest<List<AddPositionDto>> restRequest) {
        log.info("添加岗位信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = positionApi.addNewPositionAndRelation(restRequest);
        log.info("添加岗位信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查询岗位树结构，或根据汇报类型  岗位名称搜索
     * @param reportType
     * @param positionName
     * @param id
     * @return
     */
    @GetMapping("/queryPositionThreeAndSearch")
    public RestResponse queryPositionThreeAndSearch(
            @RequestParam(value = "reportType",required = false)String reportType,
            @RequestParam(value = "positionName",required = false)String positionName,
            @RequestParam(value = "id",defaultValue = "0")Integer id){
        log.info("查询岗位树结构入参："+ reportType+","+positionName+","+id);
        RestResponse restResponse = positionApi.queryPositionThreeAndSearch(reportType, positionName, id);
        log.info("查询岗位树结构出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 修改岗位上级
     * @param positionId
     * @param superId
     * @return
     */
    @PostMapping("/updateSuperIdByPositionId/{positionId}/{superId}")
    public RestResponse updateSuperIdByPositionId(
            @PathVariable("positionId")Integer positionId,
            @PathVariable("superId")Integer superId){
        log.info("修改上级岗位入参："+positionId+","+superId);
        RestResponse restResponse = positionApi.updateSuperIdByPositionId(positionId,superId);
        log.info("修改上级岗位出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

}
