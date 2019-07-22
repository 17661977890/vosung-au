package com.vosung.authapp.role.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.rolepermission.*;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.rolepermission.*;
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
import com.vosung.authapp.role.service.RoleBuObjService;
import com.vosung.authapp.user.mapper.TAuUserMapper;
import com.vosung.authapp.user.mapper.TAuUserOrgRoleMapper;
import com.vosung.boapi.client.BoClient;
import com.vosung.boapi.entity.Result;
import com.vosung.boapi.pojo.DoTempPubModel;
import com.vosung.boapi.pojo.OpreationBean;
import com.vosung.boapi.pojo.SysBoAuth;
import com.vosung.boapi.utils.JsonUtils;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 角色业务对象授权业务层
 * @Author 彬
 * @Date 2019/5/28
 */
@Slf4j
@Service
public class RoleBuObjServiceImpl extends BaseServiceImpl implements RoleBuObjService{
    @Autowired
    private TAuRolePermissionObjectMapper rolePermissionObjectMapper;
    @Autowired
    private UserHolder userHolder;
    @Autowired
    private TAuPermissionItemMapper permissionItemMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TAuPermissionObjectItemMapper permissionObjectItemMapper;
    @Autowired
    private TAuRoleMapper roleMapper;
    @Autowired
    private TAuUserOrgRoleMapper userOrgRoleMapper;
    @Autowired
    private TAuUserMapper userMapper;
    @Autowired
    private TAuSubsystemPermissionMapper subsystemPermissionMapper;
    @Autowired
    private BoClient boClient;

    /**
     * 角色业务对象授权--------------会影响子系统授权
     * @param roleBuObjRequestDto
     * @return
     */
    @Transactional
    @Override
    public RestResponse saveRoleBuObj(RoleBuObjRequestDto roleBuObjRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        Integer roleId = roleBuObjRequestDto.getRoleId();
        List<BusinessObjectDto> objectDtoList = roleBuObjRequestDto.getBusinessObjectList();
        Integer record = 0;
        //子系统 有权权限组list
        List<Integer> subsystemIds = new ArrayList<>();
        List<String> permissionGroupCodes = new ArrayList<>();
        for (BusinessObjectDto businessObjectDto:objectDtoList) {
            if(!subsystemIds.contains(businessObjectDto.getSubsystemId())){
                subsystemIds.add(businessObjectDto.getSubsystemId());
            }
            //查该业务对象有没有授权过（有 有但是关联权限项发生变化  没有）
            TAuRolePermissionObjectExample example = new TAuRolePermissionObjectExample();
            TAuRolePermissionObjectExample.Criteria criteria = example.createCriteria();
            criteria.andRoleIdEqualTo(roleId);
            criteria.andBusinessObjectIdEqualTo(businessObjectDto.getBusinessObjectId());
            List<TAuRolePermissionObject> rolePermissionObjectList =  rolePermissionObjectMapper.selectByExample(example);
            List<PermissionItemDto> permissionItemDtos = businessObjectDto.getPermissionItemDtoList();
            //授权操作：就是新增和修改权限状态（有权无权禁止，数据都在，不会删除）
            if(CollectionUtils.isEmpty(rolePermissionObjectList)){
                //该角色，该业务对象没有授权过，新增操作----------（新增前提是：业务对象的权限项必须有一个是设为有权，初次授权，全是无权不会新增，前端控制这个逻辑）
                for (PermissionItemDto permissionItemDto:permissionItemDtos) {
                    TAuRolePermissionObject tAuRolePermissionObject = new TAuRolePermissionObject();
                    tAuRolePermissionObject.setRoleId(roleId);
                    tAuRolePermissionObject.setBusinessDomainId(businessObjectDto.getBusinessDomainId());
                    tAuRolePermissionObject.setSubsystemId(businessObjectDto.getSubsystemId());
                    tAuRolePermissionObject.setBusinessObjectId(businessObjectDto.getBusinessObjectId());
                    tAuRolePermissionObject.setPermissionItemId(permissionItemDto.getPermissionItemId());
                    tAuRolePermissionObject.setPermissionStatus(permissionItemDto.getPermissionStatus());
                    tAuRolePermissionObject.setPermissionObjectId(businessObjectDto.getPermissionObjectId());
                    tAuRolePermissionObject.setUrl(permissionItemDto.getUrl());
                    tAuRolePermissionObject.setCreateUser(loginUserId);
                    tAuRolePermissionObject.setCreateTime(new Date());
                    tAuRolePermissionObject.setUpdateUser(loginUserId);
                    tAuRolePermissionObject.setUpdateTime(new Date());
                    rolePermissionObjectMapper.insertSelective(tAuRolePermissionObject);
                    record++;
                }
                //todo 修改业务对象已设置（根据业务对象id）-----------因为不同角色授权，设置字段只有一个，不能根据角色对应，所以bo暂时不需要修改是否设置字段
//                try {
//                    SysBoAuth sysBoAuth = new SysBoAuth();
//                    sysBoAuth.setBoId(businessObjectDto.getBusinessObjectId());
//                    sysBoAuth.setSettingFlag(ConstantUtil.YES_OR_NO_Y);
//                    boClient.addAuBo(sysBoAuth);
//                }catch (Exception e){
//                    log.info("业务对象授权接口（新增操作）====>调用bo接口：{修改业务对象是否设置授权字段为Y} =====>失败");
//                    e.printStackTrace();
//                }


            }else {
                Integer count1 = 0;
                for (TAuRolePermissionObject tAuRolePermissionObject:rolePermissionObjectList) {
                    if(ConstantUtil.HAS_POWER.equals(tAuRolePermissionObject.getPermissionStatus())){
                        count1++;
                    }
                }
                Integer count = 0;
                //该角色，该业务对象授权过，那只是修改具体权限状态即可
                for (PermissionItemDto permissionItemDto: permissionItemDtos) {
                    if(ConstantUtil.HAS_POWER.equals(permissionItemDto.getPermissionStatus())){
                        count++;
                    }
                    //角色 业务对象id 权限项id 确定唯一数据
                    TAuRolePermissionObjectExample example1 = new TAuRolePermissionObjectExample();
                    TAuRolePermissionObjectExample.Criteria criteria1 = example1.createCriteria();
                    criteria1.andRoleIdEqualTo(roleId);
                    criteria1.andBusinessObjectIdEqualTo(businessObjectDto.getBusinessObjectId());
                    criteria1.andPermissionItemIdEqualTo(permissionItemDto.getPermissionItemId());
                    List<TAuRolePermissionObject>  rolePermissionObjectList1 = rolePermissionObjectMapper.selectByExample(example1);
                    if(!CollectionUtils.isEmpty(rolePermissionObjectList1)){
                        TAuRolePermissionObject tAuRolePermissionObject = rolePermissionObjectList1.get(0);
                        //同一条数据，权限状态变化，进行修改
                        if(!Objects.equals(tAuRolePermissionObject.getPermissionStatus(), permissionItemDto.getPermissionStatus())){
                            tAuRolePermissionObject.setPermissionStatus(permissionItemDto.getPermissionStatus());
                            tAuRolePermissionObject.setUpdateUser(loginUserId);
                            tAuRolePermissionObject.setUpdateTime(new Date());
                            rolePermissionObjectMapper.updateByPrimaryKeySelective(tAuRolePermissionObject);
                            record++;
                        }
                    }

                    if(CollectionUtils.isEmpty(rolePermissionObjectList1)){
                        //说明该业务对象新增了权限项---新增数据
                        TAuRolePermissionObject tAuRolePermissionObject = new TAuRolePermissionObject();
                        tAuRolePermissionObject.setRoleId(roleId);
                        tAuRolePermissionObject.setBusinessDomainId(businessObjectDto.getBusinessDomainId());
                        tAuRolePermissionObject.setSubsystemId(businessObjectDto.getSubsystemId());
                        tAuRolePermissionObject.setBusinessObjectId(businessObjectDto.getBusinessObjectId());
                        tAuRolePermissionObject.setPermissionItemId(permissionItemDto.getPermissionItemId());
                        tAuRolePermissionObject.setPermissionStatus(permissionItemDto.getPermissionStatus());
                        tAuRolePermissionObject.setPermissionObjectId(businessObjectDto.getPermissionObjectId());
                        tAuRolePermissionObject.setUrl(permissionItemDto.getUrl());
                        tAuRolePermissionObject.setCreateUser(loginUserId);
                        tAuRolePermissionObject.setCreateTime(new Date());
                        tAuRolePermissionObject.setUpdateUser(loginUserId);
                        tAuRolePermissionObject.setUpdateTime(new Date());
                        rolePermissionObjectMapper.insertSelective(tAuRolePermissionObject);
                    }
                }
                //修改完后，count1>0 count=0 即该业务对象的以前的权限项含有有权（业务对象已设置），全部改为无权----- todo 调bo接口，设该业务对象为未设置
//                if(count1>0 && count==0){
//                    System.out.println("=========业务对象授权修改，该业务对象的所有权限项全部改为无权，业务对象设置字段修改为未设置=================");
//                    try {
//                        SysBoAuth sysBoAuth = new SysBoAuth();
//                        sysBoAuth.setBoId(businessObjectDto.getBusinessObjectId());
//                        sysBoAuth.setSettingFlag(ConstantUtil.YES_OR_NO_N);
//                        boClient.addAuBo(sysBoAuth);
//                    }catch (Exception e){
//                        log.info("业务对象授权接口（修改操作）====>调用bo接口：{修改业务对象是否设置授权字段为N} =====>失败");
//                        e.printStackTrace();
//                    }
//                }else if(count1==0 && count>0){
//                    //修改完后，count1==0 count>0 即该业务对象的以前全部权限项为无权（业务对象未设置），有些权限项或所有改为有权，----todo 调bo接口，设该业务对象为已设置
//                    try {
//                        SysBoAuth sysBoAuth = new SysBoAuth();
//                        sysBoAuth.setSettingFlag(ConstantUtil.YES_OR_NO_Y);
//                        sysBoAuth.setBoId(businessObjectDto.getBusinessObjectId());
//                        boClient.addAuBo(sysBoAuth);
//                    }catch (Exception e){
//                        log.info("业务对象授权接口（修改操作）====>调用bo接口：{修改业务对象是否设置授权字段为Y} =====>失败");
//                        e.printStackTrace();
//                    }
//                }
            }
        }
        //保存完业务对象授权后，同时所属子系统的权限组授权也保存
        //根据子系统和角色查询业务对象授权，遍历子系统下的业务对象授权数据，
        // 先将有权的权限项处理，（一个有权即该权限组有权），只要有权的权限项对应的权限组就是有权---再根据子系统id和权限组code去子系统授权表校验，如果之前有且有权不处理，有但无权修改，没有新增有权，
        //在将无权的权限项处理，同一类型（同一权限组下）的所有权限项都是无权，该权限组才是无权---再根据子系统id和权限组code去子系统授权表校验，如果之前有且无权不处理，有但有权修改，没有新增无权，
        for (Integer subsystemId: subsystemIds) {
            TAuRolePermissionObjectExample example = new TAuRolePermissionObjectExample();
            TAuRolePermissionObjectExample.Criteria criteria = example.createCriteria();
            criteria.andSubsystemIdEqualTo(subsystemId);
            criteria.andRoleIdEqualTo(roleId);
            List<TAuRolePermissionObject> rolePermissionObjectList = rolePermissionObjectMapper.selectByExample(example);
            for (TAuRolePermissionObject tAuRolePermissionObject:rolePermissionObjectList) {
                if(ConstantUtil.HAS_POWER.equals(tAuRolePermissionObject.getPermissionStatus())){
                    TAuPermissionItem tAuPermissionItem = permissionItemMapper.selectByPrimaryKey(tAuRolePermissionObject.getPermissionItemId());
                    //同样的权限组减少重复处理,权限组和子系统编码确定唯一数据
                    if(!permissionGroupCodes.contains(tAuPermissionItem.getPermissionGroupCode())){
                        permissionGroupCodes.add(tAuPermissionItem.getPermissionGroupCode());
                        TAuSubsystemPermissionExample example1 = new TAuSubsystemPermissionExample();
                        TAuSubsystemPermissionExample.Criteria criteria1=example1.createCriteria();
                        criteria1.andSubsystemIdEqualTo(subsystemId);
                        criteria1.andPermissionGroupCodeEqualTo(tAuPermissionItem.getPermissionGroupCode());
                        List<TAuSubsystemPermission> subsystemPermissionList = subsystemPermissionMapper.selectByExample(example1);
                        if(CollectionUtils.isEmpty(subsystemPermissionList)){
                            //之前没有，新增数据。设为有权
                            TAuSubsystemPermission tAuSubsystemPermission = new TAuSubsystemPermission();
                            tAuSubsystemPermission.setSubsystemId(subsystemId);
                            tAuSubsystemPermission.setPermissionGroupCode(tAuPermissionItem.getPermissionGroupCode());
                            tAuSubsystemPermission.setPermissionStatus(ConstantUtil.HAS_POWER);
                            tAuSubsystemPermission.setBusinessDomainId(tAuRolePermissionObject.getBusinessDomainId());
                            tAuSubsystemPermission.setRoleId(roleId);
                            tAuSubsystemPermission.setCreateTime(new Date());
                            tAuSubsystemPermission.setCreateUser(loginUserId);
                            tAuSubsystemPermission.setUpdateTime(new Date());
                            tAuSubsystemPermission.setUpdateUser(loginUserId);
                            subsystemPermissionMapper.insertSelective(tAuSubsystemPermission);
                        }else {
                            //之前有，看之前时有权还是无权,之前有权不做处理，无权改为有权
                            TAuSubsystemPermission tAuSubsystemPermission = subsystemPermissionList.get(0);
                            if(ConstantUtil.HAS_NO_POWER.equals(tAuSubsystemPermission.getPermissionStatus())){
                                //修改为有权
                                tAuSubsystemPermission.setPermissionStatus(ConstantUtil.HAS_POWER);
                                tAuSubsystemPermission.setUpdateTime(new Date());
                                tAuSubsystemPermission.setUpdateUser(loginUserId);
                                subsystemPermissionMapper.updateByPrimaryKeySelective(tAuSubsystemPermission);
                            }
                        }
                    }

                }
            }
            for (TAuRolePermissionObject tAuRolePermissionObject:rolePermissionObjectList) {
                if(ConstantUtil.HAS_NO_POWER.equals(tAuRolePermissionObject.getPermissionStatus())){
                    TAuPermissionItem tAuPermissionItem = permissionItemMapper.selectByPrimaryKey(tAuRolePermissionObject.getPermissionItemId());
                    //上面的一次遍历，已经将有权的权限组添加，如果仍有没有包含的，说明(子系统下同一类型（同一权限组）的权限项)无权的
                    if(!permissionGroupCodes.contains(tAuPermissionItem.getPermissionGroupCode())){
                        permissionGroupCodes.add(tAuPermissionItem.getPermissionGroupCode());
                        TAuSubsystemPermissionExample example1 = new TAuSubsystemPermissionExample();
                        TAuSubsystemPermissionExample.Criteria criteria1=example1.createCriteria();
                        criteria1.andSubsystemIdEqualTo(subsystemId);
                        criteria1.andPermissionGroupCodeEqualTo(tAuPermissionItem.getPermissionGroupCode());
                        List<TAuSubsystemPermission> subsystemPermissionList = subsystemPermissionMapper.selectByExample(example1);
                        if(CollectionUtils.isEmpty(subsystemPermissionList)){
                            //之前没有，新增数据,设为无权
                            TAuSubsystemPermission tAuSubsystemPermission = new TAuSubsystemPermission();
                            tAuSubsystemPermission.setSubsystemId(subsystemId);
                            tAuSubsystemPermission.setPermissionGroupCode(tAuPermissionItem.getPermissionGroupCode());
                            tAuSubsystemPermission.setPermissionStatus(ConstantUtil.HAS_NO_POWER);
                            tAuSubsystemPermission.setBusinessDomainId(tAuRolePermissionObject.getBusinessDomainId());
                            tAuSubsystemPermission.setRoleId(roleId);
                            tAuSubsystemPermission.setCreateTime(new Date());
                            tAuSubsystemPermission.setCreateUser(loginUserId);
                            tAuSubsystemPermission.setUpdateTime(new Date());
                            tAuSubsystemPermission.setUpdateUser(loginUserId);
                            subsystemPermissionMapper.insertSelective(tAuSubsystemPermission);
                        }else {
                            //之前有，原有是有权，改为无权，原有是无权不变
                            TAuSubsystemPermission tAuSubsystemPermission = subsystemPermissionList.get(0);
                            if(ConstantUtil.HAS_POWER.equals(tAuSubsystemPermission.getPermissionStatus())){
                                //修改为有权
                                tAuSubsystemPermission.setPermissionStatus(ConstantUtil.HAS_NO_POWER);
                                tAuSubsystemPermission.setUpdateTime(new Date());
                                tAuSubsystemPermission.setUpdateUser(loginUserId);
                                subsystemPermissionMapper.updateByPrimaryKeySelective(tAuSubsystemPermission);
                            }
                        }
                    }
                }
            }
        }

        restResponseHeader.setMessage("业务对象授权成功");
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 根据角色id查询有权业务对象list(含权限项list)------只展示已设置的业务对象，因为表里可能会员某些业务对象授权数据，但是全是无权状态，即未设置，拿这些数据不需要显示
     * @param roleId
     * @return
     */
    @Override
    public RestResponse getBuObjListByRoleId(Integer roleId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<RoleBuObjResponseDto> roleBuObjResponseDtoList = new ArrayList<>();
        TAuRolePermissionObjectExample example = new TAuRolePermissionObjectExample();
        TAuRolePermissionObjectExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<TAuRolePermissionObject> rolePermissionObjectList = rolePermissionObjectMapper.selectByExample(example);
        //去重业务对象id(角色id 业务对象id 权限项id确定唯一数据)
        List<Integer> buObjIdList = new ArrayList<>();
        for (TAuRolePermissionObject t:rolePermissionObjectList) {
            if(!buObjIdList.contains(t.getBusinessObjectId())){
                buObjIdList.add(t.getBusinessObjectId());
            }
        }
        for (Integer buObjId:buObjIdList) {
            RoleBuObjResponseDto roleBuObjResponseDto = new RoleBuObjResponseDto();
            //调bo接口，进行赋值前端展示，根据业务对象id查 业务对象信息（含所属业务领域，所属子系统--主要是id/name 以及 绑定的权限对象id，是否设置字段）
            //todo 待优化,循环调服务接口，效率低，将循环结构和调用的接口改造，一次查询，循环赋值
            DoTempPubModel doTempPubModel = new DoTempPubModel();
            try {
                Result result = boClient.selectAuBo(buObjId);
                doTempPubModel = (DoTempPubModel)result.getData();
            }catch (Exception e){
                log.info("角色id查询有权业务对象list接口：=====>调bo接口：{根据业务对象id查相关详情信息}====>失败");
                e.printStackTrace();
            }
            if(doTempPubModel==null){
                throw new BusinessException("4011111111",getMessage("4011111111"));
            }
            //加判断条件，是否设置字段为已设置,才添加进list，前端展示（有些已授权数据全是无权则保存时修改为未设置了）---todo 设置字段修改取消，暂时只要授权数据都显示
            roleBuObjResponseDto.setBusinessDomainName(doTempPubModel.getDomainName());
            roleBuObjResponseDto.setBusinessDomainId(doTempPubModel.getDomainId());
            roleBuObjResponseDto.setSubsystemId(doTempPubModel.getcDomainId());
            roleBuObjResponseDto.setSubsystemName(doTempPubModel.getcDomainName());
            roleBuObjResponseDto.setBusinessObjectName(doTempPubModel.getName());
            roleBuObjResponseDto.setIsSetting(ConstantUtil.YES_OR_NO_Y);
            roleBuObjResponseDto.setBusinessObjectId(buObjId);
            roleBuObjResponseDto.setPermissionObjectId(doTempPubModel.getAuId());
            roleBuObjResponseDtoList.add(roleBuObjResponseDto);

        }
        for (RoleBuObjResponseDto roleBuObjResponseDto:roleBuObjResponseDtoList) {
            //权限组code list 去重
            List<String> groupCodes = new ArrayList<>();
            //根据角色和业务对象 查授权的权限项list
            List<BuObjPermissionItemDto> buObjPermissionItemDtos = rolePermissionObjectMapper.getBuObjPermissionByRoleAndBo(roleId,roleBuObjResponseDto.getBusinessObjectId());
            for (BuObjPermissionItemDto buObjPermissionItemDto:buObjPermissionItemDtos) {
                //权限组名称只有一次即可
                if(!groupCodes.contains(buObjPermissionItemDto.getPermissionGroupCode())){
                    groupCodes.add(buObjPermissionItemDto.getPermissionGroupCode());
                    buObjPermissionItemDto.setPermissionGroupName((String) redisUtil.hget(SysDictEnum.PERMISSION_GROUP.getCode(),buObjPermissionItemDto.getPermissionGroupCode()));
                }else {
                    buObjPermissionItemDto.setPermissionGroupName("");
                }

            }
            roleBuObjResponseDto.setPermissionItemDtoList(buObjPermissionItemDtos);
        }
        restResponseHeader.setMessage("根据角色查有权业务对象列表成功");
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        return RestResponse.resultSuccess(roleBuObjResponseDtoList,restResponseHeader);
    }

    /**
     * 获取全部已绑定权限的业务对象（含权限项，权限对象和项关联表查）-----角色授权过的查授权表得权限项id，状态
     * todo 接口结构待优化，调bo接口地方尽量不要放在循环中
     * @param roleId
     * @return
     */
    @Override
    public RestResponse getAllBuObjList(Integer roleId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<RoleBuObjResponseDto> roleBuObjResponseDtoList = new ArrayList<>();
        //todo 调bo接口获取所有已绑定权限对象的业务对象（含业务领域，子系统的id，name信息，权限对象id，是否设置字段）
        List<DoTempPubModel> doTempPubModelList = new ArrayList<>();
        try {
            Result result = boClient.auDomainBo(null,true);
            doTempPubModelList = (List<DoTempPubModel>) result.getData();
        }catch (Exception e){
            log.info("获取所有已绑定权限对象的业务对象接口：=====>调bo接口：====>失败");
            e.printStackTrace();
        }
        //遍历list，属性值赋值给 RoleBuObjResponseDto 组装roleBuObjResponseDtoList
        for (DoTempPubModel doTempPubModel:doTempPubModelList) {
            RoleBuObjResponseDto roleBuObjResponseDto = new RoleBuObjResponseDto();
            roleBuObjResponseDto.setBusinessDomainName(doTempPubModel.getDomainName());
            roleBuObjResponseDto.setBusinessDomainId(doTempPubModel.getDomainId());
            roleBuObjResponseDto.setSubsystemId(doTempPubModel.getcDomainId());
            roleBuObjResponseDto.setSubsystemName(doTempPubModel.getcDomainName());
            roleBuObjResponseDto.setBusinessObjectName(doTempPubModel.getName());
//            roleBuObjResponseDto.setIsSetting(doTempPubModel.getSettingFlag());
            roleBuObjResponseDto.setBusinessObjectId(doTempPubModel.getBoId());
            roleBuObjResponseDto.setPermissionObjectId(doTempPubModel.getAuId());
            roleBuObjResponseDtoList.add(roleBuObjResponseDto);
        }
        //获取角色授权的业务对象
        TAuRolePermissionObjectExample example = new TAuRolePermissionObjectExample();
        TAuRolePermissionObjectExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<TAuRolePermissionObject> rolePermissionObjectList = rolePermissionObjectMapper.selectByExample(example);
        //去重业务对象id(角色id 业务对象id 权限项id确定唯一数据，其余为冗余数据)
        List<Integer> buObjIdList = new ArrayList<>();
        for (TAuRolePermissionObject t:rolePermissionObjectList) {
            if(!buObjIdList.contains(t.getBusinessObjectId())){
                buObjIdList.add(t.getBusinessObjectId());
            }
        }
        //组装结构
        for (RoleBuObjResponseDto roleBuObjResponseDto: roleBuObjResponseDtoList) {

            //如果业务对象是已授权的，并查授权表得权限项list，
            if(buObjIdList.contains(roleBuObjResponseDto.getBusinessObjectId())){
                //已设置
                roleBuObjResponseDto.setIsSetting(ConstantUtil.YES_OR_NO_Y);
                //权限组code list 去重
                List<String> groupCodes = new ArrayList<>();
                //根据角色和业务对象 查授权的权限项list
                List<BuObjPermissionItemDto> buObjPermissionItemDtos = rolePermissionObjectMapper.getBuObjPermissionByRoleAndBo(roleId,roleBuObjResponseDto.getBusinessObjectId());
                for (BuObjPermissionItemDto buObjPermissionItemDto:buObjPermissionItemDtos) {
                    //权限组名称只有一次即可
                    if(!groupCodes.contains(buObjPermissionItemDto.getPermissionGroupCode())){
                        buObjPermissionItemDto.setPermissionGroupName((String) redisUtil.hget(SysDictEnum.PERMISSION_GROUP.getCode(),buObjPermissionItemDto.getPermissionGroupCode()));
                        groupCodes.add(buObjPermissionItemDto.getPermissionGroupCode());
                    }else {
                        buObjPermissionItemDto.setPermissionGroupName("");
                    }

                }
                roleBuObjResponseDto.setPermissionItemDtoList(buObjPermissionItemDtos);
            }else {
                //未设置
                roleBuObjResponseDto.setIsSetting(ConstantUtil.YES_OR_NO_N);
                //如果业务对象是未授权，未设置，todo 根据业务对象id查业务对象绑定得操作项信息（操作项绑定得url 权限项id），根据权限项id查权限项信息赋值
                Result<List<OpreationBean>> result =  boClient.queryBoBingOpreation(roleBuObjResponseDto.getBusinessObjectId(),true);
                List<OpreationBean> opreationBeans = result.getData();
                if(CollectionUtils.isEmpty(opreationBeans)){
                    opreationBeans = new ArrayList<>();
                }
                List<BuObjPermissionItemDto> buObjPermissionItemDtos = new ArrayList<>();
                //权限组code list 去重
                List<String> groupCodes1 = new ArrayList<>();
                for (OpreationBean opreationBean:opreationBeans) {
                    BuObjPermissionItemDto buObjPermissionItemDto = new BuObjPermissionItemDto();
                    TAuPermissionItem tAuPermissionItem = permissionItemMapper.selectByPrimaryKey(opreationBean.getOpAuId());
                    //对list进行改装（权限项组一样的只显示第一个，其余置空）
                    if(!groupCodes1.contains(tAuPermissionItem.getPermissionGroupCode())){
                        buObjPermissionItemDto.setPermissionGroupName((String) redisUtil.hget(SysDictEnum.PERMISSION_GROUP.getCode(),tAuPermissionItem.getPermissionGroupCode()));
                        groupCodes1.add(tAuPermissionItem.getPermissionGroupCode());
                    }else {
                        buObjPermissionItemDto.setPermissionGroupName("");
                    }
                    buObjPermissionItemDto.setPermissionItemId(opreationBean.getOpAuId());
                    //全部为无效
                    buObjPermissionItemDto.setPermissionStatus(ConstantUtil.HAS_NO_POWER);
                    buObjPermissionItemDto.setPermissionItemName(tAuPermissionItem.getPermissionName());
                    buObjPermissionItemDto.setPermissionGroupCode(tAuPermissionItem.getPermissionGroupCode());
                    buObjPermissionItemDtos.add(buObjPermissionItemDto);
                }
                roleBuObjResponseDto.setPermissionItemDtoList(buObjPermissionItemDtos);

            }
        }
        restResponseHeader.setMessage("查所有绑定权限对象的业务对象列表成功");
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        return RestResponse.resultSuccess(roleBuObjResponseDtoList,restResponseHeader);
    }

    /**
     * 查询角色权限
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getBuObjPermissionListByRoleId(RestRequest<RoleBuObjRequest> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        RoleBuObjResponse roleBuObjResponse = new RoleBuObjResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(RoleBuObjListDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();

            PageMethod.startPage(pageNum,pageSize,"id");
            List<RoleBuObjListDto> roleBuObjListDtoList =  rolePermissionObjectMapper.getRoleBuObjListDtoList(restRequest.getBody().getRoleId());
            for (RoleBuObjListDto roleBuObjListDto: roleBuObjListDtoList) {
                //todo: 调bo接口查业务对象  业务领域 子系统详情（根据业务对象id 关联查得到id name）
                Result result = boClient.selectAuBo(roleBuObjListDto.getBusinessObjectId());
                DoTempPubModel doTempPubModel = (DoTempPubModel)result.getData();
                if(doTempPubModel!=null){
                    roleBuObjListDto.setBusinessDomainName(doTempPubModel.getDomainName());
                    roleBuObjListDto.setSubsystemName(doTempPubModel.getcDomainName());
                    roleBuObjListDto.setBusinessObjectName(doTempPubModel.getName());
                }
            }
            roleBuObjResponse.setColumnList(viewColumnList);
            roleBuObjResponse.setPageInfo(new PageInfo<>(roleBuObjListDtoList));
            roleBuObjResponse.setSum((int) new PageInfo<>(roleBuObjListDtoList).getTotal());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询角色权限列表成功 ");
        return RestResponse.resultSuccess(roleBuObjResponse,restResponseHeader);
    }

    /**
     * 查询用户权限
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getUserPermissionByUserId(RestRequest<UserPermissionRequest> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        UserPermissionResponse userPermissionResponse = new UserPermissionResponse();
        try{
            //获取用户角色
            TAuUserOrgRoleExample example = new TAuUserOrgRoleExample();
            TAuUserOrgRoleExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(restRequest.getBody().getUserId());
            List<TAuUserOrgRole> userOrgRoleList =  userOrgRoleMapper.selectByExample(example);
            List<Integer>  roleIds = new ArrayList<>();
            for (TAuUserOrgRole tAuUserOrgRole:userOrgRoleList) {
                if(tAuUserOrgRole.getRoleId()!=null && !roleIds.contains(tAuUserOrgRole.getRoleId())){
                    roleIds.add(tAuUserOrgRole.getRoleId());
                }
            }
            TAuUser tAuUser = userMapper.selectByPrimaryKey(restRequest.getBody().getUserId());
            //查用户权限
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(UserPermissionDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();
            PageMethod.startPage(pageNum,pageSize,"id");
            List<UserPermissionDto> userPermissionDtos = rolePermissionObjectMapper.getUserPermissionDtoList(roleIds);
            for (UserPermissionDto userPermissionDto: userPermissionDtos) {
                userPermissionDto.setUserId(restRequest.getBody().getUserId());
                userPermissionDto.setName(tAuUser.getName());
                //todo: 调bo接口查业务对象  业务领域 子系统详情（根据业务对象id 关联查得到id name）
                Result result = boClient.selectAuBo(userPermissionDto.getBusinessObjectId());
                DoTempPubModel doTempPubModel = (DoTempPubModel)result.getData();
                if(doTempPubModel!=null){
                    userPermissionDto.setBusinessDomainName(doTempPubModel.getDomainName());
                    userPermissionDto.setSubsystemName(doTempPubModel.getcDomainName());
                    userPermissionDto.setBusinessObjectName(doTempPubModel.getName());
                }
            }
            userPermissionResponse.setColumnList(viewColumnList);
            userPermissionResponse.setPageInfo(new PageInfo<>(userPermissionDtos));
            userPermissionResponse.setSum((int) new PageInfo<>(userPermissionDtos).getTotal());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询用户权限列表成功 ");
        return RestResponse.resultSuccess(userPermissionResponse,restResponseHeader);
    }
}
