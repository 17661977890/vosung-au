package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.PositionApi;
import com.vosung.auapi.client.dto.requestdto.position.AddPositionDto;
import com.vosung.auapi.client.dto.requestdto.position.PositionListRequestDto;
import com.vosung.auapi.client.dto.requestdto.position.PositionRequestDto;
import com.vosung.auapi.client.dto.requestdto.position.UpdatePositionStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * 岗位api熔断处理
 * @Author 彬
 * @Date 2019/5/13
 */
@Component
public class PositionApiHystrix extends BaseHystrix implements PositionApi{
    @Override
    public RestResponse savePosition(@RequestBody @Valid RestRequest<PositionRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getPositionInfoById(@RequestParam("positionId") Integer positionId) {
        return getError();
    }

    @Override
    public RestResponse getPositionList(@RequestBody @Valid RestRequest<PositionListRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse commitPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse abolishPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse auditPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse reverseAuditPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse deletePosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse forbiddenPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse unForbiddenPosition(@RequestBody @Valid RestRequest<UpdatePositionStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse addNewPositionAndRelation(@Valid RestRequest<List<AddPositionDto>> restRequest) {
        return getError();
    }

    @Override
    public RestResponse queryPositionThreeAndSearch(String reportType, String positionName, Integer id) { return getError(); }

    @Override
    public RestResponse updateSuperIdByPositionId(Integer positionId, Integer superId) { return getError(); }
}
