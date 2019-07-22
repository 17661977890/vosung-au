package com.vosung.authapp.employee.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.employee.EmployeeListRequest;
import com.vosung.auapi.client.dto.requestdto.employee.PostDto;
import com.vosung.auapi.client.dto.requestdto.employee.UNPostEmployeeRequest;
import com.vosung.auapi.client.dto.requestdto.post.PostListRequest;
import com.vosung.auapi.client.dto.requestdto.post.PostRequestDto;
import com.vosung.auapi.client.dto.requestdto.post.UpdatePostStatusDto;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.employee.EmployeeResponse;
import com.vosung.auapi.client.dto.responsedto.employee.EmployeeResponseDto;
import com.vosung.auapi.client.dto.responsedto.post.EmployeePostDto;
import com.vosung.auapi.client.dto.responsedto.post.PostListDto;
import com.vosung.auapi.client.dto.responsedto.post.PostListResponse;
import com.vosung.auapi.client.dto.responsedto.post.PostResponseDto;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.*;
import com.vosung.authapp.common.enums.SysDictEnum;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.dept.mapper.TAuDepartemntMapper;
import com.vosung.authapp.employee.mapper.TAuEmployeeMapper;
import com.vosung.authapp.employee.mapper.TAuEmployeePositionMapper;
import com.vosung.authapp.employee.service.EmployeeService;
import com.vosung.authapp.employee.service.PostService;
import com.vosung.authapp.org.mapper.TAuOrganizationMapper;
import com.vosung.authapp.position.mapper.TAuPositionMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 员工任岗业务实现类
 * @Author 彬
 * @Date 2019/5/9
 */
@Slf4j
@Service
public class PostServiceImpl extends BaseServiceImpl implements PostService {
    @Autowired
    private TAuEmployeePositionMapper tAuEmployeePositionMapper;

    @Autowired
    private TAuOrganizationMapper tAuOrganizationMapper;
    @Autowired
    private TAuDepartemntMapper tAuDepartemntMapper;
    @Autowired
    private TAuPositionMapper tAuPositionMapper;
    @Autowired
    private TAuEmployeeMapper tAuEmployeeMapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserHolder userHolder;


    /**
     * 保存任岗信息
     * @param postRequestDto
     * @return
     */
    @Override
    public RestResponse savePost(PostRequestDto postRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuEmployeePosition tAuEmployeePosition = new TAuEmployeePosition();
        Integer record = 0;
        try {
            BeanUtils.copyProperties(tAuEmployeePosition,postRequestDto);
            if(postRequestDto.getId()==null){
                //新增任岗信息----之前检查是否重复任岗（根据岗位id和员工id）
                checkIsExist(postRequestDto.getDirectorPositionId(),postRequestDto.getEmployeeId());
                tAuEmployeePosition.setDataState(DataStateCode.CREATED);
                tAuEmployeePosition.setCreateTime(new Date());
                tAuEmployeePosition.setCreateUser(loginUserId);
                tAuEmployeePosition.setUpdateUser(loginUserId);
                tAuEmployeePosition.setUpdateTime(new Date());
                record = tAuEmployeePositionMapper.insertSelective(tAuEmployeePosition);
            }else {
                //修改任岗信息----之前检查是否重复任岗（根据岗位id和员工id）
                checkIsExist(postRequestDto.getDirectorPositionId(),postRequestDto.getEmployeeId());
                tAuEmployeePosition.setUpdateUser(loginUserId);
                tAuEmployeePosition.setUpdateTime(new Date());
                record = tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(record==1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("保存员工任岗信息成功");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 根据任岗id查询员工任岗明细
     * @param postId
     * @return
     */
    @Override
    public RestResponse getPostInfoByPostId(Integer postId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        PostResponseDto postResponseDto = new PostResponseDto();
        TAuEmployeePosition tAuEmployeePosition = tAuEmployeePositionMapper.selectByPrimaryKey(postId);
        try {
            BeanUtils.copyProperties(postResponseDto,tAuEmployeePosition);
            //出参部分信息赋值
            //组织
            TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(tAuEmployeePosition.getWorkOrgId());
            if (tAuOrganization == null){
                throw new BusinessException("3011111116",getMessage("3011111116"));
            }
            postResponseDto.setCreateOrgName(tAuOrganization.getOrgName());
            postResponseDto.setUseOrgName(tAuOrganization.getOrgName());
            //部门
            TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(tAuEmployeePosition.getBelongDeptId());
            if (tAuDepartemnt == null){
                throw new BusinessException("3011111123",getMessage("3011111123"));
            }
            postResponseDto.setBelongDeptName(tAuDepartemnt.getDeptName());
            //岗位
            TAuPosition tAuPosition = tAuPositionMapper.selectByPrimaryKey(tAuEmployeePosition.getDirectorPositionId());
            if (tAuPosition == null){
                throw new BusinessException("3011111212",getMessage("3011111212"));
            }
            postResponseDto.setDirectorPositionName(tAuPosition.getPositionName());
            TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(tAuEmployeePosition.getEmployeeId());
            if(tAuEmployee == null){
                throw new BusinessException("3011111213",getMessage("3011111213"));
            }
            postResponseDto.setEmployeeCode(tAuEmployee.getEmployeeCode());
            postResponseDto.setEmployeeName(tAuEmployee.getEmployeeName());
            postResponseDto.setEmail(tAuEmployee.getEmail());
            postResponseDto.setMobile(tAuEmployee.getMobile());
            postResponseDto.setPhone(tAuEmployee.getPhone());
            postResponseDto.setSex(tAuEmployee.getSex());
            postResponseDto.setSummary(tAuEmployee.getSummary());
            postResponseDto.setContactAddress(tAuEmployee.getContactAddress());
            postResponseDto.setImage(tAuEmployee.getImage());
            //该组织下和员工所任岗列表
            RestResponse restResponse = employeeService.getPostListByEmployeeIdOrgId(tAuEmployeePosition.getEmployeeId(),tAuEmployeePosition.getWorkOrgId());
            List<PostDto> postDtoList = (List<PostDto>)restResponse.getBody();
            postResponseDto.setPostDtoList(postDtoList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询任岗明细详情成功");
        return RestResponse.resultSuccess(postResponseDto,restResponseHeader);
    }

    /**
     * 提交员工任岗
     * @param body
     * @return
     */
    @Override
    public RestResponse commitPost(UpdatePostStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] postIdArray = body.getPostIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //提交
        for (int i = 0; i < postIdArray.length; i++) {
            EmployeePostDto employeePostDto = tAuEmployeePositionMapper.getEmployeePostByPostId(Integer.valueOf(postIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = employeePostDto.getDataState();
            Boolean a = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) ||
                    DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(employeePostDto.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && a){
                map.put("message","员工编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(employeePostDto.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !a){
                map.put("message","员工编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map = new HashMap();
                    map.put("message","员工编号为:"+employeePostDto.getEmployeeCode()+" 的员工任刚信息；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","员工编号为："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(a){
                //符合要求，修改状态为审核中
                TAuEmployeePosition tAuEmployeePosition = new TAuEmployeePosition();
                tAuEmployeePosition.setId(employeePostDto.getId());
                tAuEmployeePosition.setDataState(DataStateCode.AUDIT_IN_PROGRESS);
                tAuEmployeePosition.setUpdateUser(loginUserId);
                tAuEmployeePosition.setUpdateTime(new Date());
                tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                map.put("message","员工编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；提交成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","员工编号为:"+employeePostDto.getEmployeeCode()+" 的员工任刚信息；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","员工编号为："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 撤销员工任岗
     * @param body
     * @return
     */
    @Override
    public RestResponse abolishPost(UpdatePostStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] postIdArray = body.getPostIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //撤销
        for (int i = 0; i < postIdArray.length; i++) {
            EmployeePostDto employeePostDto = tAuEmployeePositionMapper.getEmployeePostByPostId(Integer.valueOf(postIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();

            String dataState = employeePostDto.getDataState();
            Boolean b = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(employeePostDto.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y)&&b){
                map.put("message","员工编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(employeePostDto.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y)&&!b){
                map.put("message","员工编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","员工编号为："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","员工编号为："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(b){
                //符合要求，修改状态为重新审核
                TAuEmployeePosition tAuEmployeePosition = new TAuEmployeePosition();
                tAuEmployeePosition.setId(employeePostDto.getId());
                tAuEmployeePosition.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuEmployeePosition.setUpdateUser(loginUserId);
                tAuEmployeePosition.setUpdateTime(new Date());
                tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                map.put("message","员工编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；撤销成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","员工编号为："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","员工编号为："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 审核员工任岗
     * @param body
     * @return
     */
    @Override
    public RestResponse auditPost(UpdatePostStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] postIdArray = body.getPostIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //审核
        for (int i = 0; i < postIdArray.length; i++) {
            EmployeePostDto employeePostDto = tAuEmployeePositionMapper.getEmployeePostByPostId(Integer.valueOf(postIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = employeePostDto.getDataState();
            Boolean c = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(employeePostDto.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && c){
                map.put("message","员工编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(employeePostDto.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !c){
                map.put("message","员工编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","编号为："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(c){
                //符合要求，修改状态为已审核
                TAuEmployeePosition tAuEmployeePosition = new TAuEmployeePosition();
                tAuEmployeePosition.setId(employeePostDto.getId());
                tAuEmployeePosition.setDataState(DataStateCode.AUDITED);
                tAuEmployeePosition.setReviewUser(loginUserId);
                tAuEmployeePosition.setReviewTime(new Date());
                tAuEmployeePosition.setUpdateUser(loginUserId);
                tAuEmployeePosition.setUpdateTime(new Date());
                tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                map.put("message","编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","编号为："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 反审核员工任岗
     * @param body
     * @return
     */
    @Override
    public RestResponse reverseAuditPost(UpdatePostStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] postIdArray = body.getPostIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //反审核
        for (int i = 0; i < postIdArray.length; i++) {
            EmployeePostDto employeePostDto = tAuEmployeePositionMapper.getEmployeePostByPostId(Integer.valueOf(postIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = employeePostDto.getDataState();
            Boolean d = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState) || DataStateCode.AUDITED.equals(dataState);
            if(employeePostDto.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) &&d){
                map.put("message","员工编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(employeePostDto.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !d){
                map.put("message","员工编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","员工编号为："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(d){
                //符合要求，修改状态为重新审核
                TAuEmployeePosition tAuEmployeePosition = new TAuEmployeePosition();
                tAuEmployeePosition.setId(employeePostDto.getId());
                tAuEmployeePosition.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuEmployeePosition.setUpdateUser(loginUserId);
                tAuEmployeePosition.setUpdateTime(new Date());
                tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                map.put("message","员工编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；反审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","员工编号为："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 删除员工任岗
     * @param body
     * @return
     */
    @Override
    public RestResponse deletePost(UpdatePostStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] postIdArray = body.getPostIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //删除
        for (int i = 0; i < postIdArray.length; i++) {
            EmployeePostDto employeePostDto = tAuEmployeePositionMapper.getEmployeePostByPostId(Integer.valueOf(postIdArray[i]));
            Map map = new HashMap();
            String dataState = employeePostDto.getDataState();
            Boolean e = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) || DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(e){
                //符合要求，修改删除标识为"Y"
                TAuEmployeePosition tAuEmployeePosition = new TAuEmployeePosition();
                tAuEmployeePosition.setId(employeePostDto.getId());
                tAuEmployeePosition.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                tAuEmployeePosition.setUpdateUser(loginUserId);
                tAuEmployeePosition.setUpdateTime(new Date());
                tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                map.put("message","员工编号为 ："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；删除成功");
                map.put("code",ConstantUtil.SUCCESS);

            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.AUDITED.equals(dataState) || DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","员工编号为："+employeePostDto.getEmployeeCode()+" 的员工任刚信息；删除失败，只有创建和暂存状态的员工任刚信息才可删除");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 禁用员工任岗
     * @param body
     * @return
     */
    @Override
    public RestResponse forbiddenPost(UpdatePostStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] postIdArray = body.getPostIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //禁用
        for (int i = 0; i < postIdArray.length; i++) {
            EmployeePostDto employeePostDto = tAuEmployeePositionMapper.getEmployeePostByPostId(Integer.valueOf(postIdArray[i]));
            Map map = new HashMap();
            if (ConstantUtil.YES_OR_NO_N.equals(employeePostDto.getProhibitState())) {
                TAuEmployeePosition tAuEmployeePosition = new TAuEmployeePosition();
                tAuEmployeePosition.setId(employeePostDto.getId());
                tAuEmployeePosition.setProhibitState(ConstantUtil.YES_OR_NO_Y);
                tAuEmployeePosition.setProhibitUser(loginUserId);
                tAuEmployeePosition.setProhibitTime(new Date());
                tAuEmployeePosition.setUpdateTime(new Date());
                tAuEmployeePosition.setUpdateUser(loginUserId);
                tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                map.put("message", "员工编号为：" + employeePostDto.getEmployeeCode() + " 的员工任刚信息禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
            } else {
                map.put("message", "员工编号为：" + employeePostDto.getEmployeeCode() + " 的员工任刚信息已被禁用");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     *反禁员工任岗
     * @param body
     * @return
     */
    @Override
    public RestResponse unForbiddenPost(UpdatePostStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] postIdArray = body.getPostIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //反禁用
        for (int i = 0; i < postIdArray.length; i++) {
            EmployeePostDto employeePostDto = tAuEmployeePositionMapper.getEmployeePostByPostId(Integer.valueOf(postIdArray[i]));
            Map map = new HashMap();
            if (ConstantUtil.YES_OR_NO_Y.equals(employeePostDto.getProhibitState())) {
                TAuEmployeePosition tAuEmployeePosition = new TAuEmployeePosition();
                tAuEmployeePosition.setId(employeePostDto.getId());
                tAuEmployeePosition.setProhibitState(ConstantUtil.YES_OR_NO_N);
                tAuEmployeePosition.setUpdateTime(new Date());
                tAuEmployeePosition.setUpdateUser(loginUserId);
                tAuEmployeePositionMapper.updateByPrimaryKeySelective(tAuEmployeePosition);
                map.put("message", "员工编号为：" + employeePostDto.getEmployeeCode() + " 的员工任刚信息反禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
            } else {
                map.put("message", "员工编号为：" + employeePostDto.getEmployeeCode() + " 的员工任刚信息已处于反禁用状态");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 根据就任岗位id 查询员工任岗list----------------弃用
     * @param positionId
     * @return
     */
    @Override
    public RestResponse getPostListByPositionId(Integer positionId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<PostListDto> postListDtoList = new ArrayList<>();
        TAuEmployeePositionExample example = new TAuEmployeePositionExample();
        TAuEmployeePositionExample.Criteria criteria = example.createCriteria();
        criteria.andDirectorPositionIdEqualTo(positionId);
        List<TAuEmployeePosition> tAuEmployeePositionList = tAuEmployeePositionMapper.selectByExample(example);
        for (TAuEmployeePosition t:tAuEmployeePositionList) {
            PostListDto postListDto = new PostListDto();
            try {
                BeanUtils.copyProperties(postListDto, t);
                //出参部分信息赋值
                //组织
                TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(t.getWorkOrgId());
                if (tAuOrganization == null) {
                    throw new BusinessException("3011111116", getMessage("3011111116"));
                }
                postListDto.setCreateOrgName(tAuOrganization.getOrgName());
                postListDto.setUseOrgName(tAuOrganization.getOrgName());
                //部门
                TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(t.getBelongDeptId());
                if (tAuDepartemnt == null) {
                    throw new BusinessException("3011111123", getMessage("3011111123"));
                }
                postListDto.setBelongDeptName(tAuDepartemnt.getDeptName());
                //岗位
                TAuPosition tAuPosition = tAuPositionMapper.selectByPrimaryKey(t.getDirectorPositionId());
                if (tAuPosition == null) {
                    throw new BusinessException("3011111212", getMessage("3011111212"));
                }
                postListDto.setDirectorPositionName(tAuPosition.getPositionName());
                TAuEmployee tAuEmployee = tAuEmployeeMapper.selectByPrimaryKey(t.getEmployeeId());
                if (tAuEmployee == null) {
                    throw new BusinessException("3011111213", getMessage("3011111213"));
                }
                postListDto.setEmployeeCode(tAuEmployee.getEmployeeCode());
                postListDto.setEmployeeName(tAuEmployee.getEmployeeName());
                postListDto.setFromSHr(tAuEmployee.getFromSHr());
                postListDto.setIsHrEmployee(tAuEmployee.getIsHrEmployee());
                postListDtoList.add(postListDto);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询对应岗位的任岗情况成功");
        return RestResponse.resultSuccess(postListDtoList,restResponseHeader);
    }

    /**
     * 多条件查询任岗列表（全量分页）
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getPostList(RestRequest<PostListRequest> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        PostListResponse postListResponse = new PostListResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(PostListDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();
            PageMethod.startPage(pageNum,pageSize,"id");
            List<PostListDto> postListDtoList = tAuEmployeePositionMapper.getPostList(restRequest.getBody());

            for (PostListDto postListDto: postListDtoList) {
               postListDto.setDataStateName((String) redisUtil.hget(SysDictEnum.DATA_STATE.getCode(),postListDto.getDataState()));
            }
            postListResponse.setColumnList(viewColumnList);
            postListResponse.setPageInfo(new PageInfo<>(postListDtoList));
            postListResponse.setSum((int) new PageInfo<>(postListDtoList).getTotal());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询员工任岗列表成功 ");
        return RestResponse.resultSuccess(postListResponse,restResponseHeader);
    }

    /**
     * 查询未任岗的员工列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getEmployeeListNoPost(RestRequest<UNPostEmployeeRequest> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        EmployeeResponse employeeResponse = new EmployeeResponse();
        List<PostListDto> postListDtoList = tAuEmployeePositionMapper.getPostListByPositionId(restRequest.getBody().getPositionId());
        List<Integer> employeeIds = new ArrayList<>();
        for (PostListDto postListDto: postListDtoList) {
            if(!employeeIds.contains(postListDto.getEmployeeId())){
                employeeIds.add(postListDto.getEmployeeId());
            }
        }
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(EmployeeResponseDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();
            PageMethod.startPage(pageNum,pageSize,"id");
            List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();
            if(CollectionUtils.isEmpty(employeeIds)){
                EmployeeListRequest employeeListRequest = new EmployeeListRequest();
                employeeResponseDtoList = tAuEmployeeMapper.getEmployeeList(employeeListRequest);
            }else {
                employeeResponseDtoList = tAuEmployeeMapper.getUNPostEmployeeList(employeeIds);
            }
            for (EmployeeResponseDto employeeResponseDto: employeeResponseDtoList) {
                employeeResponseDto.setDataStateName((String) redisUtil.hget(SysDictEnum.DATA_STATE.getCode(),employeeResponseDto.getDataState()));
                EmployeeResponseDto employeeResponseDto1 = tAuEmployeeMapper.getEmployeeMainPost(employeeResponseDto.getId());
                if(employeeResponseDto1!=null){
                    employeeResponseDto.setMainPositionName(employeeResponseDto1.getMainPositionName());
                    employeeResponseDto.setDirectorPositionStartDate(employeeResponseDto1.getDirectorPositionStartDate());
                }
            }

            employeeResponse.setColumnList(viewColumnList);
            employeeResponse.setPageInfo(new PageInfo<>(employeeResponseDtoList));
            employeeResponse.setSum((int) new PageInfo<>(employeeResponseDtoList).getTotal());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询该岗位下未任岗员工列表成功 ");
        return RestResponse.resultSuccess(employeeResponse,restResponseHeader);
    }

    /**
     * 检查是否重复任岗
     * @param positionId
     * @param employeeId
     */
    private void checkIsExist(Integer positionId, Integer employeeId){
        TAuEmployeePositionExample example = new TAuEmployeePositionExample();
        TAuEmployeePositionExample.Criteria criteria = example.createCriteria();
        criteria.andDirectorPositionIdEqualTo(positionId);
        criteria.andEmployeeIdEqualTo(employeeId);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuEmployeePosition> tAuEmployeePositionList =tAuEmployeePositionMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(tAuEmployeePositionList)){
            throw new BusinessException("3011111215",getMessage("3011111215"));
        }
    }
}
