package com.vosung.authapp.dept.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.DeptApi;
import com.vosung.auapi.client.dto.requestdto.dept.DeptInfoRequestDto;
import com.vosung.auapi.client.dto.requestdto.dept.DeptRequestDto;
import com.vosung.auapi.client.dto.requestdto.dept.GetDeptListRequestDto;
import com.vosung.auapi.client.dto.requestdto.dept.UpdateDeptDataStateDto;
import com.vosung.auapi.client.dto.requestdto.deptgroup.DeptGroupRequestDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 部门相关web层
 * @Author 彬
 * @Date 2019/4/24
 */
@Slf4j
@RestController
@RequestMapping("auth/deptController")
public class DeptController extends BaseController{
    @Autowired
    private DeptApi deptApi;

    /**
     * 保存部门信息
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/saveNewDept",method = RequestMethod.POST)
    public RestResponse saveNewDept(@RequestBody @Valid RestRequest<DeptRequestDto> restRequest){
        log.info("保存部门信息入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptApi.saveNewDept(restRequest);
        log.info("保存部门信息出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 部门列表分页展示
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getDeptList",method = RequestMethod.POST)
    public RestResponse getDeptList(@RequestBody @Valid RestRequest<GetDeptListRequestDto> restRequest){
        log.info("查询部门列表入参： "+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader()==null || restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptApi.getDeptList(restRequest);
        log.info("查询部门列表出参："+ JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 提交部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/commitDept",method = RequestMethod.POST)
    public RestResponse commitDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        log.info("提交部门入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptApi.commitDept(restRequest);
        log.info("提交部门出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 撤销部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/abolishDept",method = RequestMethod.POST)
    public RestResponse abolishDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        log.info("撤销部门入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptApi.abolishDept(restRequest);
        log.info("撤销部门出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 审核部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/auditDept",method = RequestMethod.POST)
    public RestResponse auditDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        log.info("审核部门入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptApi.auditDept(restRequest);
        log.info("审核部门出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反审核部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/reverseAuditDept",method = RequestMethod.POST)
    public RestResponse reverseAuditDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        log.info("反审核部门入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptApi.reverseAuditDept(restRequest);
        log.info("反审核部门出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/deleteDept",method = RequestMethod.POST)
    public RestResponse deleteDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        log.info("删除部门入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptApi.deleteDept(restRequest);
        log.info("删除部门出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/forbiddenDept",method = RequestMethod.POST)
    public RestResponse forbiddenDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        log.info("禁用部门入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptApi.forbiddenDept(restRequest);
        log.info("禁用部门出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用部门
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/unForbiddenDept",method = RequestMethod.POST)
    public RestResponse unForbiddenDept(@RequestBody @Valid RestRequest<UpdateDeptDataStateDto> restRequest) {
        log.info("反禁用部门入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptApi.unForbiddenDept(restRequest);
        log.info("反禁用部门出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据id查看部门详情
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getDeptInfoById",method = RequestMethod.POST)
    public RestResponse getDeptInfoById(@RequestBody @Valid RestRequest<DeptInfoRequestDto> restRequest) {
        log.info("根据id查看部门详情入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = deptApi.getDeptInfoById(restRequest);
        log.info("根据id查看部门详情出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }


}
