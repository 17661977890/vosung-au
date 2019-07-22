package com.vosung.authapp.sysdata.service;

import com.vosung.auapi.client.dto.requestdto.sysdict.SysDictItemRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;


/**
 * 字典项接口
 * @Author 彬
 * @Date 2019/4/29
 */
public interface DictItemService {


    RestResponse getDictItemListByDictCode(RestRequest<SysDictItemRequestDto> restRequest);
}
