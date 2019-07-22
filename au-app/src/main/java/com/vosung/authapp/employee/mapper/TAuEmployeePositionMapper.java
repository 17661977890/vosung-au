package com.vosung.authapp.employee.mapper;

import com.vosung.auapi.client.dto.requestdto.post.PostListRequest;
import com.vosung.auapi.client.dto.responsedto.post.EmployeePostDto;
import com.vosung.auapi.client.dto.responsedto.post.PostListDto;
import com.vosung.auapi.client.entity.TAuEmployeePosition;
import com.vosung.auapi.client.entity.TAuEmployeePositionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TAuEmployeePositionMapper {
    int countByExample(TAuEmployeePositionExample example);

    int deleteByExample(TAuEmployeePositionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAuEmployeePosition record);

    int insertSelective(TAuEmployeePosition record);

    List<TAuEmployeePosition> selectByExample(TAuEmployeePositionExample example);

    TAuEmployeePosition selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAuEmployeePosition record, @Param("example") TAuEmployeePositionExample example);

    int updateByExample(@Param("record") TAuEmployeePosition record, @Param("example") TAuEmployeePositionExample example);

    int updateByPrimaryKeySelective(TAuEmployeePosition record);

    int updateByPrimaryKey(TAuEmployeePosition record);

    //新增关联查询
    EmployeePostDto getEmployeePostByPostId(Integer postId);

    /**
     * 获取任岗列表
     * @param postListRequest
     * @return
     */
    List<PostListDto> getPostList(PostListRequest postListRequest);

    /**
     * 根据岗位id 查询已任岗列表
     * @param positionId
     * @return
     */
    List<PostListDto> getPostListByPositionId(Integer positionId);
}