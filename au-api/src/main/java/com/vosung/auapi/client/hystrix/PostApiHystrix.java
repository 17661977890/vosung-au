package com.vosung.auapi.client.hystrix;

import com.vosung.auapi.client.api.PostApi;
import com.vosung.auapi.client.dto.requestdto.employee.UNPostEmployeeRequest;
import com.vosung.auapi.client.dto.requestdto.post.PostListRequest;
import com.vosung.auapi.client.dto.requestdto.post.PostRequestDto;
import com.vosung.auapi.client.dto.requestdto.post.UpdatePostStatusDto;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 员工任岗熔断处理
 * @Author 彬
 * @Date 2019/5/9
 */
@Component
public class PostApiHystrix extends BaseHystrix implements PostApi{

    @Override
    public RestResponse savePost(@RequestBody @Valid RestRequest<PostRequestDto> restRequest) {
        return getError();
    }


    @Override
    public RestResponse getPostInfoByPostId(@RequestParam("postId") Integer postId) {
        return getError();
    }
    @Override
    public RestResponse commitPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse abolishPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse auditPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse reverseAuditPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse deletePost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse forbiddenPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse unForbiddenPost(@RequestBody @Valid RestRequest<UpdatePostStatusDto> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getPostListByPositionId(Integer positionId) {
        return getError();
    }

    @Override
    public RestResponse getPostList(RestRequest<PostListRequest> restRequest) {
        return getError();
    }

    @Override
    public RestResponse getEmployeeListNoPost(@Valid RestRequest<UNPostEmployeeRequest> restRequest) {
        return getError();
    }
}
