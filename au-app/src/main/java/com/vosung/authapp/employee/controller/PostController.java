package com.vosung.authapp.employee.controller;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.PostApi;
import com.vosung.auapi.client.dto.requestdto.employee.UNPostEmployeeRequest;
import com.vosung.auapi.client.dto.requestdto.employee.UpdateEmployeeStatusDto;
import com.vosung.auapi.client.dto.requestdto.post.PostListRequest;
import com.vosung.auapi.client.dto.requestdto.post.PostRequestDto;
import com.vosung.auapi.client.dto.requestdto.post.UpdatePostStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseController;
import com.vosung.authapp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 员工任岗web层
 * @Author 彬
 * @Date 2019/5/9
 */
@Slf4j
@RequestMapping("auth/postController")
@RestController
public class PostController extends BaseController {
    @Autowired
    private PostApi postApi;


    /**
     * 保存任刚信息-------检查否重复任岗
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/savePost",method = RequestMethod.POST)
    public RestResponse savePost(@RequestBody @Valid RestRequest<PostRequestDto> restRequest) {
        log.info("保存员工任岗信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postApi.savePost(restRequest);
        log.info("保存员工任岗信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 根据任岗id  查询员工任岗明细详情
     * @param postId
     * @return
     */
    @RequestMapping(value = "/getPostInfoByPostId",method = RequestMethod.POST)
    public RestResponse getPostInfoByPostId(@RequestParam("postId") Integer postId) {
        log.info("查询员工任岗明细详情入参 ："+ postId);
        RestResponse restResponse = new RestResponse();
        restResponse = postApi.getPostInfoByPostId(postId);
        log.info("查询员工任岗明细详情出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 提交员工任岗
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/commitPost",method = RequestMethod.POST)
    public RestResponse commitPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("提交员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postApi.commitPost(restRequest);
        log.info("提交员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 撤销员工任岗
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/abolishPost",method = RequestMethod.POST)
    public RestResponse abolishPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("撤销员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postApi.abolishPost(restRequest);
        log.info("撤销员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 审核员工任岗
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/auditPost",method = RequestMethod.POST)
    public RestResponse auditPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("审核员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postApi.auditPost(restRequest);
        log.info("审核员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反审核员工任岗
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/reverseAuditPost",method = RequestMethod.POST)
    public RestResponse reverseAuditPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("反审核员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postApi.reverseAuditPost(restRequest);
        log.info("反审核员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除员工任岗
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/deletePost",method = RequestMethod.POST)
    public RestResponse deletePost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("删除员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postApi.deletePost(restRequest);
        log.info("删除员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用员工任岗
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/forbiddenPost",method = RequestMethod.POST)
    public RestResponse forbiddenPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("禁用员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postApi.forbiddenPost(restRequest);
        log.info("禁用员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用员工任岗
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/unForbiddenPost",method = RequestMethod.POST)
    public RestResponse unForbiddenPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("反禁用员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postApi.unForbiddenPost(restRequest);
        log.info("反禁用员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 根据岗位id 查询任岗list
     * @param positionId
     * @return
     */
    @RequestMapping(value = "/getPostListByPositionId",method = RequestMethod.POST)
    public RestResponse getPostListByPositionId(@RequestParam("positionId") Integer positionId) {
        log.info("根据岗位id ,查询员工任岗list入参 ："+ positionId);
        RestResponse restResponse = new RestResponse();
        restResponse = postApi.getPostListByPositionId(positionId);
        log.info("根据岗位id ,查询员工任岗list出参 ："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 全量分页查询任岗列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getPostList",method = RequestMethod.POST)
    public RestResponse getPostList(@RequestBody RestRequest<PostListRequest> restRequest) {
        log.info("查询员工任岗列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postApi.getPostList(restRequest);
        log.info("查询员工任岗列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 查询未任岗员工列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "/getEmployeeListNoPost",method = RequestMethod.POST)
    public RestResponse getEmployeeListNoPost(@RequestBody @Valid RestRequest<UNPostEmployeeRequest> restRequest) {
        log.info("查询未任岗员工列表入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postApi.getEmployeeListNoPost(restRequest);
        log.info("查询未任岗员工列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }
}
