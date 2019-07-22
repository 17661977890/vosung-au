package com.vosung.authapp.sysdata.client.api.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.SysDictItemApi;
import com.vosung.auapi.client.dto.requestdto.sysdict.SysDictItemRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.sysdata.service.DictItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 字典项接口实现类
 * @Author 彬
 * @Date 2019/4/29
 */
@Slf4j
@RestController
public class SysDictItemApiImpl extends BaseApiImpl implements SysDictItemApi {

    @Autowired
    private DictItemService dictItemService;

    /**
     * 根据字典code获取字典项
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getDictItemListByDictCode(@RequestBody @Valid RestRequest<SysDictItemRequestDto> restRequest) {
        log.info("查询字典项入参:"+JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader()==null || restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = dictItemService.getDictItemListByDictCode(restRequest);
        log.info("查询字典项出参："+ JSON.toJSONString(restResponse));
        return restResponse;
    }
}
