package com.vosung.authapp.employee.client.api.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.api.PostApi;
import com.vosung.auapi.client.dto.requestdto.employee.UNPostEmployeeRequest;
import com.vosung.auapi.client.dto.requestdto.post.PostListRequest;
import com.vosung.auapi.client.dto.requestdto.post.PostRequestDto;
import com.vosung.auapi.client.dto.requestdto.post.UpdatePostStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.authapp.common.basecore.BaseApiImpl;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.employee.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author 彬
 * @Date 2019/5/9
 */
@Slf4j
@RestController
public class PostApiImpl extends BaseApiImpl implements PostApi {
    @Autowired
    private PostService postService;

    /**
     * 保存任刚信息
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse savePost(@RequestBody @Valid RestRequest<PostRequestDto> restRequest) {
        log.info("保存员工任岗信息入参 ："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postService.savePost(restRequest.getBody());
        log.info("保存员工任岗信息出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据任岗id  查询员工任岗明细详情
     * @param postId
     * @return
     */
    @Override
    public RestResponse getPostInfoByPostId(Integer postId) {
        log.info("查询员工任岗明细详情入参 ："+ postId);
        RestResponse restResponse = new RestResponse();
        restResponse = postService.getPostInfoByPostId(postId);
        log.info("查询员工任岗明细详情出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }


    /**
     * 提交员工任岗
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse commitPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("提交员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postService.commitPost(restRequest.getBody());
        log.info("提交员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 撤销员工任岗
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse abolishPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("撤销员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postService.abolishPost(restRequest.getBody());
        log.info("撤销员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 审核员工任岗
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse auditPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("审核员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postService.auditPost(restRequest.getBody());
        log.info("审核员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反审核员工任岗
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse reverseAuditPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("反审核员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postService.reverseAuditPost(restRequest.getBody());
        log.info("反审核员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 删除员工任岗
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse deletePost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("删除员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postService.deletePost(restRequest.getBody());
        log.info("删除员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 禁用员工任岗
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse forbiddenPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("禁用员工任岗入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postService.forbiddenPost(restRequest.getBody());
        log.info("禁用员工任岗出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 反禁用员工
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse unForbiddenPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        log.info("反禁用员工入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody()==null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postService.unForbiddenPost(restRequest.getBody());
        log.info("反禁用员工出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 根据岗位id 查询任岗list
     * @param positionId
     * @return
     */
    @Override
    public RestResponse getPostListByPositionId(@RequestParam("positionId") Integer positionId) {
        log.info("根据岗位id ,查询员工任岗list入参 ："+ positionId);
        RestResponse restResponse = new RestResponse();
        restResponse = postService.getPostListByPositionId(positionId);
        log.info("根据岗位id ,查询员工任岗list出参："+JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 全量分页查询任岗列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getPostList(@RequestBody RestRequest<PostListRequest> restRequest) {
        log.info("查询员工任岗列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postService.getPostList(restRequest);
        log.info("查询员工任岗列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }

    /**
     * 查询未任岗员工列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getEmployeeListNoPost(@RequestBody @Valid RestRequest<UNPostEmployeeRequest> restRequest) {
        log.info("查询未任岗员工列表入参："+ JSON.toJSONString(restRequest));
        if(restRequest==null || restRequest.getBody() == null || restRequest.getHeader() == null){
            throw new BusinessException("3011111111",getMessage("3011111111"));
        }
        if(restRequest.getHeader().getPageNum() == null || restRequest.getHeader().getPageSize()==null){
            throw new BusinessException("3011111112",getMessage("3011111112"));
        }
        RestResponse restResponse = new RestResponse();
        restResponse = postService.getEmployeeListNoPost(restRequest);
        log.info("查询未任岗员工列表出参 ："+ JSON.toJSONString(restResponse));
        return restResponse;
    }
}
