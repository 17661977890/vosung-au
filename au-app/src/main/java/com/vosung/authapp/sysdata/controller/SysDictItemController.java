package com.vosung.authapp.sysdata.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.SysDictItemApi;
import com.vosung.auapi.client.dto.requestdto.sysdict.SysDictItemRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Author 彬
 * @Date 2019/4/29
 */
@Slf4j
@RequestMapping("auth/SysDictItemController")
@RestController
public class SysDictItemController extends BaseController{
    @Autowired
    private SysDictItemApi sysDictItemApi;

    /**
     * 查询字典项列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getDictItemListByDictCode",method = RequestMethod.POST)
    public RestResponse getDictItemListByDictCode(@RequestBody @Valid RestRequest<SysDictItemRequestDto> restRequest) {
        log.info("查询字典项入参:"+JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader()==null || restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = sysDictItemApi.getDictItemListByDictCode(restRequest);
        log.info("查询字典项出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }
}
