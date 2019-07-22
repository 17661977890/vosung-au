package com.vosung.authapp.employee.service;

import com.vosung.auapi.client.dto.requestdto.employee.EmployeeListRequest;
import com.vosung.auapi.client.dto.requestdto.employee.UNPostEmployeeRequest;
import com.vosung.auapi.client.dto.requestdto.post.PostListRequest;
import com.vosung.auapi.client.dto.requestdto.post.PostRequestDto;
import com.vosung.auapi.client.dto.requestdto.post.UpdatePostStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import io.swagger.models.auth.In;

import javax.validation.Valid;

/**
 * 员工任岗信息业务层
 * @Author 彬
 * @Date 2019/5/9
 */
public interface PostService {
    /**
     * 保存任岗信息
     * @param postRequestDto
     * @return
     */
    RestResponse savePost(PostRequestDto postRequestDto);

    /**
     * 根据任岗id 查询员工任具体岗明细
     * @param postId
     * @return
     */
    RestResponse getPostInfoByPostId(Integer postId);


    /**
     * 提交员工任岗
     * @param body
     * @return
     */
    RestResponse commitPost(UpdatePostStatusDto body);

    /**
     * 撤销员工任岗
     * @param body
     * @return
     */
    RestResponse abolishPost(UpdatePostStatusDto body);

    /**
     * 审核员工任岗
     * @param body
     * @return
     */
    RestResponse auditPost(UpdatePostStatusDto body);

    /**
     * 反审核员工任岗
     * @param body
     * @return
     */
    RestResponse reverseAuditPost(UpdatePostStatusDto body);

    /**
     * 删除员工任岗
     * @param body
     * @return
     */
    RestResponse deletePost(UpdatePostStatusDto body);

    /**
     * 禁用员工任岗
     * @param body
     * @return
     */
    RestResponse forbiddenPost(UpdatePostStatusDto body);

    /**
     * 反禁员工任岗
     * @param body
     * @return
     */
    RestResponse unForbiddenPost(UpdatePostStatusDto body);

    /**
     * 根据就任岗位id，查询员工任岗list
     * @param positionId
     * @return
     */
    RestResponse getPostListByPositionId(Integer positionId);

    /**
     * （多条件）查询任岗列表，目前只支持全量分页
     * @param restRequest
     * @return
     */
    RestResponse getPostList(RestRequest<PostListRequest> restRequest);

    /**
     * 获取未任岗的员工列表
     * @param restRequest
     * @return
     */
    RestResponse getEmployeeListNoPost(RestRequest<UNPostEmployeeRequest> restRequest);
}
