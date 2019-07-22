package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.DeptGroupApi;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 部门分组熔断处理
 * @Author 彬
 * @Date 2019/4/26
 */
@Component
public class DeptGroupHystrix extends BaseHystrix implements DeptGroupApi {

    @Override
    public RestResponse saveNewDeptGroup(@RequestBody @Valid RestRequest<DeptGroupRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getDeptGroupById(@RequestBody @Valid RestRequest<DeptGroupInfoRequestDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getDeptGroupTree() {
        return getError();
    }
}
