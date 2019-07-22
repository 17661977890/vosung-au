package com.vosung.authapp.role.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.rolesubsystem.PermissionGroupDto;
import com.vosung.auapi.client.dto.requestdto.rolesubsystem.RoleSubsystemRequest;
import com.vosung.auapi.client.dto.requestdto.rolesubsystem.RoleSubsystemRequestDto;
import com.vosung.auapi.client.dto.requestdto.rolesubsystem.SubsystemDto;
import com.vosung.auapi.client.dto.responsedto.common.BoVo;
import com.vosung.auapi.client.dto.responsedto.common.SpVo;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.rolesubsystem.RoleSubsystemListDto;
import com.vosung.auapi.client.dto.responsedto.rolesubsystem.RoleSubsystemResponseDto;
import com.vosung.auapi.client.dto.responsedto.rolesubsystem.RoleSubsystemRseponse;
import com.vosung.auapi.client.dto.responsedto.rolesubsystem.SubsysPermissionGroupDto;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.ConstantUtil;
import com.vosung.authapp.common.constant.FieldMessageCls;
import com.vosung.authapp.common.constant.RedisUtil;
import com.vosung.authapp.common.constant.UserHolder;
import com.vosung.authapp.common.enums.SysDictEnum;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.permission.mapper.TAuPermissionItemMapper;
import com.vosung.authapp.permission.mapper.TAuPermissionObjectItemMapper;
import com.vosung.authapp.role.mapper.TAuRoleMapper;
import com.vosung.authapp.role.mapper.TAuRolePermissionObjectMapper;
import com.vosung.authapp.role.mapper.TAuSubsystemPermissionMapper;
import com.vosung.authapp.role.service.RoleSubsystemService;
import com.vosung.authapp.sysdata.mapper.TSysDictItemMapper;
import com.vosung.boapi.client.BoClient;
import com.vosung.boapi.entity.Result;
import com.vosung.boapi.pojo.DoTempPubModel;
import com.vosung.boapi.pojo.OpreationBean;
import com.vosung.boapi.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * 子系统授权业务类
 * @Author 彬
 * @Date 2019/6/20
 */
@Slf4j
@Service
public class RoleSubsystemServiceImpl extends BaseServiceImpl implements RoleSubsystemService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserHolder userHolder;
    @Autowired
    private TAuSubsystemPermissionMapper subsystemPermissionMapper;
    @Autowired
    private TAuRolePermissionObjectMapper rolePermissionObjectMapper;
    @Autowired
    private TAuPermissionObjectItemMapper permissionObjectItemMapper;
    @Autowired
    private TSysDictItemMapper tSysDictItemMapper;
    @Autowired
    private TAuRoleMapper roleMapper;
    @Autowired
    private TAuPermissionItemMapper permissionItemMapper;

    @Autowired
    private BoClient boClient;


    /**
     * 子系统授权（批量影响业务对象授权数据）-------目前五个权限组，新增都新增，不管有权无权
     * ---某项权限组有权：业务对象那边所有子系统对应的同类型权限项状态设为有权
     * ---某项权限组无权：业务对象那边所有子系统对应的同类型权限项状态设为无权
     * @param roleSubsystemRequestDto
     * @return
     */
    @Transactional
    @Override
    public RestResponse saveRoleSubsystem(RoleSubsystemRequestDto roleSubsystemRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        Integer roleId = roleSubsystemRequestDto.getRoleId();
        List<SubsystemDto> subsystemDtos = roleSubsystemRequestDto.getSubsystemDtos();
        List<Integer> subsystemIds = new ArrayList<>();
        Integer record = 0;
        //===========================================子系统授权数据保存===================================================
        for (SubsystemDto subsystemDto:subsystemDtos) {
            if(!subsystemIds.contains(subsystemDto.getSubsystemId())){
                subsystemIds.add(subsystemDto.getSubsystemId());
            }
            TAuSubsystemPermissionExample example = new TAuSubsystemPermissionExample();
            TAuSubsystemPermissionExample.Criteria criteria = example.createCriteria();
            criteria.andSubsystemIdEqualTo(subsystemDto.getSubsystemId());
            List<TAuSubsystemPermission> subsystemPermissionList = subsystemPermissionMapper.selectByExample(example);
            if(CollectionUtils.isEmpty(subsystemPermissionList)){
                //如果之前没有，新增(每个子系统有五个权限项组 目前固定的，除非系统需求变化)
                for (PermissionGroupDto permissionGroupDto:subsystemDto.getPermissionGroupDtoList()) {
                    TAuSubsystemPermission tAuSubsystemPermission = new TAuSubsystemPermission();
                    tAuSubsystemPermission.setBusinessDomainId(subsystemDto.getBusinessDomainId());
                    tAuSubsystemPermission.setRoleId(roleId);
                    tAuSubsystemPermission.setSubsystemId(subsystemDto.getSubsystemId());
                    tAuSubsystemPermission.setPermissionStatus(permissionGroupDto.getPermissionStatus());
                    tAuSubsystemPermission.setPermissionGroupCode(permissionGroupDto.getPermissionGroupCode());
                    tAuSubsystemPermission.setCreateUser(loginUserId);
                    tAuSubsystemPermission.setCreateTime(new Date());
                    tAuSubsystemPermission.setUpdateUser(loginUserId);
                    tAuSubsystemPermission.setUpdateTime(new Date());
                    subsystemPermissionMapper.insertSelective(tAuSubsystemPermission);
                    record++;
                }
                //todo 修改子系统是否设置授权字段--已设置（因为和角色有关，暂时取消，改为非持久化字段显示）
            }else {
                Integer count = 0;
                //之前有，则只是修改状态
                for (PermissionGroupDto permissionGroupDto:subsystemDto.getPermissionGroupDtoList()) {
                    if(ConstantUtil.HAS_POWER.equals(permissionGroupDto.getPermissionStatus())){
                        count++;
                    }
                    TAuSubsystemPermissionExample example1 = new TAuSubsystemPermissionExample();
                    TAuSubsystemPermissionExample.Criteria criteria1 = example1.createCriteria();
                    criteria1.andSubsystemIdEqualTo(subsystemDto.getSubsystemId());
                    criteria1.andPermissionGroupCodeEqualTo(permissionGroupDto.getPermissionGroupCode());
                    List<TAuSubsystemPermission> subsystemPermissionList1 = subsystemPermissionMapper.selectByExample(example1);
                    //子系统授权，5个权限组都会有数据，如果查不到说明 权限项组code有变化，看数据库
                    if(!CollectionUtils.isEmpty(subsystemPermissionList1)){
                        TAuSubsystemPermission tAuSubsystemPermission = subsystemPermissionList1.get(0);
                        if(!Objects.equals(tAuSubsystemPermission.getPermissionStatus(), permissionGroupDto.getPermissionStatus())){
                            tAuSubsystemPermission.setPermissionStatus(permissionGroupDto.getPermissionStatus());
                            tAuSubsystemPermission.setUpdateUser(loginUserId);
                            tAuSubsystemPermission.setUpdateTime(new Date());
                            subsystemPermissionMapper.updateByPrimaryKey(tAuSubsystemPermission);
                            record++;
                        }
                    }
                }
                //todo 修改子系统是否设置授权字段--未设置（因为和角色有关，暂时取消，改为非持久化字段显示）
//                if(count>0){
//                    System.out.println("======所有的权限项组都是无权，则子系统设置字段修改为未设置========");
//                }
            }
        }
        //todo 调bo接口 查 子系统id 对应的业务对象id 以及绑定的权限对象id 对应的所有操作项的url（入参：子系统id的list集合  list出参：业务对象id、业务对象绑定的权限对象id、业务对象绑定的操作项的url对应权限项id）
        //============================组装刚授权的子系统对应的权限对象以及所有权限项数据和权限组code===========================
        List<SpVo> spVoList = new ArrayList<>();
        Result<List<DoTempPubModel>> result = boClient.queryChildrenRelam(subsystemIds);
        List<DoTempPubModel> doTempPubModelList = result.getData();
        if(CollectionUtils.isEmpty(doTempPubModelList)){
            throw new BusinessException("4011111112",getMessage("4011111112"));
        }
        for (DoTempPubModel doTempPubModel:doTempPubModelList) {
            List<OpreationBean> opreationBeans = doTempPubModel.getBoOpreation();
            for (OpreationBean operationBean:opreationBeans) {
                SpVo spVo = new SpVo();
                spVo.setBusinessObjectId(doTempPubModel.getBoId());
                spVo.setSubsystemId(doTempPubModel.getcDomainId());
                spVo.setPermissionObjectId(doTempPubModel.getAuId());
                spVo.setUrl(operationBean.getApiUrl());
                spVo.setPermissionItemId(operationBean.getOpAuId());
                TAuPermissionItem tAuPermissionItem = permissionItemMapper.selectByPrimaryKey(operationBean.getOpAuId());
                spVo.setPermissionGroupCode(tAuPermissionItem.getPermissionGroupCode());
                spVoList.add(spVo);
            }
        }
        //==========================================批量保存业务对象授权数据===============================================
        Integer count = 0;
        Integer updateCount = 0;
        //数据新增修改完以后--获取刚保存的数据
        List<TAuSubsystemPermission> subsystemPermissionList = subsystemPermissionMapper.getSubsystemPermission(subsystemIds,roleId);
        for (TAuSubsystemPermission tAuSubsystemPermission: subsystemPermissionList) {
            for (SpVo spVo:spVoList) {
                //同一个子系统，同样的权限组code（同一类型的权限）
                if(tAuSubsystemPermission.getSubsystemId() == spVo.getSubsystemId() && tAuSubsystemPermission.getPermissionGroupCode().equals(spVo.getPermissionGroupCode())){
                    //查该业务对象和权限项的数据是否存在，(角色 业务对象和权限项确定唯一数据)---存在，修改 ----不存在，新增
                    TAuRolePermissionObjectExample example = new TAuRolePermissionObjectExample();
                    TAuRolePermissionObjectExample.Criteria criteria = example.createCriteria();
                    criteria.andRoleIdEqualTo(roleId);
                    criteria.andBusinessObjectIdEqualTo(spVo.getBusinessObjectId());
                    criteria.andPermissionItemIdEqualTo(spVo.getPermissionItemId());
                    List<TAuRolePermissionObject> rolePermissionObjectList = rolePermissionObjectMapper.selectByExample(example);
                    if(CollectionUtils.isEmpty(rolePermissionObjectList)){
                        //之前数据不存在，新增
                        TAuRolePermissionObject tAuRolePermissionObject = new TAuRolePermissionObject();
                        tAuRolePermissionObject.setBusinessDomainId(tAuSubsystemPermission.getBusinessDomainId());
                        tAuRolePermissionObject.setSubsystemId(tAuSubsystemPermission.getSubsystemId());
                        tAuRolePermissionObject.setPermissionObjectId(spVo.getPermissionObjectId());
                        tAuRolePermissionObject.setRoleId(tAuSubsystemPermission.getRoleId());
                        tAuRolePermissionObject.setPermissionItemId(spVo.getPermissionItemId());
                        tAuRolePermissionObject.setBusinessObjectId(spVo.getBusinessObjectId());
                        tAuRolePermissionObject.setPermissionStatus(tAuSubsystemPermission.getPermissionStatus());
                        tAuRolePermissionObject.setUrl(spVo.getUrl());
                        tAuRolePermissionObject.setCreateUser(loginUserId);
                        tAuRolePermissionObject.setCreateTime(new Date());
                        tAuRolePermissionObject.setUpdateUser(loginUserId);
                        tAuRolePermissionObject.setUpdateTime(new Date());
                        rolePermissionObjectMapper.insertSelective(tAuRolePermissionObject);
                        count++;
                    }else{
                        //之前有数据
                        TAuRolePermissionObject tAuRolePermissionObject = rolePermissionObjectList.get(0);
                        if(!tAuRolePermissionObject.getPermissionStatus().equals(tAuSubsystemPermission.getPermissionStatus())){
                            //如果之前有的数据权限状态和子系统授权的权限状态不一样，修改，一样不操作
                            tAuRolePermissionObject.setPermissionStatus(tAuSubsystemPermission.getPermissionStatus());
                            tAuRolePermissionObject.setUpdateTime(new Date());
                            tAuRolePermissionObject.setUpdateUser(loginUserId);
                            rolePermissionObjectMapper.updateByPrimaryKeySelective(tAuRolePermissionObject);
                            updateCount++;
                        }
                    }
                }
            }
        }
        log.info("==================同步新增业务对象授权数据:"+count+"修改业务对象授权数据："+updateCount);
        restResponseHeader.setMessage("子系统授权成功");
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 根据角色查询已授权的子系统
     * @param roleId
     * @return
     */
    @Override
    public RestResponse getSubsystemPermissionListByRoleId(Integer roleId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<RoleSubsystemResponseDto> roleSubsystemResponseDtoList = new ArrayList<>();
        //查角色下所有子系统授权数据
        TAuSubsystemPermissionExample example = new TAuSubsystemPermissionExample();
        TAuSubsystemPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<TAuSubsystemPermission> tAuSubsystemPermissionList = subsystemPermissionMapper.selectByExample(example);
        //去重子系统id（子系统id和权限组code确定唯一数据）
        List<Integer> subsystemIds = new ArrayList<>();
        for(TAuSubsystemPermission tAuSubsystemPermission:tAuSubsystemPermissionList){
            if(!subsystemIds.contains(tAuSubsystemPermission.getSubsystemId())){
                subsystemIds.add(tAuSubsystemPermission.getSubsystemId());
            }
        }
        //========开始组装数据=========
        for (Integer subsystemId:subsystemIds) {
            RoleSubsystemResponseDto roleSubsystemResponseDto = new RoleSubsystemResponseDto();
            //调bo接口 根据子系统id  查所属业务领域、子系统的（id name）、子系统是否设置字段
            //todo 待优化，循环调服务接口，效率低，将循环结构和调用的接口改造，一次查询，循环赋值
            DoTempPubModel doTempPubModel = null;
            try {
                Result<DoTempPubModel> result =  boClient.queryChildrenById(subsystemId);
                doTempPubModel = result.getData();
            }catch (Exception e){
                log.info("角色id查询有权子系统list接口：=====>调bo接口：{根据子系统id查相关详情信息}====>失败");
                e.printStackTrace();
            }
            if(doTempPubModel!=null){
                roleSubsystemResponseDto.setBusinessDomainId(doTempPubModel.getDomainId());
                roleSubsystemResponseDto.setBusinessDomainName(doTempPubModel.getDomainName());
                roleSubsystemResponseDto.setSubsystemId(subsystemId);
                roleSubsystemResponseDto.setSubsystemName(doTempPubModel.getcDomainName());
                //直接设为已设置（已授权数据）
                roleSubsystemResponseDto.setIsSetting(ConstantUtil.YES_OR_NO_Y);
            }else {
                throw new BusinessException("4011111112",getMessage("4011111112"));
            }
            roleSubsystemResponseDtoList.add(roleSubsystemResponseDto);
        }
        //组装权限项组list
        for (RoleSubsystemResponseDto roleSubsystemResponseDto:roleSubsystemResponseDtoList) {
            TAuSubsystemPermissionExample example1 = new TAuSubsystemPermissionExample();
            TAuSubsystemPermissionExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andRoleIdEqualTo(roleId);
            criteria1.andSubsystemIdEqualTo(roleSubsystemResponseDto.getSubsystemId());
            List<TAuSubsystemPermission> tAuSubsystemPermissionList1 = subsystemPermissionMapper.selectByExample(example1);
            //组装权限项组list
            List<SubsysPermissionGroupDto> subsysPermissionGroupDtoList = new ArrayList<>();
            for (TAuSubsystemPermission tAuSubsystemPermission:tAuSubsystemPermissionList1){
                SubsysPermissionGroupDto subsysPermissionGroupDto = new SubsysPermissionGroupDto();
                subsysPermissionGroupDto.setPermissionGroupCode(tAuSubsystemPermission.getPermissionGroupCode());
                subsysPermissionGroupDto.setPermissionStatus(tAuSubsystemPermission.getPermissionStatus());
                subsysPermissionGroupDto.setPermissionGroupName((String) redisUtil.hget(SysDictEnum.PERMISSION_GROUP.getCode(),tAuSubsystemPermission.getPermissionGroupCode()));
                subsysPermissionGroupDtoList.add(subsysPermissionGroupDto);
            }
            roleSubsystemResponseDto.setPermissionGroupDtoList(subsysPermissionGroupDtoList);
        }
        restResponseHeader.setMessage("根据角色查有权业务对象列表成功");
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        return RestResponse.resultSuccess(roleSubsystemResponseDtoList,restResponseHeader);
    }

    /**
     * 获取所有子系统（含权限项组）-----也是绑定过
     * @param roleId
     * @return
     */
    @Override
    public RestResponse getAllSubsystemList(Integer roleId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<RoleSubsystemResponseDto> roleSubsystemResponseDtoList = new ArrayList<>();
        //todo 调bo接口 查所有已绑定权限对象的 子系统 （所属业务领域和子系统的id name） ------查询结果 赋值 roleSubsystemResponseDtoList
        List<DoTempPubModel> doTempPubModelList = new ArrayList<>();
        try {
            Result<List<DoTempPubModel>> result = boClient.queryBindAllChildrenDomain();
            doTempPubModelList = result.getData();
        }catch (Exception e){
            log.info("获取所有已绑定权限对象的子系统列表接口：=====>调bo接口：====>失败");
            e.printStackTrace();
        }
        for (DoTempPubModel doTempPubModel:doTempPubModelList) {
            RoleSubsystemResponseDto roleSubsystemResponseDto = new RoleSubsystemResponseDto();
            roleSubsystemResponseDto.setBusinessDomainId(doTempPubModel.getDomainId());
            roleSubsystemResponseDto.setBusinessDomainName(doTempPubModel.getDomainName());
            roleSubsystemResponseDto.setSubsystemName(doTempPubModel.getcDomainName());
            roleSubsystemResponseDto.setSubsystemId(doTempPubModel.getcDomainId());
            roleSubsystemResponseDtoList.add(roleSubsystemResponseDto);
        }
        //查角色下所有子系统授权数据
        TAuSubsystemPermissionExample example = new TAuSubsystemPermissionExample();
        TAuSubsystemPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<TAuSubsystemPermission> tAuSubsystemPermissionList = subsystemPermissionMapper.selectByExample(example);
        //去重子系统id（子系统id和权限组code确定唯一数据）
        List<Integer> subsystemIds = new ArrayList<>();
        for(TAuSubsystemPermission tAuSubsystemPermission:tAuSubsystemPermissionList){
            if(!subsystemIds.contains(tAuSubsystemPermission.getSubsystemId())){
                subsystemIds.add(tAuSubsystemPermission.getSubsystemId());
            }
        }

        for (RoleSubsystemResponseDto roleSubsystemResponseDto: roleSubsystemResponseDtoList) {
            if(subsystemIds.contains(roleSubsystemResponseDto.getSubsystemId())){
                //已设置
                roleSubsystemResponseDto.setIsSetting(ConstantUtil.YES_OR_NO_Y);
                //已授权的子系统，查授权表权限组的权限状态
                List<SubsysPermissionGroupDto> subsysPermissionGroupDtoList = new ArrayList<>();
                TAuSubsystemPermissionExample example1 = new TAuSubsystemPermissionExample();
                TAuSubsystemPermissionExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andRoleIdEqualTo(roleId);
                List<TAuSubsystemPermission> tAuSubsystemPermissionList1 = subsystemPermissionMapper.selectByExample(example1);
                for (TAuSubsystemPermission tAuSubsystemPermission:tAuSubsystemPermissionList1) {
                    SubsysPermissionGroupDto subsysPermissionGroupDto = new SubsysPermissionGroupDto();
                    subsysPermissionGroupDto.setPermissionStatus(tAuSubsystemPermission.getPermissionStatus());
                    subsysPermissionGroupDto.setPermissionGroupCode(tAuSubsystemPermission.getPermissionGroupCode());
                    subsysPermissionGroupDto.setPermissionGroupName((String) redisUtil.hget(SysDictEnum.PERMISSION_GROUP.getCode(),tAuSubsystemPermission.getPermissionGroupCode()));
                    subsysPermissionGroupDtoList.add(subsysPermissionGroupDto);
                }
                roleSubsystemResponseDto.setPermissionGroupDtoList(subsysPermissionGroupDtoList);
            }else {
                //未设置
                roleSubsystemResponseDto.setIsSetting(ConstantUtil.YES_OR_NO_N);
                //未授权的，就直接设置每个子系统对应5个权限组数据，权限状态全为无权
                List<SubsysPermissionGroupDto> subsysPermissionGroupDtoList = getList();
                roleSubsystemResponseDto.setPermissionGroupDtoList(subsysPermissionGroupDtoList);
            }
        }
        restResponseHeader.setMessage("查所有绑定权限对象的业务对象列表成功");
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        return RestResponse.resultSuccess(roleSubsystemResponseDtoList,restResponseHeader);
    }

    /**
     * 查询权限（角色的--子系统）
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getSubsystemPermissionReportByRoleId(RestRequest<RoleSubsystemRequest> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        RoleSubsystemRseponse roleSubsystemRseponse = new RoleSubsystemRseponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(RoleSubsystemListDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();

            TAuSubsystemPermissionExample example = new TAuSubsystemPermissionExample();
            TAuSubsystemPermissionExample.Criteria criteria = example.createCriteria();
            criteria.andRoleIdEqualTo(restRequest.getBody().getRoleId());
            PageMethod.startPage(pageNum,pageSize,"id");
            List<TAuSubsystemPermission> subsystemPermissionList =  subsystemPermissionMapper.selectByExample(example);
            List<RoleSubsystemListDto> subsystemListDtoList = new ArrayList<>();
            TAuRole tAuRole = roleMapper.selectByPrimaryKey(restRequest.getBody().getRoleId());
            for (TAuSubsystemPermission tAuSubsystemPermission: subsystemPermissionList) {
                RoleSubsystemListDto roleSubsystemListDto=new RoleSubsystemListDto();
                BeanUtils.copyProperties(roleSubsystemListDto,tAuSubsystemPermission);
                //todo: 调bo接口查 业务领域 子系统详情（根据子系统id 关联查得到id name）--赋值
                DoTempPubModel doTempPubModel = null;
                try {
                    Result<DoTempPubModel> result =  boClient.queryChildrenById(tAuSubsystemPermission.getSubsystemId());
                    doTempPubModel = result.getData();
                }catch (Exception e){
                    log.info("角色id查询有权子系统list接口：=====>调bo接口：{根据子系统id查相关详情信息}====>失败");
                    e.printStackTrace();
                }
                if(doTempPubModel!=null){
                    roleSubsystemListDto.setBusinessDomainName(doTempPubModel.getDomainName());
                    roleSubsystemListDto.setSubsystemName(doTempPubModel.getcDomainName());
                    roleSubsystemListDto.setRoleName(tAuRole.getRoleName());
                    roleSubsystemListDto.setPermissionGroupName((String) redisUtil.hget(SysDictEnum.PERMISSION_GROUP.getCode(),tAuSubsystemPermission.getPermissionGroupCode()));
                }else {
                    throw new BusinessException("4011111112",getMessage("4011111112"));
                }
                subsystemListDtoList.add(roleSubsystemListDto);
            }
            Integer sum = subsystemPermissionMapper.countByExample(example);
            roleSubsystemRseponse.setColumnList(viewColumnList);
            roleSubsystemRseponse.setPageInfo(new PageInfo<>(subsystemListDtoList));
            roleSubsystemRseponse.setSum(sum);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询角色子系统权限列表成功 ");
        return RestResponse.resultSuccess(roleSubsystemRseponse,restResponseHeader);
    }

    /**
     * 生成权限组list
     * @return
     */
    private List<SubsysPermissionGroupDto> getList(){
        List<SubsysPermissionGroupDto> subsysPermissionGroupDtoList = new ArrayList<>();
        TSysDictItemExample example = new TSysDictItemExample();
        TSysDictItemExample.Criteria criteria = example.createCriteria();
        criteria.andDictCodeEqualTo(SysDictEnum.PERMISSION_GROUP.getCode());
        List<TSysDictItem> tSysDictItems = tSysDictItemMapper.selectByExample(example);
        for (TSysDictItem tSysDictItem:tSysDictItems) {
            SubsysPermissionGroupDto subsysPermissionGroupDto = new SubsysPermissionGroupDto();
            subsysPermissionGroupDto.setPermissionGroupCode(tSysDictItem.getItemCode());
            subsysPermissionGroupDto.setPermissionGroupName(tSysDictItem.getItemName());
            subsysPermissionGroupDto.setPermissionStatus(ConstantUtil.HAS_NO_POWER);
            subsysPermissionGroupDtoList.add(subsysPermissionGroupDto);
        }
        return subsysPermissionGroupDtoList;
    }
}
