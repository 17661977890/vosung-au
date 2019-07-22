package com.vosung.authapp.position.service;

import com.vosung.auapi.client.dto.requestdto.position.AddPositionDto;
import com.vosung.auapi.client.dto.requestdto.position.PositionListRequestDto;
import com.vosung.auapi.client.dto.requestdto.position.PositionRequestDto;
import com.vosung.auapi.client.dto.requestdto.position.UpdatePositionStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 岗位业务层
 * @Author 彬
 * @Date 2019/5/10
 */
public interface PositionService {
    /**
     * 保存岗位信息
     * @param positionRequestDto
     * @return
     */
    RestResponse savePosition(PositionRequestDto positionRequestDto);

    /**
     * 查看岗位详情
     * @param positionId
     * @return
     */
    RestResponse getPositionInfoById(Integer positionId);

    /**
     * 查看岗位列表
     * @param restRequest
     * @return
     */
    RestResponse getPositionList(RestRequest<PositionListRequestDto> restRequest);

    /**
     * 提交岗位
     * @param body
     * @return
     */
    RestResponse commitPosition(UpdatePositionStatusDto body);
    /**
     * 撤销岗位
     * @param body
     * @return
     */
    RestResponse abolishPosition(UpdatePositionStatusDto body);
    /**
     * 审核岗位
     * @param body
     * @return
     */
    RestResponse auditPosition(UpdatePositionStatusDto body);
    /**
     * 反审核岗位
     * @param body
     * @return
     */
    RestResponse reverseAuditPosition(UpdatePositionStatusDto body);
    /**
     * 删除岗位
     * @param body
     * @return
     */
    RestResponse deletePosition(UpdatePositionStatusDto body);
    /**
     * 禁用岗位
     * @param body
     * @return
     */
    RestResponse forbiddenPosition(UpdatePositionStatusDto body);
    /**
     * 反禁用岗位
     * @param body
     * @return
     */
    RestResponse unForbiddenPosition(UpdatePositionStatusDto body);

    /**
     * 岗位汇报体系中的新增岗位（知道汇报类型的前提：直接确定上下级关系）
     * @param addPositionDtoList
     * @return
     */
    RestResponse addNewPositionAndRelation(List<AddPositionDto> addPositionDtoList);

    /**
     * 查询岗位树结构，或根据汇报类型  岗位名称搜索
     * @param reportType
     * @param positionName
     * @param id
     * @return
     */
    RestResponse queryPositionThreeAndSearch(String reportType, String positionName,Integer id);

    /**
     * 修改岗位上级
     * @param PositionId
     * @param SuperId
     * @return
     */
    RestResponse updateSuperIdByPositionId(Integer PositionId, Integer SuperId);
}
