package com.vosung.auapi.client.api;
import com.vosung.auapi.client.dto.requestdto.employee.UNPostEmployeeRequest;
import com.vosung.auapi.client.dto.requestdto.post.PostListRequest;
import com.vosung.auapi.client.dto.requestdto.post.PostRequestDto;
import com.vosung.auapi.client.dto.requestdto.post.UpdatePostStatusDto;
import com.vosung.auapi.client.hystrix.PostApiHystrix;
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
 * 员工任岗api
 * @Author 彬
 * @Date 2019/5/9
 */
@FeignClient(value = "vosung-au-app",configuration = FeignClientsConfiguration.class,fallback = PostApiHystrix.class)
public interface PostApi {

    /**
     * 保存任岗信息
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/postController/savePost",method = RequestMethod.POST)
    RestResponse savePost(@RequestBody @Valid RestRequest<PostRequestDto> restRequest);

    /**
     * 根据任岗id 查询员工任岗明细详情
     * @param postId
     * @return
     */
    @RequestMapping(value = "api/postController/getPostInfoByPostId",method = RequestMethod.POST)
    RestResponse getPostInfoByPostId(@RequestParam("postId") Integer postId);

    /**
     * 提交员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/postController/commitPost",method = RequestMethod.POST)
    RestResponse commitPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest);

    /**
     * 撤销员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/postController/abolishPost",method = RequestMethod.POST)
    RestResponse abolishPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest);

    /**
     * 审核员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/postController/auditPost",method = RequestMethod.POST)
    RestResponse auditPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest);

    /**
     * 反审核员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/postController/reverseAuditPost",method = RequestMethod.POST)
    RestResponse reverseAuditPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest);

    /**
     * 删除员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/postController/deletePost",method = RequestMethod.POST)
    RestResponse deletePost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest);

    /**
     * 禁用员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/postController/forbiddenPost",method = RequestMethod.POST)
    RestResponse forbiddenPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest);

    /**
     * 反禁员工
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/postController/unForbiddenPost",method = RequestMethod.POST)
    RestResponse unForbiddenPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest);


    /**
     * 根据岗位id 获取员工任岗list
     * @param positionId
     * @return
     */
    @RequestMapping(value = "api/postController/getPostListByPositionId",method = RequestMethod.POST)
    RestResponse getPostListByPositionId(@RequestParam("positionId") Integer positionId);

    /**
     * （多条件）查询任岗列表，目前只支持全量分页
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/postController/getPostList",method = RequestMethod.POST)
    RestResponse getPostList(@RequestBody RestRequest<PostListRequest> restRequest);


    /**
     * 获取未任岗的员工列表
     * @param restRequest
     * @return
     */
    @RequestMapping(value = "api/postController/getEmployeeListNoPost",method = RequestMethod.POST)
    RestResponse getEmployeeListNoPost(@RequestBody @Valid RestRequest<UNPostEmployeeRequest> restRequest);
}
