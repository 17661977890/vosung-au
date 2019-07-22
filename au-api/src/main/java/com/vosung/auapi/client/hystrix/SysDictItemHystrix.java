package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.SysDictItemApi;
import com.vosung.auapi.client.dto.requestdto.sysdict.SysDictItemRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Author 彬
 * @Date 2019/4/29
 */
@Component
public class SysDictItemHystrix extends BaseHystrix implements SysDictItemApi{
    @Override
    public RestResponse getDictItemListByDictCode(@RequestBody @Valid RestRequest<SysDictItemRequestDto> restRequest) {
        return getError();
    }
}
