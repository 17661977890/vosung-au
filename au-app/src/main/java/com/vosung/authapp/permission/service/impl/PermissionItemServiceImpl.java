package com.vosung.authapp.permission.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.permissionitem.PermissionItemListRequest;
import com.vosung.auapi.client.dto.requestdto.permissionitem.PermissionItemRequestDto;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.permissionitem.PermissionItemResponse;
import com.vosung.auapi.client.dto.responsedto.permissionitem.PermissionItemResponseDto;
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
import com.vosung.authapp.permission.mapper.TAuPermissionGroupMapper;
import com.vosung.authapp.permission.mapper.TAuPermissionItemMapper;
import com.vosung.authapp.permission.mapper.TAuPermissionObjectItemMapper;
import com.vosung.authapp.permission.service.PermissionItemService;
import com.vosung.authapp.role.mapper.TAuRolePermissionObjectMapper;
import com.vosung.boapi.client.BoClient;
import com.vosung.boapi.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 权限项管理业务实现类
 * @Author 彬
 * @Date 2019/5/6
 */
@Slf4j
@Service
public class PermissionItemServiceImpl extends BaseServiceImpl implements PermissionItemService{
    @Autowired
    private TAuPermissionItemMapper tAuPermissionItemMapper;
    @Autowired
    private TAuPermissionGroupMapper tAuPermissionGroupMapper;
    @Autowired
    private TAuPermissionObjectItemMapper permissionObjectItemMapper;
    @Autowired
    private TAuRolePermissionObjectMapper rolePermissionObjectMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserHolder userHolder;

    @Autowired
    private BoClient boClient;


    /**
     * 保存权限项
     * @param permissionItemRequestDto
     * @return
     */
    @Override
    public RestResponse savePermissionItem(PermissionItemRequestDto permissionItemRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuPermissionItem tAuPermissionItem = new TAuPermissionItem();
        Integer record = 0;
        try {
            BeanUtils.copyProperties(tAuPermissionItem,permissionItemRequestDto);
            if(permissionItemRequestDto.getId() == null){
                //新增权限项 判重编码和名称
                RestResponse restResponse = checkAddIsExist(permissionItemRequestDto.getPermissionCode(),permissionItemRequestDto.getPermissionName());
                List<Map> messageList = (List<Map>) restResponse.getBody();
                if(!CollectionUtils.isEmpty(messageList)){
                    return restResponse;
                }
                tAuPermissionItem.setCreateUser(loginUserId);
                tAuPermissionItem.setCreateTime(new Date());
                tAuPermissionItem.setUpdateTime(new Date());
                tAuPermissionItem.setUpdateUser(loginUserId);
                record = tAuPermissionItemMapper.insertSelective(tAuPermissionItem);
            }else {
                //修改权限项 改变进行判重
                TAuPermissionItem tAuPermissionItem1 = tAuPermissionItemMapper.selectByPrimaryKey(permissionItemRequestDto.getId());
                if(tAuPermissionItem1 == null){
                    throw new BusinessException("3011111181",getMessage("3011111181"));
                }
                Boolean a = !tAuPermissionItem1.getPermissionCode().equalsIgnoreCase(permissionItemRequestDto.getPermissionCode());
                Boolean b = !tAuPermissionItem1.getPermissionName().equalsIgnoreCase(permissionItemRequestDto.getPermissionName());
                if(a || b){
                    //只要修改编码或者名称，就判重
                    RestResponse restResponse = checkUpdateIsExist(permissionItemRequestDto.getPermissionCode(),permissionItemRequestDto.getPermissionName(),a,b);
                    List<Map> messageList = (List<Map>) restResponse.getBody();
                    if(!CollectionUtils.isEmpty(messageList)){
                        return restResponse;
                    }
                }
                tAuPermissionItem.setUpdateTime(new Date());
                tAuPermissionItem.setUpdateUser(loginUserId);
                record = tAuPermissionItemMapper.updateByPrimaryKeySelective(tAuPermissionItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(record == 1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("保存权限项信息成功");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 根据id查看权限项详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getPermissionItemInfoById(Integer id) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        PermissionItemResponseDto responseDto = new PermissionItemResponseDto();
        TAuPermissionItem tAuPermissionItem = tAuPermissionItemMapper.selectByPrimaryKey(id);
        if(tAuPermissionItem == null){
            throw new BusinessException("3011111181",getMessage("3011111181"));
        }
        try {
            BeanUtils.copyProperties(responseDto,tAuPermissionItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //查询权限项分组名称
//        TAuPermissionGroup tAuPermissionGroup = tAuPermissionGroupMapper.selectByPrimaryKey(tAuPermissionItem.getPermissionGroupId());
//        if(tAuPermissionGroup == null){
//            throw new BusinessException("3011111191",getMessage("3011111191"));
//        }
        responseDto.setPermissionGroupName((String) redisUtil.hget(SysDictEnum.PERMISSION_GROUP.getCode(),tAuPermissionItem.getPermissionCode()));
        restResponseHeader.setMessage("查询角色详情成功");
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        return RestResponse.resultSuccess(responseDto,restResponseHeader);
    }

    /**
     * 查询权限项列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getPermissionItemList(RestRequest<PermissionItemListRequest> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        PermissionItemResponse permissionItemResponse = new PermissionItemResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(PermissionItemResponseDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();

            TAuPermissionItemExample example = new TAuPermissionItemExample();
            TAuPermissionItemExample.Criteria criteria = example.createCriteria();
            //显示未删除的
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);

            PageMethod.startPage(pageNum,pageSize,"id");
            List<TAuPermissionItem> tAuPermissionItemList =  tAuPermissionItemMapper.selectByExample(example);
            List<PermissionItemResponseDto> permissionItemResponseDtoList = new ArrayList<>();

            for (TAuPermissionItem tAuPermissionItem: tAuPermissionItemList) {
                PermissionItemResponseDto permissionItemResponseDto=new PermissionItemResponseDto();
                BeanUtils.copyProperties(permissionItemResponseDto,tAuPermissionItem);
                permissionItemResponseDto.setPermissionGroupName((String) redisUtil.hget(SysDictEnum.PERMISSION_GROUP.getCode(),permissionItemResponseDto.getPermissionGroupCode()));

                permissionItemResponseDtoList.add(permissionItemResponseDto);
            }
            Integer sum = tAuPermissionItemMapper.countByExample(example);
            permissionItemResponse.setColumnList(viewColumnList);
            permissionItemResponse.setPageInfo(new PageInfo<>(permissionItemResponseDtoList));
            permissionItemResponse.setSum(sum);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询权限项列表成功 ");
        return RestResponse.resultSuccess(permissionItemResponse,restResponseHeader);
    }

    /**
     * （逻辑）删除权限项------已被权限对象绑定的数据也要删除 | 已经业务对象授权的数据也要删除 | 删除操作项绑定的权限项数据
     * @param itemIds
     * @return
     */
    @Transactional
    @Override
    public RestResponse deletePermissionItem(String itemIds) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] itemIdArray = itemIds.split(",");
        List<Map> messageList = new ArrayList<>();
        for (int i=0;i<itemIdArray.length;i++){
            TAuPermissionItem tAuPermissionItem = tAuPermissionItemMapper.selectByPrimaryKey(Integer.valueOf(itemIdArray[i]));
            Map map = new HashMap();
            if(ConstantUtil.YES_OR_NO_N.equals(tAuPermissionItem.getIsDelete())){
                tAuPermissionItem.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                tAuPermissionItem.setUpdateTime(new Date());
                tAuPermissionItem.setUpdateUser(loginUserId);
                tAuPermissionItemMapper.updateByPrimaryKeySelective(tAuPermissionItem);
                map.put("message","权限项编码为："+tAuPermissionItem.getPermissionCode()+" 删除成功");
                map.put("code",ConstantUtil.SUCCESS);
                //同时删除权限对象和权限项的绑定关系
                TAuPermissionObjectItemExample example = new TAuPermissionObjectItemExample();
                TAuPermissionObjectItemExample.Criteria criteria = example.createCriteria();
                criteria.andPermissionItemIdEqualTo(tAuPermissionItem.getId());
                Integer count = permissionObjectItemMapper.deleteByExample(example);
                log.info("===删除权限项id："+tAuPermissionItem.getId()+"同步删除已关联权限对象的数量："+count);
                //同时删除业务对象授权的含权限项数据
                TAuRolePermissionObjectExample example1 = new TAuRolePermissionObjectExample();
                TAuRolePermissionObjectExample.Criteria criteria1 = example1.createCriteria();
                criteria1.andPermissionItemIdEqualTo(tAuPermissionItem.getId());
                Integer count2 = rolePermissionObjectMapper.deleteByExample(example1);
                log.info("===删除权限项id："+tAuPermissionItem.getId()+"同步删除已经过业务对象授权的数量："+count2);
                //todo 考虑是不是要删除子系统授权权限项组得数据（可删可不删，不影响结果，因为在子系统授权得时候，同步授权业务对象，bo接口查该子系统下的业务对象，返回必须绑定权限对象的业务对象以及已经绑定权限项的操作项，已经解绑的就不会返回，也就不会授权）
                //todo 调bo接口 删除操作项绑定的权限项数据（根据权限项id删除所有绑定的操作项关联数据）
                try{
                    Result result = boClient.delOpRelationAu(Integer.valueOf(itemIdArray[i]));
                }catch (Exception e){
                    log.info("删除权限项接口======>调bo接口解除权限项和操作项得绑定====>失败");
                    throw new BusinessException("4011111113",getMessage("4011111113"));
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }


    /**
     * 权限项新增判重
     * @param permissionItemCode
     * @param permissionItemName
     * @return
     */
    private RestResponse checkAddIsExist(String permissionItemCode,String permissionItemName){
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        //逻辑删除的可以重复
        TAuPermissionItemExample example = new TAuPermissionItemExample();
        TAuPermissionItemExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria.andPermissionCodeEqualTo(permissionItemCode);
        TAuPermissionItemExample.Criteria criteria2 = example.createCriteria();
        criteria2.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria2.andPermissionNameEqualTo(permissionItemName);
        example.or(criteria2);
        List<TAuPermissionItem> permissionItemList = tAuPermissionItemMapper.selectByExample(example);
        List<Map> messageList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(permissionItemList)){
            if(permissionItemCode.equalsIgnoreCase(permissionItemList.get(0).getPermissionCode())){
                Map map = new HashMap();
                map.put("code", ConstantUtil.ERROR);
                map.put("message","此权限项编码：'"+permissionItemCode+"'已被使用");
                messageList.add(map);
            }
            if(permissionItemName.equalsIgnoreCase(permissionItemList.get(0).getPermissionName())){
                Map map = new HashMap();
                map.put("code", ConstantUtil.ERROR);
                map.put("message","此权限项名称：'"+permissionItemName+"'已被使用");
                messageList.add(map);
            }
            restResponseHeader.setCode(ConstantUtil.ERROR);
            restResponseHeader.setMessage("新增失败");
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 权限项修改判重
     * @param permissionItemCode
     * @param permissionItemName
     * @param a
     * @param b
     * @return
     */
    private RestResponse checkUpdateIsExist(String permissionItemCode,String permissionItemName,boolean a,boolean b){
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        List<Map> messageList = new ArrayList<>();
        TAuPermissionItemExample example = new TAuPermissionItemExample();
        if(a){
            //此权限项编码变化
            TAuPermissionItemExample.Criteria criteria = example.createCriteria();
            criteria.andPermissionCodeEqualTo(permissionItemCode);
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            List<TAuPermissionItem> permissionItemList = tAuPermissionItemMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(permissionItemList)){
                if(permissionItemCode.equalsIgnoreCase(permissionItemList.get(0).getPermissionCode())){
                    Map map = new HashMap();
                    map.put("code", ConstantUtil.ERROR);
                    map.put("message","此权限项编码 ：'"+permissionItemCode+"'与被使用");
                    messageList.add(map);
                }
            }
            restResponseHeader.setCode(ConstantUtil.ERROR);
            restResponseHeader.setMessage("保存失败");
        }
        if(b){
            //此权限项名变化
            TAuPermissionItemExample.Criteria criteria = example.createCriteria();
            criteria.andPermissionNameEqualTo(permissionItemName);
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            List<TAuPermissionItem> permissionItemList = tAuPermissionItemMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(permissionItemList)){
                if(permissionItemName.equalsIgnoreCase(permissionItemList.get(0).getPermissionName())){
                    Map map = new HashMap();
                    map.put("code", ConstantUtil.ERROR);
                    map.put("message","此权限项名 ：'"+permissionItemName+"'已被使用");
                    messageList.add(map);
                }
            }
            restResponseHeader.setCode(ConstantUtil.ERROR);
            restResponseHeader.setMessage("保存失败");
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
}
