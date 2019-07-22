package com.vosung.auapi.client.api;

import com.vosung.auapi.client.dto.requestdto.sysdict.SysDictItemRequestDto;
import com.vosung.auapi.client.hystrix.SysDictItemHystrix;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


/**
 * 数据字典项api
 * @Author 彬
 * @Date 2019/4/29
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = SysDictItemHystrix.class)
public interface SysDictItemApi {

    /**
     * 根据字典code获取字典项
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/dictItemController/getDictItemListByDictCode",method = RequestMethod.POST)
    RestResponse getDictItemListByDictCode(@RequestBody @Valid RestRequest<SysDictItemRequestDto> restRequest);
}
