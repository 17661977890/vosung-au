package com.vosung.authapp.permission.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.permissionobject.PermissionObjectRequest;
import com.vosung.auapi.client.dto.requestdto.permissionobject.PermissionObjectRequestDto;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.permissionobject.PermissionObjectResponse;
import com.vosung.auapi.client.dto.responsedto.permissionobject.PermissionObjectResponseDto;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.ConstantUtil;
import com.vosung.authapp.common.constant.FieldMessageCls;
import com.vosung.authapp.common.constant.UserHolder;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.permission.mapper.TAuPermissionItemMapper;
import com.vosung.authapp.permission.mapper.TAuPermissionObjectItemMapper;
import com.vosung.authapp.permission.mapper.TAuPermissionObjectMapper;
import com.vosung.authapp.permission.service.PermissionObjectService;
import com.vosung.authapp.role.mapper.TAuRolePermissionObjectMapper;
import com.vosung.authapp.role.mapper.TAuSubsystemPermissionMapper;
import com.vosung.boapi.client.BoClient;
import com.vosung.boapi.entity.Result;
import com.vosung.boapi.pojo.DoTempPubModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 权限对象管理业务层
 * @Author 彬
 * @Date 2019/5/6
 */
@Slf4j
@Service
public class PermissionObjectServiceImpl extends BaseServiceImpl implements PermissionObjectService{
    @Autowired
    private TAuPermissionObjectMapper tAuPermissionObjectMapper;
    @Autowired
    private TAuPermissionObjectItemMapper tAuPermissionObjectItemMapper;
    @Autowired
    private TAuPermissionItemMapper tAuPermissionItemMapper;
    @Autowired
    private UserHolder userHolder;
    @Autowired
    private BoClient boClient;
    @Autowired
    private TAuRolePermissionObjectMapper rolePermissionObjectMapper;
    @Autowired
    private TAuSubsystemPermissionMapper subsystemPermissionMapper;

    /**
     * 保存权限对象
     * @param permissionObjectRequestDto
     * @return
     */
    @Transactional
    @Override
    public RestResponse savePermissionObject(PermissionObjectRequestDto permissionObjectRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuPermissionObject tAuPermissionObject= new TAuPermissionObject();
        //权限项列表入参
        List<Integer> itemIds = permissionObjectRequestDto.getPermissionItemIds();
        if(CollectionUtils.isEmpty(itemIds)){
            itemIds = new ArrayList<>();
        }
        Integer record = 0;
        try {
            BeanUtils.copyProperties(tAuPermissionObject, permissionObjectRequestDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(permissionObjectRequestDto.getId() == null){
            //新增权限对象 判重编码
            checkIsExist(permissionObjectRequestDto.getPermissionObjectCode());
            tAuPermissionObject.setCreateUser(loginUserId);
            tAuPermissionObject.setCreateTime(new Date());
            tAuPermissionObject.setUpdateTime(new Date());
            tAuPermissionObject.setUpdateUser(loginUserId);
            //返回新增数据主键
            record = tAuPermissionObjectMapper.insertSelective(tAuPermissionObject);
            //保存权限对象和权限项关联信息
            for (Integer itemId:itemIds) {
                insertPermissionObjectItem(itemId,tAuPermissionObject.getId());
            }

        }else {
            //修改权限对象 改变进行判重
            TAuPermissionObject tAuPermissionObject1 = tAuPermissionObjectMapper.selectByPrimaryKey(permissionObjectRequestDto.getId());
            if(tAuPermissionObject1 == null){
                throw new BusinessException("3011111181",getMessage("3011111181"));
            }
            Boolean a = !tAuPermissionObject1.getPermissionObjectCode().equalsIgnoreCase(permissionObjectRequestDto.getPermissionObjectCode());
            if(a){
                //只要修改编码或者名称，就判重
                checkIsExist(permissionObjectRequestDto.getPermissionObjectCode());
            }
            tAuPermissionObject.setUpdateTime(new Date());
            tAuPermissionObject.setUpdateUser(loginUserId);
            record = tAuPermissionObjectMapper.updateByPrimaryKeySelective(tAuPermissionObject);
            //修改权限项与权限对象的关联信息
            List<Integer> relatedItemIds = new ArrayList<>();
            TAuPermissionObjectItemExample example = new TAuPermissionObjectItemExample();
            TAuPermissionObjectItemExample.Criteria criteria = example.createCriteria();
            criteria.andPermissionObjectIdEqualTo(permissionObjectRequestDto.getId());
            List<TAuPermissionObjectItem> tAuPermissionObjectItemList = tAuPermissionObjectItemMapper.selectByExample(example);
            //权限项变化的三种情况，增多（新增），减少（删除），不变（不做处理）
            Integer dCount =0;
            Integer iCount =0;
            for (TAuPermissionObjectItem tAuPermissionObjectItem:tAuPermissionObjectItemList) {
                relatedItemIds.add(tAuPermissionObjectItem.getPermissionItemId());
                if(!itemIds.contains(tAuPermissionObjectItem.getPermissionItemId())){
                   //删除了权限项关联数据
                   tAuPermissionObjectItemMapper.deleteByPrimaryKey(tAuPermissionObjectItem.getId());
                   dCount++;
                }
            }
            log.info("=========修改权限对象和权限项的关联入参信息，修改后的变少，删除之前多的,删除数："+dCount);
            for (Integer itemId:itemIds) {
                if(!relatedItemIds.contains(itemId)){
                    //新增了权限项关联关系，新增数据
                    insertPermissionObjectItem(itemId,permissionObjectRequestDto.getId());
                    iCount++;
                }
            }
            log.info("=========修改权限对象和权限项的关联入参信息，修改后的变多，新增关联关系："+iCount);
            if(dCount==0 && iCount ==0){
                log.info("=========修改权限对象和权限项的关联入参信息，关联情况没有发生变化===================");
            }

        }
        if(record == 1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("保存权限对象信息成功");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 根据id查询权限对象详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getPermissionObjectInfoById(Integer id) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        PermissionObjectResponseDto responseDto = new PermissionObjectResponseDto();
        TAuPermissionObject tAuPermissionObject = tAuPermissionObjectMapper.selectByPrimaryKey(id);
        try {
            BeanUtils.copyProperties(responseDto,tAuPermissionObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //todo （1）调bo接口查询子系统名称 赋值
        DoTempPubModel doTempPubModel =null;
        try {
            Result<DoTempPubModel> result =  boClient.queryChildrenById(responseDto.getSubsystemId());
            doTempPubModel =result.getData();
        }catch (Exception e){
            log.info("查权限对象详情接口：=====>调bo接口：{根据子系统id查相关详情信息}====>失败");
            e.printStackTrace();
        }
        if(doTempPubModel!=null){
            responseDto.setSubsystemName(doTempPubModel.getcDomainName());
        }else {
            throw new BusinessException("4011111112",getMessage("4011111112"));
        }
        //关联权限项列表
        List<TAuPermissionItem> permissionItemList = new ArrayList<>();
        TAuPermissionObjectItemExample example = new TAuPermissionObjectItemExample();
        TAuPermissionObjectItemExample.Criteria criteria = example.createCriteria();
        criteria.andPermissionObjectIdEqualTo(id);
        List<TAuPermissionObjectItem> tAuPermissionObjectItemList = tAuPermissionObjectItemMapper.selectByExample(example);
        for (TAuPermissionObjectItem tAuPermissionObjectItem:tAuPermissionObjectItemList) {
            TAuPermissionItem tAuPermissionItem = tAuPermissionItemMapper.selectByPrimaryKey(tAuPermissionObjectItem.getPermissionItemId());
            permissionItemList.add(tAuPermissionItem);
        }
        responseDto.setPermissionItemList(permissionItemList);
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询权限对象详情成功");
        return RestResponse.resultSuccess(responseDto,restResponseHeader);
    }

    /**
     * 查询权限对象列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getPermissionObjectList(RestRequest<PermissionObjectRequest> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        PermissionObjectResponse permissionObjectResponse = new PermissionObjectResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(PermissionObjectResponseDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();

            TAuPermissionObjectExample example = new TAuPermissionObjectExample();
            TAuPermissionObjectExample.Criteria criteria = example.createCriteria();
            //显示未删除的
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);

            PageMethod.startPage(pageNum,pageSize,"id");
            List<TAuPermissionObject> tAuPermissionObjectList =  tAuPermissionObjectMapper.selectByExample(example);
            List<PermissionObjectResponseDto> permissionObjectResponseDtos = new ArrayList<>();

            for (TAuPermissionObject tAuPermissionObject: tAuPermissionObjectList) {
                PermissionObjectResponseDto permissionObjectResponseDto=new PermissionObjectResponseDto();
                BeanUtils.copyProperties(permissionObjectResponseDto,tAuPermissionObject);
                permissionObjectResponseDtos.add(permissionObjectResponseDto);
            }
            Integer sum = tAuPermissionObjectMapper.countByExample(example);
            permissionObjectResponse.setColumnList(viewColumnList);
            permissionObjectResponse.setPageInfo(new PageInfo<>(permissionObjectResponseDtos));
            permissionObjectResponse.setSum(sum);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询权限项列表成功 ");
        return RestResponse.resultSuccess(permissionObjectResponse,restResponseHeader);
    }

    /**
     * 根据权限对象id 查关联权限项列表-------todo 具体出参内容格式，和前端对接再具体化
     * @param permissionObjectId
     * @return
     */
    @Override
    public RestResponse getPermissionItemListByPOId(Integer permissionObjectId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        //集合内容，权限项分组可以把名称也加进来
        List<TAuPermissionItem> permissionItemList = new ArrayList<>();
        TAuPermissionObjectItemExample example = new TAuPermissionObjectItemExample();
        TAuPermissionObjectItemExample.Criteria criteria = example.createCriteria();
        criteria.andPermissionObjectIdEqualTo(permissionObjectId);
        List<TAuPermissionObjectItem> tAuPermissionObjectItemList = tAuPermissionObjectItemMapper.selectByExample(example);
        for (TAuPermissionObjectItem tAuPermissionObjectItem:tAuPermissionObjectItemList) {
            TAuPermissionItem tAuPermissionItem = tAuPermissionItemMapper.selectByPrimaryKey(tAuPermissionObjectItem.getPermissionItemId());
            permissionItemList.add(tAuPermissionItem);
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("根据权限对象id 查关联权限项列表成功");
        return RestResponse.resultSuccess(permissionItemList,restResponseHeader);
    }

    /**
     * 批量逻辑删除权限对象，以及与权限项的关系数据，（如果绑定了业务对象，调接口解除与业务对象关系，以及删除授权中的数据）
     * @param ids
     * @return
     */
    @Transactional
    @Override
    public RestResponse deletePOAndRelatedPI(String ids) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] poIds = ids.split(",");
        Integer record = 0;
        //逻辑删权限对象
        for (int i = 0; i <poIds.length ; i++) {
            TAuPermissionObject tAuPermissionObject = tAuPermissionObjectMapper.selectByPrimaryKey(Integer.valueOf(poIds[i]));
            if(ConstantUtil.YES_OR_NO_Y.equals(tAuPermissionObject.getIsDelete())){
                throw new BusinessException("3011111193",getMessage("3011111193"));
            }
            //删除权限对象
            tAuPermissionObject.setIsDelete(ConstantUtil.YES_OR_NO_Y);
            tAuPermissionObject.setUpdateTime(new Date());
            tAuPermissionObject.setUpdateUser(loginUserId);
            tAuPermissionObjectMapper.updateByPrimaryKeySelective(tAuPermissionObject);
            record++;
            //查关系表数据--真删与权限项关系数据（打印日志记录）
            TAuPermissionObjectItemExample example = new TAuPermissionObjectItemExample();
            TAuPermissionObjectItemExample.Criteria criteria = example.createCriteria();
            criteria.andPermissionObjectIdEqualTo(Integer.valueOf(poIds[i]));
            Integer count  = tAuPermissionObjectItemMapper.deleteByExample(example);
            log.info("删除权限项，同步删除权限对象和权限项得绑定数据数量："+count);
            //todo 删除授权中含有权限对象得数据(业务对象数据)
            TAuRolePermissionObjectExample example1 = new TAuRolePermissionObjectExample();
            TAuRolePermissionObjectExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andPermissionObjectIdEqualTo(Integer.valueOf(poIds[i]));
            Integer count1 =  rolePermissionObjectMapper.deleteByExample(example1);
            log.info("删除权限项，同步删除有关权限对象的业务对象授权数据数量："+count1);
            //todo 子系统授权数据（可删可不删，不影响结果，因为在子系统授权得时候，同步授权业务对象，bo接口查该子系统下的业务对象，返回必须绑定权限对象的业务对象，已经解绑的就不会返回，也就不会授权）
            // 如果要删除，需要判断多种那个情况，根据权限对象id查绑定得业务对象得所属子系统，看这个子系统下除了刚才得业务对象还有没有其他绑定权限对象的业务对象，没有，我这边删除子系统授权数据，有，看权限项类型。。。

            //todo 调BO接口解除与业务对象关系(传参权限对象id) | 还要把该业务对象下的已绑定权限项的操作项也要解除关系（bo那边逻辑，脏数据的问题，不删也没影响对au）
            try {
                Result result = boClient.delAuRelation(Integer.valueOf(poIds[i]));
            }catch (Exception e){
                log.info("删除权限对象接口=======>调bo接口解除权限对象与业务对象得绑定======>失败");
                throw new BusinessException("4011111113",getMessage("4011111113"));
            }

        }
        if(record>0){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("删除权限对象成功");
        }else {
            restResponseHeader.setCode(ConstantUtil.ERROR);
            restResponseHeader.setMessage("没有需要删除的权限对象");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 新增权限对象和权限项关联表（初始化数据）
     * @param itemId
     * @param objectId
     */
    private void insertPermissionObjectItem(Integer itemId,Integer objectId){
        TAuPermissionObjectItem tAuPermissionObjectItem = new TAuPermissionObjectItem();
        tAuPermissionObjectItem.setPermissionItemId(itemId);
        tAuPermissionObjectItem.setPermissionObjectId(objectId);
        tAuPermissionObjectItem.setCreateTime(new Date());
        tAuPermissionObjectItem.setCreateUser(1);
        tAuPermissionObjectItem.setUpdateTime(new Date());
        tAuPermissionObjectItem.setUpdateUser(1);
        tAuPermissionObjectItemMapper.insertSelective(tAuPermissionObjectItem);
    }

    /**
     * 新增判重
     * @param permissionObjectCode
     */
    private void checkIsExist(String permissionObjectCode){
        TAuPermissionObjectExample example = new TAuPermissionObjectExample();
        TAuPermissionObjectExample.Criteria criteria = example.createCriteria();
        //已逻辑删除的可以重复
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria.andPermissionObjectCodeEqualTo(permissionObjectCode);
        List<TAuPermissionObject> permissionObjectList = tAuPermissionObjectMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(permissionObjectList)){
            throw new BusinessException("3011111192",getMessage("3011111192"));
        }
    }
}
