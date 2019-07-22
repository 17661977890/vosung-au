package com.vosung.authapp.common.basecore;

import com.vosung.authapp.common.message.MessageSourceService;

import javax.annotation.Resource;

/**
 * @Author 彬
 * @Date 2019/4/23
 */
public class BaseApiImpl {
    @Resource
    private MessageSourceService messageSourceService;

    protected String getMessage(String code){
        return messageSourceService.getMessage(code);
    }
}
