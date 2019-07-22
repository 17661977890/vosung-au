package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.position.AddPositionDto;
import com.vosung.auapi.client.dto.requestdto.position.PositionListRequestDto;
import com.vosung.auapi.client.dto.requestdto.position.PositionRequestDto;
import com.vosung.auapi.client.dto.requestdto.position.UpdatePositionStatusDto;
import com.vosung.auapi.client.hystrix.PositionApiHystrix;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 岗位api
 * @Author 彬
 * @Date 2019/5/13
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = PositionApiHystrix.class)
public interface PositionApi {

    /**
     * 保存岗位信息
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/positionController/savePosition",method = RequestMethod.POST)
    RestResponse savePosition(@RequestBody @Valid RestRequest<PositionRequestDto> restRequest);

    /**
     * 查看岗位详情
     * @param positionId
     * @return
     */
    @RequestMapping(value = "api/positionController/getPositionInfoById",method = RequestMethod.POST)
    RestResponse getPositionInfoById(@RequestParam("positionId") Integer positionId);

    /**
     * 查看岗位列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/positionController/getPositionList",method = RequestMethod.POST)
    RestResponse getPositionList(@RequestBody @Valid RestRequest<PositionListRequestDto> restRequest);

    /**
     * 提交岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/positionController/commitPosition",method = RequestMethod.POST)
    RestResponse commitPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest);
    /**
     * 撤销岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/positionController/abolishPosition",method = RequestMethod.POST)
    RestResponse abolishPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest);
    /**
     * 审核岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/positionController/auditPosition",method = RequestMethod.POST)
    RestResponse auditPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest);
    /**
     * 反审核岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/positionController/reverseAuditPosition",method = RequestMethod.POST)
    RestResponse reverseAuditPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest);
    /**
     * 删除岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/positionController/deletePosition",method = RequestMethod.POST)
    RestResponse deletePosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest);
    /**
     * 禁用岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/positionController/forbiddenPosition",method = RequestMethod.POST)
    RestResponse forbiddenPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest);
    /**
     * 反禁用岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/positionController/unForbiddenPosition",method = RequestMethod.POST)
    RestResponse unForbiddenPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest);

    /**
     * 岗位汇报体系中新增岗位
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/positionController/addNewPositionAndRelation",method = RequestMethod.POST)
    RestResponse addNewPositionAndRelation(@RequestBody @Valid  RestRequest<List<AddPositionDto>> restRequest);


    /**
     * 查询岗位树结构，或根据汇报类型  岗位名称搜索
     * @param reportType
     * @param positionName
     * @param id
     * @return
     */
    @GetMapping("api/positionController/queryPositionThreeAndSearch")
    RestResponse queryPositionThreeAndSearch(
            @RequestParam(value = "reportType",required = false)String reportType,
            @RequestParam(value = "positionName",required = false)String positionName,
            @RequestParam(value = "id",defaultValue = "0")Integer id);

    /**
     * 修改岗位上级
     * @param positionId
     * @param superId
     * @return
     */
    @PostMapping("api/positionController/updateSuperIdByPositionId/{positionId}/{superId}")
    RestResponse updateSuperIdByPositionId(
            @PathVariable("positionId")Integer positionId,
            @PathVariable("superId")Integer superId);
}
