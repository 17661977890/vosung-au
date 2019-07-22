package com.vosung.authapp.org.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.org.*;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.org.LegalPersonOrgDto;
import com.vosung.auapi.client.dto.responsedto.org.OrgInfoResponseDto;
import com.vosung.auapi.client.dto.responsedto.org.OrgResponse;
import com.vosung.auapi.client.dto.responsedto.org.OrgResponseDto;
import com.vosung.auapi.client.entity.TAuOrganization;
import com.vosung.auapi.client.entity.TAuOrganizationExample;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.*;
import com.vosung.authapp.common.enums.SysDictEnum;
import com.vosung.authapp.common.exception.BusinessException;

import com.vosung.authapp.org.mapper.TAuOrganizationMapper;
import com.vosung.authapp.org.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


/**
 * 组织机构业务层实现类
 * @Author 彬
 * @Date 2019/4/19
 */
@Service
@Slf4j
public class OrgServiceImpl extends BaseServiceImpl implements OrgService {

    @Autowired
    private TAuOrganizationMapper tAuOrganizationMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserHolder userHolder;


    /**
     * 保存组织机构api----（入参有id修改，无id新增）
     * -----新增之前，先根据组织编码或名称查询，若有，则提示报错，不能重复添加
     * -----新增数据状态：创建
     * @param orgRequestDto
     * @return body:Integer
     */
    @Override
    public RestResponse saveNewOrg(OrgRequestDto orgRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuOrganization tAuOrganization = new TAuOrganization();
        try {
            BeanUtils.copyProperties(tAuOrganization,orgRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer record = 0;
        if(orgRequestDto.getId() == null){
            //判重
            checkAddIsExist(orgRequestDto.getOrgCode(),orgRequestDto.getOrgName());
            //id为空为新增
            //先设死，应该获取登录用户信息----单点登录时进行设置缓存类
            tAuOrganization.setDataState(DataStateCode.CREATED);
            tAuOrganization.setCreateUser(loginUserId);
            tAuOrganization.setUpdateUser(loginUserId);
            tAuOrganization.setCreateTime(new Date());
            tAuOrganization.setUpdateTime(new Date());
            //新增返回主键id
            record = tAuOrganizationMapper.insertSelective(tAuOrganization);
            if(ConstantUtil.LEGAL_PERSON.equals(orgRequestDto.getAccountancyOrgType())){
                //法人组织，所属法人就是本身
                TAuOrganization tAuOrganization1 = new TAuOrganization();
                tAuOrganization1.setId(tAuOrganization.getId());
                tAuOrganization1.setLegalPerson(tAuOrganization.getId());
                tAuOrganizationMapper.updateByPrimaryKeySelective(tAuOrganization1);
            }

        }else {
            //id有值，根据主键修改---已审核不可修改（前端控制）
            TAuOrganization tAuOrganization1 = tAuOrganizationMapper.selectByPrimaryKey(orgRequestDto.getId());
            if(tAuOrganization1 == null){
                throw new BusinessException("3011111116",getMessage("3011111116"));
            }
            boolean a = !tAuOrganization1.getOrgCode().equalsIgnoreCase(orgRequestDto.getOrgCode());
            boolean b = !tAuOrganization1.getOrgName().equalsIgnoreCase(orgRequestDto.getOrgName());
            if(a || b){
                //修改code 或 name 判重(通过就修改),没有修改不判
                checkIsExist(orgRequestDto.getOrgCode(),orgRequestDto.getOrgName(),a,b);
            }
            tAuOrganization.setUpdateUser(1);
            tAuOrganization.setUpdateTime(new Date());
            record = tAuOrganizationMapper.updateByPrimaryKeySelective(tAuOrganization);
        }
        if(record == 1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("组织机构保存成功 ");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 多条件查询
     * @param restRequest
     * @return body:OrgResponse
     */
    @Override
    public RestResponse getOrgList(RestRequest<GetOrgListRequestDto> restRequest)  {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        OrgResponse orgResponse = new OrgResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(OrgResponseDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();

            Integer pageSize = restRequest.getHeader().getPageSize();
            GetOrgListRequestDto getOrgListRequestDto = restRequest.getBody();

            TAuOrganizationExample example = new TAuOrganizationExample();
            TAuOrganizationExample.Criteria criteria = example.createCriteria();
            //显示未删除的
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getOrgCode())){
                criteria.andOrgCodeLike("%"+getOrgListRequestDto.getOrgCode()+"%");
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getOrgName())){
                criteria.andOrgNameLike("%"+getOrgListRequestDto.getOrgName()+"%");
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getOrgFormId())){
                criteria.andOrgFormIdEqualTo(getOrgListRequestDto.getOrgFormId());
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getContacts())){
                criteria.andContactsLike("%"+getOrgListRequestDto.getContacts()+"%");
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getTelephone())){
                criteria.andTelephoneLike("%"+getOrgListRequestDto.getTelephone()+"%");
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getZipCode())){
                criteria.andZipCodeLike("%"+getOrgListRequestDto.getZipCode()+"%");
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getAddress())){
                criteria.andAddressLike("%"+getOrgListRequestDto.getAddress()+"%");
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getIsAccountancyOrg())){
                criteria.andIsAccountancyOrgEqualTo(getOrgListRequestDto.getIsAccountancyOrg());
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getAccountancyOrgType())){
                criteria.andAccountancyOrgTypeEqualTo(getOrgListRequestDto.getAccountancyOrgType());
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getIsOperationOrg())){
                criteria.andIsOperationOrgEqualTo(getOrgListRequestDto.getIsOperationOrg());
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getOrgFunctionType())){
                criteria.andOrgFunctionTypeEqualTo(getOrgListRequestDto.getOrgFunctionType());
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getSummary())){
                criteria.andSummaryLike("%"+getOrgListRequestDto.getSummary()+"%");
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getDataState())){
                criteria.andDataStateEqualTo(getOrgListRequestDto.getDataState());
            }
            if(StringUtils.isNotEmpty(getOrgListRequestDto.getProhibitState())){
                criteria.andProhibitStateEqualTo(getOrgListRequestDto.getProhibitState());
            }
            if(getOrgListRequestDto.getLegalPerson() != null){
                criteria.andLegalPersonEqualTo(getOrgListRequestDto.getLegalPerson());
            }
            if(getOrgListRequestDto.getCreateTime()!=null){
                criteria.andCreateTimeEqualTo(getOrgListRequestDto.getCreateTime());
            }
            if(getOrgListRequestDto.getUpdateTime()!=null){
                criteria.andUpdateTimeEqualTo(getOrgListRequestDto.getUpdateTime());
            }
            if(getOrgListRequestDto.getProhibitTime()!=null){
                criteria.andProhibitTimeEqualTo(getOrgListRequestDto.getProhibitTime());
            }
            PageMethod.startPage(pageNum,pageSize,"id");
            List<TAuOrganization> tAuOrganizationList =  tAuOrganizationMapper.selectByExample(example);
            List<OrgResponseDto> orgResponseDtos = new ArrayList<>();

            for (TAuOrganization tAuOrganization: tAuOrganizationList) {
                OrgResponseDto orgResponseDto =new OrgResponseDto();
                BeanUtils.copyProperties(orgResponseDto,tAuOrganization);
                orgResponseDto.setDataStateName((String) redisUtil.hget(SysDictEnum.DATA_STATE.getCode(),orgResponseDto.getDataState()));
                orgResponseDto.setAccountancyOrgTypeName((String) redisUtil.hget(SysDictEnum.ACCOUNT_ORG.getCode(),orgResponseDto.getAccountancyOrgType()));
                orgResponseDto.setOrgFormName((String) redisUtil.hget(SysDictEnum.COMPANY_FORM.getCode(),orgResponseDto.getOrgFormId()));
                if(orgResponseDto.getLegalPerson()!=null){
                    TAuOrganization tAuOrganization1 = tAuOrganizationMapper.selectByPrimaryKey(orgResponseDto.getLegalPerson());
                    orgResponseDto.setLegalPersonName(tAuOrganization1.getOrgName());
                }
                orgResponseDtos.add(orgResponseDto);
            }
            Integer sumCount = tAuOrganizationMapper.countByExample(example);

            orgResponse.setPageInfo(new PageInfo<>(orgResponseDtos));
            orgResponse.setSum(sumCount);
            orgResponse.setColumnList(viewColumnList);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询组织机构列表成功 ");
        return RestResponse.resultSuccess(orgResponse,restResponseHeader);
    }

    /**
     * 根据id查看组织机构详情
     * @param orgInfoRequestDto
     * @return body:TAuOrganization
     */
    @Override
    public RestResponse getOrgInfoById(OrgInfoRequestDto orgInfoRequestDto) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(orgInfoRequestDto.getId());
        OrgInfoResponseDto orgInfoResponseDto = new OrgInfoResponseDto();
        try {
            BeanUtils.copyProperties(orgInfoResponseDto,tAuOrganization);
            if(tAuOrganization.getLegalPerson()!=null){
                TAuOrganization tAuOrganization1 = tAuOrganizationMapper.selectByPrimaryKey(tAuOrganization.getLegalPerson());
                orgInfoResponseDto.setLegalPersonName(tAuOrganization1.getOrgName());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if(tAuOrganization != null){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("查看组织机构详情成功 ");
        }else {
            throw new BusinessException("3011111116",getMessage("3011111116"));
        }
        return RestResponse.resultSuccess(orgInfoResponseDto,restResponseHeader);
    }


    /**
     * 获取可选组织（新增组织关系方案初显数据，不分页）---已审核，未删除，未禁止
     * @return
     */
    @Override
    public RestResponse getSelectableOrgList() {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuOrganizationExample example = new TAuOrganizationExample();
        TAuOrganizationExample.Criteria criteria = example.createCriteria();
        criteria.andDataStateEqualTo(DataStateCode.AUDITED);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria.andProhibitStateEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuOrganization> tAuOrganizations = tAuOrganizationMapper.selectByExample(example);
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询未有组织关系的可选组织成功");
        return RestResponse.resultSuccess(tAuOrganizations,restResponseHeader);
    }

    /**
     * 法人组织（所属法人）
     * @return
     */
    @Override
    public RestResponse getLegalPersonOrgList() {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuOrganizationExample example = new TAuOrganizationExample();
        List<TAuOrganization> tAuOrganizations =  tAuOrganizationMapper.selectByExample(example);
        List<LegalPersonOrgDto> personOrgDtoList = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        for (TAuOrganization tAuOrganization : tAuOrganizations){
            if(!ids.contains(tAuOrganization.getLegalPerson())){
                ids.add(tAuOrganization.getLegalPerson());
                LegalPersonOrgDto legalPersonOrgDto = new LegalPersonOrgDto();
                legalPersonOrgDto.setId(tAuOrganization.getLegalPerson());
                TAuOrganization tAuOrganization1=tAuOrganizationMapper.selectByPrimaryKey(tAuOrganization.getLegalPerson());
                if(tAuOrganization1 == null){
                    throw new BusinessException("3011111117",getMessage("3011111117"));
                }
                legalPersonOrgDto.setOrgName(tAuOrganization1.getOrgName());
                personOrgDtoList.add(legalPersonOrgDto);
            }
        }
        restResponseHeader.setMessage("展示法人组织成功");
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        return RestResponse.resultSuccess(personOrgDtoList,restResponseHeader);
    }

    /**
     * 提交组织
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse commitOrg(UpdateOrgDataStateDto restRequest) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        String[] orgIds = restRequest.getOrgIds().split(",");
        for (int i=0;i<orgIds.length;i++) {
            TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(Integer.valueOf(orgIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuOrganization.getDataState();
            Boolean a = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) ||
                    DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(tAuOrganization.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && a){
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuOrganization.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !a){
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map = new HashMap();
                    map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(a){
                //符合要求，修改状态为审核中
                tAuOrganization.setDataState(DataStateCode.AUDIT_IN_PROGRESS);
                tAuOrganization.setUpdateUser(loginUserId);
                tAuOrganization.setUpdateTime(new Date());
                tAuOrganizationMapper.updateByPrimaryKeySelective(tAuOrganization);
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；提交成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 撤销组织
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse abolishOrg(UpdateOrgDataStateDto restRequest) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        String[] orgIds = restRequest.getOrgIds().split(",");
        for (int i=0;i<orgIds.length;i++) {
            TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(Integer.valueOf(orgIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuOrganization.getDataState();
            Boolean b = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuOrganization.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && b){
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuOrganization.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !b){
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(b){
                //符合要求，修改状态为重新审核
                tAuOrganization.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuOrganization.setUpdateUser(loginUserId);
                tAuOrganization.setUpdateTime(new Date());
                tAuOrganizationMapper.updateByPrimaryKeySelective(tAuOrganization);
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；撤销成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 审核组织
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse auditOrg(UpdateOrgDataStateDto restRequest) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        String[] orgIds = restRequest.getOrgIds().split(",");
        for (int i=0;i<orgIds.length;i++) {
            TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(Integer.valueOf(orgIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuOrganization.getDataState();
            Boolean c = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuOrganization.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && c){
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuOrganization.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !c){
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","组织机构："+tAuOrganization.getOrgName()+"；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(c){
                //符合要求，修改状态为已审核
                tAuOrganization.setDataState(DataStateCode.AUDITED);
                tAuOrganization.setReviewUser(loginUserId);
                tAuOrganization.setReviewTime(new Date());
                tAuOrganization.setUpdateUser(loginUserId);
                tAuOrganization.setUpdateTime(new Date());
                tAuOrganizationMapper.updateByPrimaryKeySelective(tAuOrganization);
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 反审核组织
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse reverseAuditOrg(UpdateOrgDataStateDto restRequest) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        String[] orgIds = restRequest.getOrgIds().split(",");
        for (int i=0;i<orgIds.length;i++) {
            TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(Integer.valueOf(orgIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuOrganization.getDataState();
            Boolean d = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState) || DataStateCode.AUDITED.equals(dataState);
            if(tAuOrganization.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && d){
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuOrganization.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !d){
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(d){
                //符合要求，修改状态为重新审核
                tAuOrganization.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuOrganization.setUpdateUser(loginUserId);
                tAuOrganization.setUpdateTime(new Date());
                tAuOrganizationMapper.updateByPrimaryKeySelective(tAuOrganization);
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；反审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","组织机构："+tAuOrganization.getOrgName()+"；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 删除组织
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse deleteOrg(UpdateOrgDataStateDto restRequest) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        String[] orgIds = restRequest.getOrgIds().split(",");
        for (int i=0;i<orgIds.length;i++) {
            TAuOrganization tAuOrganization = tAuOrganizationMapper.selectByPrimaryKey(Integer.valueOf(orgIds[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuOrganization.getDataState();
            //只有创建和暂存才可删除：
            Boolean e = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState);
            if(e){
                tAuOrganization.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                tAuOrganization.setUpdateUser(loginUserId);
                tAuOrganization.setUpdateTime(new Date());
                tAuOrganizationMapper.updateByPrimaryKeySelective(tAuOrganization);
                map.put("message","组织机构 ："+tAuOrganization.getOrgName()+"；删除成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.AUDITED.equals(dataState) || DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","组织机构："+tAuOrganization.getOrgName()+"；删除失败，只有创建和暂存状态的组织机构才可删除");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 禁用组织
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse forbiddenOrg(UpdateOrgDataStateDto restRequest) {
        return null;
    }
    /**
     * 反禁用组织
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse unForbiddenOrg(UpdateOrgDataStateDto restRequest) {
        return null;
    }

    /**
     * 对组织机构修改判重
     * @param orgCode
     * @param orgName
     * @return
     */
    private void checkIsExist(String orgCode,String orgName,boolean a,boolean b){
        if(a){
            //code 修改，就判重
            TAuOrganizationExample tAuOrganizationExample = new TAuOrganizationExample();
            TAuOrganizationExample.Criteria criteria = tAuOrganizationExample.createCriteria();
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            criteria.andOrgCodeEqualTo(orgCode);
            List<TAuOrganization> tAuOrganizations= tAuOrganizationMapper.selectByExample(tAuOrganizationExample);
            if(!CollectionUtils.isEmpty(tAuOrganizations)){
                throw new BusinessException("3011111113",getMessage("3011111113"));
            }
        }
        if(b){
            //name 修改，就判重
            TAuOrganizationExample tAuOrganizationExample = new TAuOrganizationExample();
            TAuOrganizationExample.Criteria criteria = tAuOrganizationExample.createCriteria();
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            criteria.andOrgNameEqualTo(orgName);
            List<TAuOrganization> tAuOrganizations= tAuOrganizationMapper.selectByExample(tAuOrganizationExample);
            if(!CollectionUtils.isEmpty(tAuOrganizations)){
                throw new BusinessException("3011111114",getMessage("3011111114"));
            }
        }
    }

    /**
     * 对组织机构新增判重
     * @param orgCode
     * @param orgName
     */
    private void checkAddIsExist(String orgCode,String orgName){
        TAuOrganizationExample tAuOrganizationExample = new TAuOrganizationExample();
        TAuOrganizationExample.Criteria criteria =tAuOrganizationExample.createCriteria();
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria.andOrgCodeEqualTo(orgCode);
        TAuOrganizationExample.Criteria criteria2 =tAuOrganizationExample.createCriteria();
        criteria2.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria2.andOrgNameEqualTo(orgName);
        tAuOrganizationExample.or(criteria2);
        List<TAuOrganization> tAuOrganizations= tAuOrganizationMapper.selectByExample(tAuOrganizationExample);
        if(!CollectionUtils.isEmpty(tAuOrganizations)){
            if(orgCode.equalsIgnoreCase(tAuOrganizations.get(0).getOrgCode())){
                throw new BusinessException("3011111113",getMessage("3011111113"));
            }
            if(orgName.equalsIgnoreCase(tAuOrganizations.get(0).getOrgName())){
                throw new BusinessException("3011111114",getMessage("3011111114"));
            }
        }
    }

}
