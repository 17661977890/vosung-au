package com.vosung.authapp.position.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.position.*;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.position.PositionDownResponseDto;
import com.vosung.auapi.client.dto.responsedto.position.PositionListResponse;
import com.vosung.auapi.client.dto.responsedto.position.PositionResponseDto;
import com.vosung.auapi.client.dto.responsedto.position.PositionUpResponseDto;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.*;
import com.vosung.authapp.common.enums.SysDictEnum;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.dept.mapper.TAuDepartemntMapper;
import com.vosung.authapp.employee.mapper.TAuEmployeePositionMapper;
import com.vosung.authapp.org.mapper.TAuOrganizationMapper;
import com.vosung.authapp.position.mapper.TAuPositionMapper;
import com.vosung.authapp.position.mapper.TAuPositionRelationDownMapper;
import com.vosung.authapp.position.mapper.TAuPositionRelationUpMapper;
import com.vosung.authapp.position.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 *岗位业务实现类
 * @Author 彬
 * @Date 2019/5/10
 */
@Slf4j
@Service
public class PositionServiceImpl extends BaseServiceImpl implements PositionService {
    @Autowired
    private TAuPositionMapper tAuPositionMapper;
    @Autowired
    private TAuPositionRelationUpMapper relationUpMapper;
    @Autowired
    private TAuPositionRelationDownMapper relationDownMapper;
    @Autowired
    private TAuOrganizationMapper tAuOrganizationMapper;
    @Autowired
    private TAuDepartemntMapper tAuDepartemntMapper;
    @Autowired
    private TAuEmployeePositionMapper tAuEmployeePositionMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserHolder userHolder;

    /**
     * 保存岗位信息(上级汇报关系也进行操作)-----统一汇报类型上级只有一个，下级可以有多个
     * @param positionRequestDto
     * @return
     */
    @Transactional
    @Override
    public RestResponse savePosition(PositionRequestDto positionRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuPosition tAuPosition = new TAuPosition();
        Integer record = 0;
        try {
            BeanUtils.copyProperties(tAuPosition,positionRequestDto);
            if(positionRequestDto.getId()==null){
                //新增岗位，岗位名称判重
                checkIsExist(positionRequestDto.getUseOrgId(),positionRequestDto.getBelongDeptId(),positionRequestDto.getPositionName());
                tAuPosition.setDataState(DataStateCode.CREATED);
                tAuPosition.setCreateUser(loginUserId);
                tAuPosition.setUpdateUser(loginUserId);
                tAuPosition.setCreateTime(new Date());
                tAuPosition.setUpdateTime(new Date());
                //返回主键id,设置岗位编码并修改
                record = tAuPositionMapper.insertSelective(tAuPosition);
                if(tAuPosition.getPositionCode()==null){
                    tAuPosition.setPositionCode("GW_"+tAuPosition.getId());
                }
                record = tAuPositionMapper.updateByPrimaryKeySelective(tAuPosition);
                //新增岗位上级汇报关系--对应的上级岗位也会有该下级岗位
                if(CollectionUtils.isNotEmpty(positionRequestDto.getPositionUpDtoList())){
                    //必要参数校验，以及同一汇报类型上级汇报关系判重
                    validParam(positionRequestDto.getPositionUpDtoList(),tAuPosition.getId());
                    Integer count=0;
                    Integer count2=0;
                    for (PositionUpDto positionUpDto:positionRequestDto.getPositionUpDtoList()) {
                        //新增上级岗位
                        TAuPositionRelationUp tAuPositionRelationUp = new TAuPositionRelationUp();
                        tAuPositionRelationUp.setPositionId(tAuPosition.getId());
                        tAuPositionRelationUp.setSuperiorPositionId(positionUpDto.getSuperiorPositionId());
                        tAuPositionRelationUp.setAbateDate(positionUpDto.getAbateDate());
                        tAuPositionRelationUp.setEffectDate(positionUpDto.getEffectDate());
                        tAuPositionRelationUp.setSummary(positionUpDto.getSummary());
                        tAuPositionRelationUp.setReportType(positionUpDto.getReportType());
                        tAuPositionRelationUp.setCreateUser(loginUserId);
                        tAuPositionRelationUp.setUpdateUser(loginUserId);
                        tAuPositionRelationUp.setCreateTime(new Date());
                        tAuPositionRelationUp.setUpdateTime(new Date());
                        relationUpMapper.insertSelective(tAuPositionRelationUp);
                        count++;
                        //对应新增下级岗位
                        TAuPositionRelationDown tAuPositionRelationDown = new TAuPositionRelationDown();
                        tAuPositionRelationDown.setPositionId(positionUpDto.getSuperiorPositionId());
                        tAuPositionRelationDown.setDownPositionId(tAuPosition.getId());
                        tAuPositionRelationDown.setAbateDate(positionUpDto.getAbateDate());
                        tAuPositionRelationDown.setEffectDate(positionUpDto.getEffectDate());
                        tAuPositionRelationDown.setSummary(positionUpDto.getSummary());
                        tAuPositionRelationDown.setReportType(positionUpDto.getReportType());
                        tAuPositionRelationDown.setCreateUser(loginUserId);
                        tAuPositionRelationDown.setUpdateUser(loginUserId);
                        tAuPositionRelationDown.setCreateTime(new Date());
                        tAuPositionRelationDown.setUpdateTime(new Date());
                        relationDownMapper.insertSelective(tAuPositionRelationDown);
                        count2++;
                    }
                    log.info("新增岗位信息的上级汇报关系数量："+count+" 对应下级汇报关系数："+count2);
                }
            }else {
                //修改岗位，岗位名变化判重
                TAuPosition tAuPosition1 = tAuPositionMapper.selectByPrimaryKey(positionRequestDto.getId());
                if(tAuPosition1 == null){
                    throw new BusinessException("3011111212",getMessage("3011111212"));
                }
                if(!tAuPosition1.getPositionName().equalsIgnoreCase(positionRequestDto.getPositionName())){
                    //岗位名称变化，判重
                    checkIsExist(positionRequestDto.getUseOrgId(),positionRequestDto.getBelongDeptId(),positionRequestDto.getPositionName());
                }
                tAuPosition.setUpdateUser(loginUserId);
                tAuPosition.setUpdateTime(new Date());
                record = tAuPositionMapper.updateByPrimaryKeySelective(tAuPosition);
                //修改上级汇报关系
                updateRelationUpList(positionRequestDto.getId(),positionRequestDto.getPositionUpDtoList());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(record==1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("保存岗位信息成功");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 查看岗位详情---包含基本信息/上级汇报关系/下级汇报关系list
     * @param positionId
     * @return
     */
    @Override
    public RestResponse getPositionInfoById(Integer positionId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        PositionResponseDto positionResponseDto = new PositionResponseDto();
        //查基本信息
        TAuPosition tAuPosition = tAuPositionMapper.selectByPrimaryKey(positionId);
        if(tAuPosition==null){
            throw new BusinessException("3011111212",getMessage("3011111212"));
        }

            BeanUtils.copyProperties(positionResponseDto,tAuPosition);
            //附带信息赋值
            TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(tAuPosition.getCreateOrgId());
            if(tAuOrganization!=null){
                positionResponseDto.setCreateOrgName(tAuOrganization.getOrgName());
                positionResponseDto.setUseOrgName(tAuOrganization.getOrgName());
            }
            TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(tAuPosition.getBelongDeptId());
            if(tAuDepartemnt!=null){
                positionResponseDto.setBelongDeptName(tAuDepartemnt.getDeptName());
                positionResponseDto.setDeptFullName(tAuDepartemnt.getDeptFullName());
            }
            //查询上级汇报关系
            TAuPositionRelationUpExample example = new TAuPositionRelationUpExample();
            TAuPositionRelationUpExample.Criteria criteria = example.createCriteria();
            criteria.andPositionIdEqualTo(positionId);
            List<TAuPositionRelationUp> relationUps = relationUpMapper.selectByExample(example);
            List<PositionUpResponseDto> positionUpDtos = new ArrayList<>();
            for (TAuPositionRelationUp tAuPositionRelationUp:relationUps) {
                PositionUpResponseDto positionUpResponseDto = new PositionUpResponseDto();
                BeanUtils.copyProperties(positionUpResponseDto,tAuPositionRelationUp);
                TAuPosition tAuPosition1 = tAuPositionMapper.selectByPrimaryKey(tAuPositionRelationUp.getSuperiorPositionId());
                positionUpResponseDto.setPositionCode(tAuPosition1.getPositionCode());
                positionUpResponseDto.setPositionName(tAuPosition1.getPositionName());
                //附带信息赋值
                TAuOrganization tAuOrganization1 = tAuOrganizationMapper.selectByPrimaryKey(tAuPosition1.getCreateOrgId());
                if(tAuOrganization!=null){
                    positionUpResponseDto.setCreateOrgName(tAuOrganization1.getOrgName());
                    positionUpResponseDto.setUseOrgName(tAuOrganization1.getOrgName());
                }
                TAuDepartemnt tAuDepartemnt1 = tAuDepartemntMapper.selectByPrimaryKey(tAuPosition1.getBelongDeptId());
                if(tAuDepartemnt!=null){
                    positionUpResponseDto.setBelongDeptName(tAuDepartemnt1.getDeptName());
                }
                positionUpResponseDto.setReportTypeName((String) redisUtil.hget(SysDictEnum.REPORT_TYPE.getCode(),tAuPositionRelationUp.getReportType()));
                positionUpDtos.add(positionUpResponseDto);
            }
            positionResponseDto.setRelationUpList(positionUpDtos);
            //查询下级汇报关系
            TAuPositionRelationDownExample example1 = new TAuPositionRelationDownExample();
            TAuPositionRelationDownExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andPositionIdEqualTo(positionId);
            List<TAuPositionRelationDown> relationDowns = relationDownMapper.selectByExample(example1);
            List<PositionDownResponseDto> positionDownResponseDtos = new ArrayList<>();
            for (TAuPositionRelationDown tAuPositionRelationDown:relationDowns) {
                PositionDownResponseDto positionDownResponseDto = new PositionDownResponseDto();
                BeanUtils.copyProperties(positionDownResponseDto,tAuPositionRelationDown);
                TAuPosition tAuPosition1 = tAuPositionMapper.selectByPrimaryKey(positionDownResponseDto.getDownPositionId());
                positionDownResponseDto.setPositionCode(tAuPosition1.getPositionCode());
                positionDownResponseDto.setPositionName(tAuPosition1.getPositionName());
                //附带信息赋值
                TAuOrganization tAuOrganization1 = tAuOrganizationMapper.selectByPrimaryKey(tAuPosition1.getCreateOrgId());
                if(tAuOrganization!=null){
                    positionDownResponseDto.setCreateOrgName(tAuOrganization1.getOrgName());
                    positionDownResponseDto.setUseOrgName(tAuOrganization1.getOrgName());
                }
                TAuDepartemnt tAuDepartemnt1 = tAuDepartemntMapper.selectByPrimaryKey(tAuPosition1.getBelongDeptId());
                if(tAuDepartemnt!=null){
                    positionDownResponseDto.setBelongDeptName(tAuDepartemnt1.getDeptName());
                }
                positionDownResponseDto.setReportTypeName((String) redisUtil.hget(SysDictEnum.REPORT_TYPE.getCode(),tAuPositionRelationDown.getReportType()));
                positionDownResponseDtos.add(positionDownResponseDto);
            }
            positionResponseDto.setRelationDownList(positionDownResponseDtos);


        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查看岗位详情成功");
        return RestResponse.resultSuccess(positionResponseDto,restResponseHeader);
    }

    /**
     *岗位多列表查询
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getPositionList(RestRequest<PositionListRequestDto> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        PositionListResponse positionListResponse = new PositionListResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(PositionResponseDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();
            PageMethod.startPage(pageNum,pageSize,"id");
            List<PositionResponseDto> positionResponseDtos =  tAuPositionMapper.getPositionList(restRequest.getBody());

            for (PositionResponseDto positionResponseDto: positionResponseDtos) {
                positionResponseDto.setDataSateName((String) redisUtil.hget(SysDictEnum.DATA_STATE.getCode(),positionResponseDto.getDataState()));
            }
            Integer sum = tAuPositionMapper.getCount(restRequest.getBody());
            positionListResponse.setColumnList(viewColumnList);
            positionListResponse.setPageInfo(new PageInfo<>(positionResponseDtos));
            positionListResponse.setSum(sum);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询岗位列表成功 ");
        return RestResponse.resultSuccess(positionListResponse,restResponseHeader);
    }

    /**
     * 提交岗位
     * @param body
     * @return
     */
    @Override
    public RestResponse commitPosition(UpdatePositionStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] positionIdArray = body.getPositionIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //提交
        for (int i = 0; i < positionIdArray.length; i++) {
            TAuPosition tAuPosition = tAuPositionMapper.selectByPrimaryKey(Integer.valueOf(positionIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuPosition.getDataState();
            Boolean a = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) ||
                    DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(tAuPosition.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && a){
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuPosition.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !a){
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map = new HashMap();
                    map.put("message","岗位编码为:"+tAuPosition.getPositionCode()+" 的岗位；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(a){
                //符合要求，修改状态为审核中
                tAuPosition.setDataState(DataStateCode.AUDIT_IN_PROGRESS);
                tAuPosition.setUpdateUser(loginUserId);
                tAuPosition.setUpdateTime(new Date());
                tAuPositionMapper.updateByPrimaryKeySelective(tAuPosition);
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；提交成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","岗位编码为:"+tAuPosition.getPositionCode()+" 的岗位；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 撤销岗位
     * @param body
     * @return
     */
    @Override
    public RestResponse abolishPosition(UpdatePositionStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] positionIdArray = body.getPositionIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //提交
        for (int i = 0; i < positionIdArray.length; i++) {
            TAuPosition tAuPosition = tAuPositionMapper.selectByPrimaryKey(Integer.valueOf(positionIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuPosition.getDataState();
            Boolean b = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuPosition.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && b){
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuPosition.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !b){
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(b){
                //符合要求，修改状态为审核中
                tAuPosition.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuPosition.setUpdateUser(loginUserId);
                tAuPosition.setUpdateTime(new Date());
                tAuPositionMapper.updateByPrimaryKeySelective(tAuPosition);
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；撤销成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 审核岗位
     * @param body
     * @return
     */
    @Override
    public RestResponse auditPosition(UpdatePositionStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] positionIdArray = body.getPositionIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //提交
        for (int i = 0; i < positionIdArray.length; i++) {
            TAuPosition tAuPosition = tAuPositionMapper.selectByPrimaryKey(Integer.valueOf(positionIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuPosition.getDataState();
            Boolean c = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuPosition.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && c){
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuPosition.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !c){
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(c){
                //符合要求，修改状态为已审核
                tAuPosition.setDataState(DataStateCode.AUDITED);
                tAuPosition.setReviewUser(loginUserId);
                tAuPosition.setReviewTime(new Date());
                tAuPosition.setUpdateUser(loginUserId);
                tAuPosition.setUpdateTime(new Date());
                tAuPositionMapper.updateByPrimaryKeySelective(tAuPosition);
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 反审核岗位
     * @param body
     * @return
     */
    @Override
    public RestResponse reverseAuditPosition(UpdatePositionStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] positionIdArray = body.getPositionIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //提交
        for (int i = 0; i < positionIdArray.length; i++) {
            TAuPosition tAuPosition = tAuPositionMapper.selectByPrimaryKey(Integer.valueOf(positionIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuPosition.getDataState();
            Boolean d = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState) || DataStateCode.AUDITED.equals(dataState);
            if(tAuPosition.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) &&d){
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuPosition.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !d){
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(d){
                //符合要求，修改状态为重新审核
                tAuPosition.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuPosition.setUpdateUser(loginUserId);
                tAuPosition.setUpdateTime(new Date());
                tAuPositionMapper.updateByPrimaryKeySelective(tAuPosition);
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的员工任刚信息；反审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的员工任刚信息；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 删除岗位
     * @param body
     * @return
     */
    @Override
    public RestResponse deletePosition(UpdatePositionStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] positionIdArray = body.getPositionIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //提交
        for (int i = 0; i < positionIdArray.length; i++) {
            TAuPosition tAuPosition = tAuPositionMapper.selectByPrimaryKey(Integer.valueOf(positionIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuPosition.getDataState();
            Boolean e = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) || DataStateCode.REVIEW_AGAIN.equals(dataState);
            //检查岗位是否被员工使用。（员工任岗表：未删除，未禁用）
            Boolean e1 = getPositionIsUse(Integer.valueOf(positionIdArray[i]),1);
            if(e1 && e){
                map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位被员工使用，请检查");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(e1 && !e){
                map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位被员工使用，请检查");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.AUDITED.equals(dataState) || DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map = new HashMap();
                    map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位；删除失败，只有创建和暂存状态的岗位才可删除");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(e){
                //符合要求，修改删除标识为"Y"
                tAuPosition.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                tAuPosition.setUpdateUser(loginUserId);
                tAuPosition.setUpdateTime(new Date());
                tAuPositionMapper.updateByPrimaryKeySelective(tAuPosition);
                map.put("message","岗位编码为 ："+tAuPosition.getPositionCode()+" 的岗位；删除成功");
                map.put("code",ConstantUtil.SUCCESS);

            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.AUDITED.equals(dataState) || DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位；删除失败，只有创建和暂存状态的岗位才可删除");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 禁用岗位
     * @param body
     * @return
     */
    @Override
    public RestResponse forbiddenPosition(UpdatePositionStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] positionIdArray = body.getPositionIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //提交
        for (int i = 0; i < positionIdArray.length; i++) {
            TAuPosition tAuPosition = tAuPositionMapper.selectByPrimaryKey(Integer.valueOf(positionIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            Boolean q = ConstantUtil.YES_OR_NO_N.equals(tAuPosition.getProhibitState());
            // 检查岗位是否存在有效员工
            Boolean q1 = getPositionIsUse(Integer.valueOf(positionIdArray[i]),2);
            if(q1){
                map.put("message","岗位编码为："+tAuPosition.getPositionCode()+" 的岗位存在有效员工");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if (q) {
                tAuPosition.setProhibitState(ConstantUtil.YES_OR_NO_Y);
                tAuPosition.setProhibitUser(loginUserId);
                tAuPosition.setProhibitTime(new Date());
                tAuPosition.setUpdateTime(new Date());
                tAuPosition.setUpdateUser(loginUserId);
                tAuPositionMapper.updateByPrimaryKeySelective(tAuPosition);
                map.put("message", "岗位编码为：" + tAuPosition.getPositionCode() + " 的岗位禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
            } else {
                map.put("message", "岗位编码为：" + tAuPosition.getPositionCode() + " 的岗位已被禁用");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 反禁用岗位
     * @param body
     * @return
     */
    @Override
    public RestResponse unForbiddenPosition(UpdatePositionStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] positionIdArray = body.getPositionIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        //提交
        for (int i = 0; i < positionIdArray.length; i++) {
            TAuPosition tAuPosition = tAuPositionMapper.selectByPrimaryKey(Integer.valueOf(positionIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            if (ConstantUtil.YES_OR_NO_Y.equals(tAuPosition.getProhibitState())) {
                tAuPosition.setProhibitState(ConstantUtil.YES_OR_NO_N);
                tAuPosition.setUpdateTime(new Date());
                tAuPosition.setUpdateUser(loginUserId);
                tAuPositionMapper.updateByPrimaryKeySelective(tAuPosition);
                map.put("message", "岗位编码为：" + tAuPosition.getPositionCode()  + " 的岗位反禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
            } else {
                map.put("message", "岗位编码为：" +tAuPosition.getPositionCode() + " 的岗位已处于反禁用状态");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 添加岗位(存在汇报类型，上下级汇报关系也保存)
     * @param addPositionDtos
     * @return
     */
    @Transactional
    @Override
    public RestResponse addNewPositionAndRelation(List<AddPositionDto> addPositionDtos) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuPosition tAuPosition = new TAuPosition();
        Integer record = 0;
        for (AddPositionDto addPositionDto:addPositionDtos){
            try {
                org.apache.commons.beanutils.BeanUtils.copyProperties(tAuPosition,addPositionDto);
                //新增岗位，岗位名称判重
                checkIsExist(addPositionDto.getUseOrgId(),addPositionDto.getBelongDeptId(),addPositionDto.getPositionName());
                tAuPosition.setDataState(DataStateCode.CREATED);
                tAuPosition.setCreateUser(loginUserId);
                tAuPosition.setUpdateUser(loginUserId);
                tAuPosition.setCreateTime(new Date());
                tAuPosition.setUpdateTime(new Date());
                //返回主键id,设置岗位编码并修改
                tAuPositionMapper.insertSelective(tAuPosition);
                tAuPosition.setPositionCode("GW_"+tAuPosition.getId());
                tAuPositionMapper.updateByPrimaryKeySelective(tAuPosition);
                record++;
                //新增岗位上级汇报关系--对应的上级岗位也会有该下级岗位
                if(addPositionDto.getSuperiorPositionId()!=null){
                    //新增上级岗位
                    TAuPositionRelationUp tAuPositionRelationUp = new TAuPositionRelationUp();
                    tAuPositionRelationUp.setPositionId(tAuPosition.getId());
                    tAuPositionRelationUp.setSuperiorPositionId(addPositionDto.getSuperiorPositionId());
                    tAuPositionRelationUp.setAbateDate(addPositionDto.getAbateDate());
                    tAuPositionRelationUp.setEffectDate(addPositionDto.getEffectDate());
                    tAuPositionRelationUp.setSummary(addPositionDto.getSummary());
                    tAuPositionRelationUp.setReportType(addPositionDto.getReportType());
                    tAuPositionRelationUp.setCreateUser(1);
                    tAuPositionRelationUp.setUpdateUser(1);
                    tAuPositionRelationUp.setCreateTime(new Date());
                    tAuPositionRelationUp.setUpdateTime(new Date());
                    relationUpMapper.insertSelective(tAuPositionRelationUp);
                    //对应新增下级岗位
                    TAuPositionRelationDown tAuPositionRelationDown = new TAuPositionRelationDown();
                    tAuPositionRelationDown.setPositionId(addPositionDto.getSuperiorPositionId());
                    tAuPositionRelationDown.setDownPositionId(tAuPosition.getId());
                    tAuPositionRelationDown.setAbateDate(addPositionDto.getAbateDate());
                    tAuPositionRelationDown.setEffectDate(addPositionDto.getEffectDate());
                    tAuPositionRelationDown.setSummary(addPositionDto.getSummary());
                    tAuPositionRelationDown.setReportType(addPositionDto.getReportType());
                    tAuPositionRelationDown.setCreateUser(1);
                    tAuPositionRelationDown.setUpdateUser(1);
                    tAuPositionRelationDown.setCreateTime(new Date());
                    tAuPositionRelationDown.setUpdateTime(new Date());
                    relationDownMapper.insertSelective(tAuPositionRelationDown);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }

        if(record>=1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("添加岗位信息成功");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }



    /**
     * 对同组织部门下的岗位名进行判重
     * @param useOrgId
     * @param belongDeptId
     * @param positionName
     */
    private void checkIsExist(Integer useOrgId,Integer belongDeptId,String positionName){
        TAuPositionExample example = new TAuPositionExample();
        TAuPositionExample.Criteria criteria = example.createCriteria();
        criteria.andUseOrgIdEqualTo(useOrgId);
        criteria.andBelongDeptIdEqualTo(belongDeptId);
        criteria.andPositionNameEqualTo(positionName);
        List<TAuPosition> tAuPositionList = tAuPositionMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(tAuPositionList)){
            throw new BusinessException("3011111214",getMessage("3011111214"));
        }
    }

    /**
     * 参数校验
     * @param relationUps
     */
    private void validParam(List<PositionUpDto> relationUps,Integer positionId){
        List<Map> messageList = new ArrayList<>();
        List<String> reportTypes = new ArrayList<>();
        Integer count = 0;
        String reportType = null;
        for (int i =0;i<relationUps.size();i++){
            if(relationUps.get(i).getSuperiorPositionId()==null){
                Map map = new HashMap();
                map.put("message","岗位信息单据体，第"+i+1+"行字段岗位编码是必填项");
                messageList.add(map);
            }
            if(relationUps.get(i).getReportType()==null){
                Map map = new HashMap();
                map.put("message","岗位信息单据体，第"+i+1+"行字段汇报类型是必填项");
                messageList.add(map);
            }
            if(!reportTypes.contains(relationUps.get(i).getReportType())){
                reportTypes.add(relationUps.get(i).getReportType());
            }else {
                reportType = relationUps.get(i).getReportType();
                count++;
            }
            if(count>0){
                Map map = new HashMap();
                map.put("message",reportType+"该汇报类型上级汇报关系应只有一次");
                messageList.add(map);
            }

        }
        if(!CollectionUtils.isEmpty(messageList)){
            throw new BusinessException(ConstantUtil.ERROR,messageList.toString());
        }
    }

    /**
     * 修改任岗列表信息
     * @param positionId
     * @param positionUpDtoList
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void updateRelationUpList(Integer positionId,List<PositionUpDto> positionUpDtoList) throws InvocationTargetException, IllegalAccessException {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        //获取该岗位的上级岗位
        TAuPositionRelationUpExample example = new TAuPositionRelationUpExample();
        TAuPositionRelationUpExample.Criteria criteria =example.createCriteria();
        criteria.andPositionIdEqualTo(positionId);
        List<TAuPositionRelationUp> relationUps = relationUpMapper.selectByExample(example);
        List<Integer> relationUpIds = new ArrayList<>();
        //岗位作为下级岗位对应的数据
        TAuPositionRelationDownExample example1 = new TAuPositionRelationDownExample();
        TAuPositionRelationDownExample.Criteria criteria1 =example1.createCriteria();
        criteria1.andDownPositionIdEqualTo(positionId);
        List<TAuPositionRelationDown> relationDowns = relationDownMapper.selectByExample(example1);
        if(!CollectionUtils.isEmpty(relationUps)){
            //该岗位本来有上级岗位
            //(1)传参集合为空，删除原有数据
            if(CollectionUtils.isEmpty(positionUpDtoList)){
                for (TAuPositionRelationUp tAuPositionRelationUp: relationUps) {
                    tAuPositionRelationUp.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                    relationUpMapper.updateByPrimaryKey(tAuPositionRelationUp);
                    log.info("=======之前存在，传参为空，逻辑删除之前有的上级岗位汇报关系数据=====");
                }
                for (TAuPositionRelationDown tAuPositionRelationDown: relationDowns) {
                    tAuPositionRelationDown.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                    relationDownMapper.updateByPrimaryKey(tAuPositionRelationDown);
                    log.info("========同步逻辑删除之前有的对应下级岗位汇报关系数据==========");
                }
            }
            //(2)传参集合不为空，校验参数，遍历任岗信息---> 有id | 有id和没有id | 没有id
            validParam(positionUpDtoList,positionId);
            for (PositionUpDto upDto:positionUpDtoList) {
                if(upDto.getId()==null){
                    //没有id 新增上级汇报关系
                    TAuPositionRelationUp tAuPositionRelationUp = new TAuPositionRelationUp();
                    BeanUtils.copyProperties(tAuPositionRelationUp,upDto);
                    tAuPositionRelationUp.setPositionId(positionId);
                    tAuPositionRelationUp.setCreateUser(loginUserId);
                    tAuPositionRelationUp.setUpdateUser(loginUserId);
                    tAuPositionRelationUp.setCreateTime(new Date());
                    tAuPositionRelationUp.setUpdateTime(new Date());
                    Integer count = relationUpMapper.insertSelective(tAuPositionRelationUp);
                    //同步新增下级汇报关系
                    TAuPositionRelationDown tAuPositionRelationDown = new TAuPositionRelationDown();
                    BeanUtils.copyProperties(tAuPositionRelationDown,upDto);
                    tAuPositionRelationDown.setPositionId(upDto.getSuperiorPositionId());
                    tAuPositionRelationDown.setDownPositionId(positionId);;
                    tAuPositionRelationDown.setCreateUser(loginUserId);
                    tAuPositionRelationDown.setUpdateUser(loginUserId);
                    tAuPositionRelationDown.setCreateTime(new Date());
                    tAuPositionRelationDown.setUpdateTime(new Date());
                    Integer count2 = relationDownMapper.insertSelective(tAuPositionRelationDown);
                    log.info("==============修改岗位时，同步修改上级汇报信息和下级岗位汇报信息，此处新增数据，上级数:"+count+"下级数："+count2);
                }
                if(upDto.getId()!=null){
                    //有id，与原有数据比较多少情况
                    relationUpIds.add(upDto.getId());
                }
            }
            for (TAuPositionRelationUp tAuPositionRelationUp: relationUps) {
                if(!relationUpIds.contains(tAuPositionRelationUp.getId())){
                    //不包含以前有的，逻辑删除之前有的上级岗位数据
                    tAuPositionRelationUp.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                    relationUpMapper.updateByPrimaryKeySelective(tAuPositionRelationUp);
                    log.info("逻辑删除上级汇报关系，id为："+tAuPositionRelationUp.getId());
                    //找到对应的下级汇报关系（上级下级确定唯一数据）,同步逻辑删除
                    TAuPositionRelationDownExample example2 = new TAuPositionRelationDownExample();
                    TAuPositionRelationDownExample.Criteria criteria2 =example2.createCriteria();
                    criteria2.andDownPositionIdEqualTo(positionId);
                    criteria2.andPositionIdEqualTo(tAuPositionRelationUp.getSuperiorPositionId());
                    relationDowns = relationDownMapper.selectByExample(example2);
                    for (TAuPositionRelationDown tAuPositionRelationDown: relationDowns) {
                        tAuPositionRelationDown.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                        relationDownMapper.updateByPrimaryKeySelective(tAuPositionRelationDown);
                        log.info("逻辑删除下级汇报关系，id为："+tAuPositionRelationDown.getId());
                    }
                }else {
                    //包含，就在原有数据修改一次
                    for (PositionUpDto positionUpDto:positionUpDtoList) {
                        if (tAuPositionRelationUp.getId() == positionUpDto.getId()){
                            BeanUtils.copyProperties(tAuPositionRelationUp,positionUpDto);
                            tAuPositionRelationUp.setPositionId(positionId);
                            tAuPositionRelationUp.setUpdateUser(loginUserId);
                            tAuPositionRelationUp.setUpdateTime(new Date());
                            Integer count = relationUpMapper.updateByPrimaryKey(tAuPositionRelationUp);
                            log.info("=============修改岗位时，同步上级汇报关系信息，此处在原数据基础上修改数据===============:"+count);
                            //找到对应的下级汇报关系（上级下级确定唯一数据）
                            criteria1.andPositionIdEqualTo(tAuPositionRelationUp.getSuperiorPositionId());
                            relationDowns = relationDownMapper.selectByExample(example1);
                            for (TAuPositionRelationDown tAuPositionRelationDown: relationDowns) {
                                tAuPositionRelationDown.setIsEffect(positionUpDto.getIsEffect());
                                tAuPositionRelationDown.setSummary(positionUpDto.getSummary());
                                tAuPositionRelationDown.setPositionId(positionUpDto.getSuperiorPositionId());
                                tAuPositionRelationDown.setUpdateUser(loginUserId);
                                tAuPositionRelationDown.setUpdateTime(new Date());
                                Integer count2 = relationDownMapper.updateByPrimaryKey(tAuPositionRelationDown);
                                log.info("同步下级汇报关系信息，此处在原数据基础上修改数据===============:"+count2);
                            }
                        }

                    }


                }
            }
        }else {
            //岗位本来没有上级汇报信息
            if(!CollectionUtils.isEmpty(positionUpDtoList)){
                //（1）传参集合有值，新增数据 | 传参集合为空，则不变化
                //参数必要性校验
                validParam(positionUpDtoList,positionId);
                for (PositionUpDto positionUpDto:positionUpDtoList){
                    TAuPositionRelationUp tAuPositionRelationUp = new TAuPositionRelationUp();
                    BeanUtils.copyProperties(tAuPositionRelationUp,positionUpDto);
                    tAuPositionRelationUp.setPositionId(positionId);
                    tAuPositionRelationUp.setCreateUser(loginUserId);
                    tAuPositionRelationUp.setUpdateUser(loginUserId);
                    tAuPositionRelationUp.setCreateTime(new Date());
                    tAuPositionRelationUp.setUpdateTime(new Date());
                    Integer count = relationUpMapper.insertSelective(tAuPositionRelationUp);
                    TAuPositionRelationDown tAuPositionRelationDown = new TAuPositionRelationDown();
                    BeanUtils.copyProperties(tAuPositionRelationDown,positionUpDto);
                    tAuPositionRelationDown.setPositionId(positionUpDto.getSuperiorPositionId());
                    tAuPositionRelationDown.setDownPositionId(positionId);;
                    tAuPositionRelationDown.setCreateUser(loginUserId);
                    tAuPositionRelationDown.setUpdateUser(loginUserId);
                    tAuPositionRelationDown.setCreateTime(new Date());
                    tAuPositionRelationDown.setUpdateTime(new Date());
                    Integer count2 = relationDownMapper.insertSelective(tAuPositionRelationDown);
                    log.info("==============修改岗位时，原本没有上级汇报关系数据，此时新增数据:"+count+"同步新增下级汇报关系数据："+count2);
                }
            }

        }
    }

    /**
     * 查看岗位是否被员工使用（有效使用，未删除，未禁用）
     * @param positionId
     * @return
     */
    private Boolean getPositionIsUse(Integer positionId,Integer type){
        TAuEmployeePositionExample example = new TAuEmployeePositionExample();
        //岗位删除条件：任岗删除
        if(type==1){
            TAuEmployeePositionExample.Criteria criteria = example.createCriteria();
            criteria.andDirectorPositionIdEqualTo(positionId);
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        }
        //岗位禁用条件：任岗删除或禁用
        if(type==2){
            TAuEmployeePositionExample.Criteria criteria2 = example.createCriteria();
            criteria2.andDirectorPositionIdEqualTo(positionId);
            criteria2.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            criteria2.andProhibitStateEqualTo(ConstantUtil.YES_OR_NO_N);
        }
        List<TAuEmployeePosition> tAuEmployeePositionList = tAuEmployeePositionMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(tAuEmployeePositionList)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 根据汇报类型和岗位id查看上级汇报关系，有且只有一条数据
     * @param positionId
     * @param reportType
     */
    private List<TAuPositionRelationUp> checkUpRelation(Integer positionId,String reportType){
        TAuPositionRelationUpExample example = new TAuPositionRelationUpExample();
        TAuPositionRelationUpExample.Criteria criteria = example.createCriteria();
        criteria.andPositionIdEqualTo(positionId);
        criteria.andReportTypeEqualTo(reportType);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuPositionRelationUp> tAuPositionRelationUpList = relationUpMapper.selectByExample(example);
        return tAuPositionRelationUpList;
    }

    /**
     * 岗位树结构展示及条件搜索
     * @return
     */
    public RestResponse queryPositionThreeAndSearch(String reportType, String positionName,Integer id) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuPositionExample example = new TAuPositionExample();
        TAuPositionExample.Criteria criteria = example.createCriteria();
        Set<TAuPosition> tAuPositions= new HashSet<>();
        if (StringUtils.isNotBlank(positionName)||StringUtils.isNotBlank(reportType)){
            TAuPositionRelationUpExample upExample = null;
            if (StringUtils.isNotBlank(reportType)){
                //根据汇报类型查询
                List<TAuPosition> tAuPositionslist = tAuPositionMapper.selectPositionUPByReportType("%" + reportType + "%");
                if (tAuPositionslist.size()!=0){
                    tAuPositions.addAll(tAuPositionslist);
                }
                List<TAuPosition> tAuPositions1 = tAuPositionMapper.selectPositionDOWNByReportType("%" + reportType + "%");
                if (tAuPositions1.size()!=0){
                    tAuPositions.addAll(tAuPositions1);
                }
            }
            if (StringUtils.isNotBlank(positionName)&&StringUtils.isNotBlank(reportType)){
                tAuPositions.forEach(tAuPosition -> {
                    if (tAuPosition.getPositionName().equalsIgnoreCase(positionName)){
                        tAuPositions.remove(tAuPosition);
                    }
                });
            }else if (StringUtils.isNotBlank(positionName)){
                //根据岗位名称查询
                criteria.andPositionNameLike("%"+positionName+"%");
                tAuPositions.addAll(tAuPositionMapper.selectByExample(example));
            }

            List<TAuPosition> tAuPositionList=selectDownPosition(tAuPositions);
            ArrayList<TAuPosition> positionslist2 = new ArrayList<>();
            //判断是否顶级岗位
            if (tAuPositionList.size()!=0){
                tAuPositionList.forEach(tAuPosition ->{
                    if(relationUpMapper.selectBySuperiorPositionId(tAuPosition.getId()).size()>0){
                        positionslist2.add(tAuPosition);
                    }
                } );
                //将不是顶级岗位的元素去除
                tAuPositionList.removeAll(positionslist2);
            }
            if (tAuPositionList.size()!=0||!tAuPositionList.isEmpty()){
                restResponseHeader.setCode(ConstantUtil.SUCCESS);
                restResponseHeader.setMessage("查询成功");

            }else {
                restResponseHeader.setCode(ConstantUtil.SUCCESS);
                restResponseHeader.setMessage("没有查询到数据");
            }

            return RestResponse.resultSuccess(tAuPositionList,restResponseHeader);
        }

        //根据岗位ID查询下级
        if (id!=0){
            tAuPositions.addAll(tAuPositionMapper.selectDownPositionByPositionId(id));
        }
        //查询最上级岗位
        if (id==0){
            tAuPositions.addAll(tAuPositionMapper.selectByExample(example));
            ArrayList<TAuPosition> positionslist2 = new ArrayList<>();
            //判断是否顶级岗位
            if (tAuPositions.size()!=0){
                tAuPositions.forEach(tAuPosition ->{
                    if(relationUpMapper.selectBySuperiorPositionId(tAuPosition.getId()).size()>0){
                        positionslist2.add(tAuPosition);
                    }
                } );
                //将不是顶级岗位的元素去除
                tAuPositions.removeAll(positionslist2);
            }
        }
        //查询部门名称及是否有下级
        List<TAuPosition> tAuPositionList=selectDownPosition(tAuPositions);
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        if (tAuPositions.size()==0){
            restResponseHeader.setMessage("没有查询到数据");
        }else {
            restResponseHeader.setMessage("查询成功");
        }
        return RestResponse.resultSuccess(tAuPositions,restResponseHeader);
    }

    /**
     * 修改岗位上级
     * @param positionId
     * @param superId
     * @return
     */
    public RestResponse updateSuperIdByPositionId(Integer positionId, Integer superId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Integer> integers = relationUpMapper.selectBySuperiorPositionId(positionId);
        if (integers.size()<1){throw new BusinessException(ConstantUtil.ERROR,"此岗位已是顶级岗位");}
        Integer res = relationUpMapper.updateSuperIdByPositionId(positionId,superId);
        System.out.println("res = " + res);
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("岗位上级修改成功");
        return RestResponse.resultSuccess(0,restResponseHeader);
    }


    private List<TAuPosition> selectDownPosition(Set<TAuPosition> tAuPositions){
        List<TAuPosition> tAuPositionList= new ArrayList<>();
        //查询部门名称及是否有下级
        if (tAuPositions.size()!=0){
            tAuPositions.forEach(tAuPosition -> {
                if (tAuPosition!=null){
                    TAuDepartemnt tAuDepartemnt = tAuDepartemntMapper.selectByPrimaryKey(tAuPosition.getBelongDeptId());
                    tAuPosition.setDeptName(tAuDepartemnt.getDeptName());
                    tAuPosition.setDeptFullName(tAuDepartemnt.getDeptFullName());
                    tAuPosition.setHasChildren( tAuPositionMapper.selectDownPositionByPositionId(tAuPosition.getId()).size()!= 0 );
                    tAuPosition.setOrgName(tAuOrganizationMapper.selectByPrimaryKey(tAuPosition.getUseOrgId()).getOrgName());
                    tAuPositionList.add(tAuPosition);
                }
            });
        }
        return tAuPositionList;
    }
}
