package com.vosung.authapp.resource.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.vosung.auapi.client.dto.requestdto.menu.MenuRequest;
import com.vosung.auapi.client.dto.requestdto.menu.MenuRequestDto;
import com.vosung.auapi.client.dto.requestdto.menu.UpdateMenuStatusDto;
import com.vosung.auapi.client.dto.responsedto.common.ViewColumn;
import com.vosung.auapi.client.dto.responsedto.menu.MenuResponse;
import com.vosung.auapi.client.dto.responsedto.menu.MenuResponseDto;
import com.vosung.auapi.client.dto.responsedto.menu.MenuTree;
import com.vosung.auapi.client.dto.responsedto.roleresource.ButtonResourceResponseDto;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestRequest;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.*;
import com.vosung.authapp.common.enums.SysDictEnum;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.resource.mapper.TAuButtonMapper;
import com.vosung.authapp.resource.mapper.TAuMenuMapper;
import com.vosung.authapp.resource.service.ButtonService;
import com.vosung.authapp.resource.service.MenuService;
import com.vosung.authapp.role.mapper.TAuRoleResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 菜单维护页面
 * @Author 彬
 * @Date 2019/5/16
 */
@Slf4j
@Service
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {

    @Autowired
    private TAuMenuMapper tAuMenuMapper;
    @Autowired
    private TAuRoleResourceMapper tAuRoleResourceMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserHolder userHolder;
    @Autowired
    private TAuButtonMapper tAuButtonMapper;
    @Autowired
    private ButtonService buttonService;
    /**
     * 保存菜单（新增/修改）---菜单名和code 判重，都不可重复
     * @param menuRequestDto
     * @return
     */
    @Override
    public RestResponse saveMenu(MenuRequestDto menuRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        TAuMenu menu = new TAuMenu();
        Integer record = 0;
        try {
            BeanUtils.copyProperties(menu,menuRequestDto);
            if(menuRequestDto.getId()==null){
                //新增 判重
                checkIsExist(menuRequestDto.getMenuCode(),menuRequestDto.getMenuName());
                if(menuRequestDto.getParentId()==null){
                    menu.setParentId(0);
                }
                menu.setDataState(DataStateCode.CREATED);
                menu.setCreateUser(loginUserId);
                menu.setUpdateUser(loginUserId);
                menu.setCreateTime(new Date());
                menu.setUpdateTime(new Date());
                record = tAuMenuMapper.insertSelective(menu);
            }else {
                //修改 判重
                //id有值，根据主键修改---已审核不可修改（前端控制）
                TAuMenu tAuMenu = tAuMenuMapper.selectByPrimaryKey(menuRequestDto.getId());
                if(tAuMenu == null){
                    throw new BusinessException("3011111314",getMessage("3011111314"));
                }
                boolean a = !tAuMenu.getMenuCode().equalsIgnoreCase(menuRequestDto.getMenuCode());
                boolean b = !tAuMenu.getMenuName().equalsIgnoreCase(menuRequestDto.getMenuName());
                if(a || b){
                    //修改code 或 name 判重(通过就修改),没有修改不判
                    checkUpdateIsExist(menuRequestDto.getMenuCode(),menuRequestDto.getMenuName(),a,b);
                }
                menu.setUpdateUser(loginUserId);
                menu.setUpdateTime(new Date());
                record = tAuMenuMapper.updateByPrimaryKeySelective(menu);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if(record == 1){
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponseHeader.setMessage("菜单保存成功 ");
        }
        return RestResponse.resultSuccess(record,restResponseHeader);
    }

    /**
     * 查看菜单详情
     * @param id
     * @return
     */
    @Override
    public RestResponse getMenuInfoById(Integer id) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        MenuResponseDto menuResponseDto = new MenuResponseDto();
        TAuMenu menu = tAuMenuMapper.selectByPrimaryKey(id);
        if(menu == null){
            throw new BusinessException("3011111314",getMessage("3011111314"));
        }
        try {
            BeanUtils.copyProperties(menuResponseDto,menu);
            if(menu.getParentId()==0){
                menuResponseDto.setParentName("无");
            }else {
                TAuMenu menu1 = tAuMenuMapper.selectByPrimaryKey(menu.getParentId());
                if(menu1 == null){
                    throw new BusinessException("3011111314",getMessage("3011111314"));
                }
                menuResponseDto.setParentName(menu1.getMenuName());
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        restResponseHeader.setMessage("查看菜单详请成功");
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        return RestResponse.resultSuccess(menuResponseDto,restResponseHeader);
    }
    /**
     * 提交菜单
     * @param body
     * @return
     */
    @Override
    public RestResponse commitMenu(UpdateMenuStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] menuIdArray = body.getMenuIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < menuIdArray.length; i++) {
            TAuMenu tAuMenu = tAuMenuMapper.selectByPrimaryKey(Integer.valueOf(menuIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuMenu.getDataState();
            Boolean a = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) ||
                    DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(tAuMenu.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && a){
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuMenu.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !a){
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；禁用的数据不允许提交");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map = new HashMap();
                    map.put("message","菜单名为:"+tAuMenu.getMenuName()+" 的菜单；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","菜单名为："+tAuMenu.getMenuName()+" 的菜单；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(a){
                //符合要求，修改状态为审核中
                tAuMenu.setDataState(DataStateCode.AUDIT_IN_PROGRESS);
                tAuMenu.setUpdateUser(loginUserId);
                tAuMenu.setUpdateTime(new Date());
                tAuMenuMapper.updateByPrimaryKeySelective(tAuMenu);
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；提交成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","菜单名为:"+tAuMenu.getMenuName()+" 的菜单；正在审核中无需重复提交");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","菜单名为："+tAuMenu.getMenuName()+" 的菜单；已审核的不允许提交");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 撤销菜单
     * @param body
     * @return
     */
    @Override
    public RestResponse abolishMenu(UpdateMenuStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] menuIdArray = body.getMenuIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < menuIdArray.length; i++) {
            TAuMenu tAuMenu = tAuMenuMapper.selectByPrimaryKey(Integer.valueOf(menuIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuMenu.getDataState();
            Boolean b = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuMenu.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y)&&b){
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuMenu.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y)&&!b){
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；禁用的数据不允许撤销");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","菜单名为："+tAuMenu.getMenuName()+" 的菜单；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","菜单名为："+tAuMenu.getMenuName()+" 的菜单；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(b){
                //符合要求，修改状态为重新审核
                tAuMenu.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuMenu.setUpdateUser(loginUserId);
                tAuMenu.setUpdateTime(new Date());
                tAuMenuMapper.updateByPrimaryKeySelective(tAuMenu);
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；撤销成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","菜单名为："+tAuMenu.getMenuName()+" 的菜单；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","菜单名为："+tAuMenu.getMenuName()+" 的菜单；已审核完毕，不允许撤销");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 审核菜单
     * @param body
     * @return
     */
    @Override
    public RestResponse auditMenu(UpdateMenuStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] menuIdArray = body.getMenuIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < menuIdArray.length; i++) {
            TAuMenu tAuMenu = tAuMenuMapper.selectByPrimaryKey(Integer.valueOf(menuIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuMenu.getDataState();
            Boolean c = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState);
            if(tAuMenu.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && c){
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuMenu.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !c){
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；禁用的数据不允许审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","菜单名为："+tAuMenu.getMenuName()+" 的菜单；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(c){
                //符合要求，修改状态为已审核
                tAuMenu.setDataState(DataStateCode.AUDITED);
                tAuMenu.setReviewUser(loginUserId);
                tAuMenu.setReviewTime(new Date());
                tAuMenu.setUpdateUser(loginUserId);
                tAuMenu.setUpdateTime(new Date());
                tAuMenuMapper.updateByPrimaryKeySelective(tAuMenu);
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }else if(DataStateCode.AUDITED.equals(dataState)){
                    map.put("message","菜单名为："+tAuMenu.getMenuName()+" 的菜单；数据已审核完毕，无需再审");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 反审核菜单
     * @param body
     * @return
     */
    @Override
    public RestResponse reverseAuditMenu(UpdateMenuStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] menuIdArray = body.getMenuIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < menuIdArray.length; i++) {
            TAuMenu tAuMenu = tAuMenuMapper.selectByPrimaryKey(Integer.valueOf(menuIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuMenu.getDataState();
            Boolean d = DataStateCode.AUDIT_IN_PROGRESS.equals(dataState) || DataStateCode.AUDITED.equals(dataState);
            if(tAuMenu.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) &&d){
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                continue;
            }
            if(tAuMenu.getProhibitState().equals(ConstantUtil.YES_OR_NO_Y) && !d){
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；禁用的数据不允许反审核");
                map.put("code",ConstantUtil.ERROR);
                messageList.add(map);
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map = new HashMap();
                    map.put("message","菜单名为："+tAuMenu.getMenuName()+" 的菜单；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                    messageList.add(map);
                }
                continue;
            }
            if(d){
                //符合要求，修改状态为重新审核
                tAuMenu.setDataState(DataStateCode.REVIEW_AGAIN);
                tAuMenu.setUpdateUser(loginUserId);
                tAuMenu.setUpdateTime(new Date());
                tAuMenuMapper.updateByPrimaryKeySelective(tAuMenu);
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；反审核成功");
                map.put("code",ConstantUtil.SUCCESS);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.CREATED.equals(dataState)){
                    map.put("message","菜单名为："+tAuMenu.getMenuName()+" 的菜单；请先提交审核");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 删除菜单-------同步删除角色资源（关联对应菜单得删除）
     * @param body
     * @return
     */
    @Transactional
    @Override
    public RestResponse deleteMenu(UpdateMenuStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] menuIdArray = body.getMenuIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < menuIdArray.length; i++) {
            TAuMenu tAuMenu = tAuMenuMapper.selectByPrimaryKey(Integer.valueOf(menuIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            String dataState = tAuMenu.getDataState();
            Boolean e = DataStateCode.CREATED.equals(dataState) || DataStateCode.TEMPORARY_STORAGE.equals(dataState) || DataStateCode.REVIEW_AGAIN.equals(dataState);
            if(e){
                //符合要求，修改删除标识为"Y"
                tAuMenu.setIsDelete(ConstantUtil.YES_OR_NO_Y);
                tAuMenu.setUpdateUser(loginUserId);
                tAuMenu.setUpdateTime(new Date());
                tAuMenuMapper.updateByPrimaryKeySelective(tAuMenu);
                map.put("message","菜单名为 ："+tAuMenu.getMenuName()+" 的菜单；删除成功");
                map.put("code",ConstantUtil.SUCCESS);
                //查角色关联此菜单资源list，同步删除
                TAuRoleResourceExample example = new TAuRoleResourceExample();
                TAuRoleResourceExample.Criteria criteria=example.createCriteria();
                criteria.andResourceItemIdEqualTo(Integer.valueOf(menuIdArray[i]));
                criteria.andResourceTypeEqualTo(ConstantUtil.MENU);
                Integer count = tAuRoleResourceMapper.deleteByExample(example);
                log.info("同步删除该菜单资源关联角色得所有数据；删除数："+count);
            }else {
                if(DataStateCode.REVIEW_AGAIN.equals(dataState) || DataStateCode.AUDITED.equals(dataState) || DataStateCode.AUDIT_IN_PROGRESS.equals(dataState)){
                    map.put("message","菜单名为："+tAuMenu.getMenuName()+" 的菜单；删除失败，只有创建和暂存状态的菜单才可删除");
                    map.put("code",ConstantUtil.ERROR);
                }
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 禁用菜单-----同步禁用角色资源项
     * @param body
     * @return
     */
    @Override
    public RestResponse forbiddenMenu(UpdateMenuStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] menuIdArray = body.getMenuIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < menuIdArray.length; i++) {
            TAuMenu tAuMenu = tAuMenuMapper.selectByPrimaryKey(Integer.valueOf(menuIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            if (ConstantUtil.YES_OR_NO_N.equals(tAuMenu.getProhibitState())) {
                tAuMenu.setProhibitState(ConstantUtil.YES_OR_NO_Y);
                tAuMenu.setProhibitUser(loginUserId);
                tAuMenu.setProhibitTime(new Date());
                tAuMenu.setUpdateTime(new Date());
                tAuMenu.setUpdateUser(loginUserId);
                tAuMenuMapper.updateByPrimaryKeySelective(tAuMenu);
                map.put("message", "菜单名为：" + tAuMenu.getMenuName()+ " 的菜单禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
                TAuRoleResourceExample example = new TAuRoleResourceExample();
                TAuRoleResourceExample.Criteria criteria=example.createCriteria();
                criteria.andResourceItemIdEqualTo(Integer.valueOf(menuIdArray[i]));
                criteria.andResourceTypeEqualTo(ConstantUtil.MENU);
                List<TAuRoleResource> roleResourceList = tAuRoleResourceMapper.selectByExample(example);
                for (TAuRoleResource tAuRoleResource: roleResourceList) {
                    //设置状态--禁用
                    tAuRoleResource.setState("0");
                    tAuRoleResourceMapper.updateByPrimaryKeySelective(tAuRoleResource);
                }
            } else {
                map.put("message", "菜单名为：" + tAuMenu.getMenuName()+ " 的菜单已被禁用");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }
    /**
     * 反禁菜单
     * @param body
     * @return
     */
    @Override
    public RestResponse unForbiddenMenu(UpdateMenuStatusDto body) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        String[] menuIdArray = body.getMenuIds().split(",");
        //消息集合
        List<Map> messageList = new ArrayList<>();
        for (int i = 0; i < menuIdArray.length; i++) {
            TAuMenu tAuMenu = tAuMenuMapper.selectByPrimaryKey(Integer.valueOf(menuIdArray[i]));
            //将每个修改的结果信息，放在每个map中
            Map map = new HashMap();
            if (ConstantUtil.YES_OR_NO_Y.equals(tAuMenu.getProhibitState())) {
                tAuMenu.setProhibitState(ConstantUtil.YES_OR_NO_N);
                tAuMenu.setProhibitUser(loginUserId);
                tAuMenu.setProhibitTime(new Date());
                tAuMenu.setUpdateTime(new Date());
                tAuMenu.setUpdateUser(loginUserId);
                tAuMenuMapper.updateByPrimaryKeySelective(tAuMenu);
                map.put("message", "菜单名为：" + tAuMenu.getMenuName()+ " 的菜单反禁用成功");
                map.put("code", ConstantUtil.SUCCESS);
                TAuRoleResourceExample example = new TAuRoleResourceExample();
                TAuRoleResourceExample.Criteria criteria=example.createCriteria();
                criteria.andResourceItemIdEqualTo(Integer.valueOf(menuIdArray[i]));
                criteria.andResourceTypeEqualTo(ConstantUtil.MENU);
                List<TAuRoleResource> roleResourceList = tAuRoleResourceMapper.selectByExample(example);
                for (TAuRoleResource tAuRoleResource: roleResourceList) {
                    //设置状态--启用
                    tAuRoleResource.setState("1");
                    tAuRoleResourceMapper.updateByPrimaryKeySelective(tAuRoleResource);
                }
            } else {
                map.put("message", "菜单名为：" + tAuMenu.getMenuName()+ " 的菜单已是反禁用状态");
                map.put("code", ConstantUtil.ERROR);
            }
            messageList.add(map);
        }
        return RestResponse.resultSuccess(messageList,restResponseHeader);
    }

    /**
     * 展示所有可用菜单--树
     * @return
     */
    @Override
    public RestResponse getAllMenuTree() {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        //查所有菜单(未删除，已审核,未禁用)
        TAuMenuExample example = new TAuMenuExample();
        TAuMenuExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria.andDataStateEqualTo(DataStateCode.AUDITED);
        criteria.andProhibitStateEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuMenu> menuList = tAuMenuMapper.selectByExample(example);
        List<MenuTree> menuTrees = new ArrayList<>();
        for (TAuMenu tAuMenu: menuList) {
            MenuTree menuTree = new MenuTree();
            try {
                BeanUtils.copyProperties(menuTree,tAuMenu);
                //查菜单下得可用按钮list
                RestResponse restResponse = buttonService.getButtonListByMenuId(tAuMenu.getId());
                List<ButtonResourceResponseDto> resourceDtoList = (List<ButtonResourceResponseDto>)restResponse.getBody();
                menuTree.setButtonList(resourceDtoList);
                menuTrees.add(menuTree);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        MenuTreeUtils menuTreeUtils = new MenuTreeUtils();
//        menuTrees = MenuTreeUtil.generateMenuTree(menuTrees);
        List<Object> list  = menuTreeUtils.treeMenu(menuTrees);
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("菜单树形结构展示成功");
        return RestResponse.resultSuccess(list,restResponseHeader);
    }

    /**
     * 菜单列表
     * @param restRequest
     * @return
     */
    @Override
    public RestResponse getMenuList(RestRequest<MenuRequest> restRequest) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        MenuResponse menuResponse = new MenuResponse();
        try{
            List<ViewColumn> viewColumnList = new ArrayList<>();
            viewColumnList = FieldMessageCls.getViewColumn(MenuResponseDto.class.getName());
            Integer pageNum = restRequest.getHeader().getPageNum();
            Integer pageSize = restRequest.getHeader().getPageSize();
            PageMethod.startPage(pageNum,pageSize,"id");
            TAuMenuExample example = new TAuMenuExample();
            TAuMenuExample.Criteria criteria = example.createCriteria();
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            List<TAuMenu> menuList =  tAuMenuMapper.selectByExample(example);
            List<MenuResponseDto> menuResponseDtoList = new ArrayList<>();
            for (TAuMenu tAuMenu: menuList) {
                MenuResponseDto menuResponseDto = new MenuResponseDto();
                BeanUtils.copyProperties(menuResponseDto,tAuMenu);
                menuResponseDto.setDataStateName((String) redisUtil.hget(SysDictEnum.DATA_STATE.getCode(),menuResponseDto.getDataState()));
                menuResponseDtoList.add(menuResponseDto);
            }
            Integer sum = tAuMenuMapper.countByExample(example);
            menuResponse.setColumnList(viewColumnList);
            menuResponse.setPageInfo(new PageInfo<>(menuResponseDtoList));
            menuResponse.setSum(sum);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("查询菜单列表成功 ");
        return RestResponse.resultSuccess(menuResponse,restResponseHeader);
    }

    /**
     * 检查是否存在
     * @param code
     * @param name
     */
    private void checkIsExist(String code,String name){
        TAuMenuExample example = new TAuMenuExample();
        TAuMenuExample.Criteria criteria = example.createCriteria();
        criteria.andMenuCodeEqualTo(code);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        TAuMenuExample.Criteria criteria1 = example.createCriteria();
        criteria1.andMenuNameEqualTo(name);
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        example.or(criteria1);
        List<TAuMenu> menuList = tAuMenuMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(menuList)){
            if(code.equalsIgnoreCase(menuList.get(0).getMenuCode())){
                throw new BusinessException("3011111315",getMessage("3011111315"));
            }
            if(name.equalsIgnoreCase(menuList.get(0).getMenuName())){
                throw new BusinessException("3011111316",getMessage("3011111316"));
            }
        }
    }

    /**
     * 修改判重
     * @param code
     * @param name
     * @param a
     * @param b
     */
    private void checkUpdateIsExist(String code,String name,boolean a,boolean b){
        if(a){
            //code 修改，就判重
            TAuMenuExample example = new TAuMenuExample();
            TAuMenuExample.Criteria criteria = example.createCriteria();
            criteria.andMenuCodeEqualTo(code);
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            List<TAuMenu> menuList = tAuMenuMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(menuList)){
                throw new BusinessException("3011111315",getMessage("3011111315"));
            }
        }
        if(b){
            //name 修改，就判重
            TAuMenuExample example = new TAuMenuExample();
            TAuMenuExample.Criteria criteria = example.createCriteria();
            criteria.andMenuNameEqualTo(name);
            criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
            List<TAuMenu> menuList = tAuMenuMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(menuList)){
                throw new BusinessException("3011111316",getMessage("3011111316"));
            }
        }
    }
}
