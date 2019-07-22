package com.vosung.authapp.role.service.impl;

import com.alibaba.fastjson.JSON;
import com.vosung.auapi.client.dto.requestdto.roleresource.ButtonResourceRequestDto;
import com.vosung.auapi.client.dto.requestdto.roleresource.MenuButton;
import com.vosung.auapi.client.dto.requestdto.roleresource.RoleResourceRequestDto;
import com.vosung.auapi.client.dto.responsedto.menu.MenuTree;
import com.vosung.auapi.client.dto.responsedto.orguserrole.CountDto;
import com.vosung.auapi.client.dto.responsedto.roleresource.ButtonResourceResponseDto;
import com.vosung.auapi.client.entity.*;
import com.vosung.auapi.client.restparam.RestResponse;
import com.vosung.auapi.client.restparam.RestResponseHeader;
import com.vosung.authapp.common.basecore.BaseServiceImpl;
import com.vosung.authapp.common.constant.*;
import com.vosung.authapp.common.exception.BusinessException;
import com.vosung.authapp.resource.mapper.TAuButtonMapper;
import com.vosung.authapp.resource.mapper.TAuMenuMapper;
import com.vosung.authapp.resource.service.ButtonService;
import com.vosung.authapp.role.mapper.TAuRoleResourceMapper;
import com.vosung.authapp.role.service.RoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色资源授权实现类
 * @Author 彬
 * @Date 2019/5/16
 */
@Slf4j
@Service
public class RoleResourceServiceImpl extends BaseServiceImpl implements RoleResourceService {

    @Autowired
    private TAuRoleResourceMapper tAuRoleResourceMapper;
    @Autowired
    private TAuMenuMapper tAuMenuMapper;
    @Autowired
    private TAuButtonMapper tAuButtonMapper;
    @Autowired
    private ButtonService buttonService;
    @Autowired
    private UserHolder userHolder;
    /**
     * 角色批量授权
     * @param roleResourceRequestDto
     * @return
     */
    @Transactional
    @Override
    public RestResponse saveRoleResource(RoleResourceRequestDto roleResourceRequestDto) {
        Integer loginUserId = Integer.valueOf(userHolder.getUserId());
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        CountDto countDto = new CountDto();
        //根据角色id查，之前有没有数据
        TAuRoleResourceExample example = new TAuRoleResourceExample();
        TAuRoleResourceExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleResourceRequestDto.getRoleId());
        List<TAuRoleResource> roleResourceList = tAuRoleResourceMapper.selectByExample(example);
        Integer record=0;
        Integer record2 = 0;
        Integer deleteCount=0;
        if(CollectionUtils.isEmpty(roleResourceList)){
            //之前没有数据
            if(!CollectionUtils.isEmpty(roleResourceRequestDto.getMenuButtonList())){
                //如果传参菜单和按钮对应list不为空，即新增数据
                for (MenuButton menuButton:roleResourceRequestDto.getMenuButtonList()) {
                    TAuRoleResource tAuRoleResource = new TAuRoleResource();
                    tAuRoleResource.setResourceItemId(menuButton.getMenuId());
                    tAuRoleResource.setState("1");
                    tAuRoleResource.setResourceType(ConstantUtil.MENU);
                    tAuRoleResource.setRoleId(roleResourceRequestDto.getRoleId());
                    tAuRoleResource.setCreateTime(new Date());
                    tAuRoleResource.setCreateUser(loginUserId);
                    tAuRoleResource.setUpdateTime(new Date());
                    tAuRoleResource.setUpdateUser(loginUserId);
                    tAuRoleResourceMapper.insertSelective(tAuRoleResource);
                    record2++;
                    for (Integer buttonId:menuButton.getButtonIds()) {
                        tAuRoleResource = new TAuRoleResource();
                        tAuRoleResource.setResourceItemId(buttonId);
                        tAuRoleResource.setState("1");
                        tAuRoleResource.setResourceType(ConstantUtil.BUTTON);
                        tAuRoleResource.setRoleId(roleResourceRequestDto.getRoleId());
                        tAuRoleResource.setCreateTime(new Date());
                        tAuRoleResource.setCreateUser(loginUserId);
                        tAuRoleResource.setUpdateTime(new Date());
                        tAuRoleResource.setUpdateUser(loginUserId);
                        tAuRoleResourceMapper.insertSelective(tAuRoleResource);
                        record++;
                    }
                }
                log.info("角色资源授权关联菜单数量："+record+" 关联按钮数量："+record2);
            }
            restResponseHeader.setMessage("角色资源授权成功---新增操作");
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
        }else {
            //之前有数据
            if(CollectionUtils.isEmpty(roleResourceRequestDto.getMenuButtonList()) && roleResourceRequestDto.getRoleId()!=null){
                //传参为空，即删除所有之前数据
                deleteCount = tAuRoleResourceMapper.deleteByExample(example);
                log.info("传参菜单按钮对应关系全为空，删除角色id为："+roleResourceRequestDto.getRoleId()+" 关联资源数："+deleteCount);
                restResponseHeader.setMessage("保存组织用户维护关系成功---删除操作");
                restResponseHeader.setCode(ConstantUtil.SUCCESS);
            }else if(!CollectionUtils.isEmpty(roleResourceRequestDto.getMenuButtonList()) && roleResourceRequestDto.getRoleId()!=null){
                List<Integer> menuIds = new ArrayList<>();
                Map<Integer,List<Integer>> map = new HashMap();
                for (MenuButton menuButton: roleResourceRequestDto.getMenuButtonList()) {
                    if(menuButton.getMenuId()==null){
                        throw new BusinessException("3011111317","3011111317");
                    }
                    if(menuButton.getButtonIds()==null){
                        throw new BusinessException("3011111318","3011111318");
                    }
                    if(!menuIds.contains(menuButton.getMenuId())){
                        menuIds.add(menuButton.getMenuId());
                        map.put(menuButton.getMenuId(),menuButton.getButtonIds());
                    }
                }
                log.info("传参中菜单按钮的对应关系："+ JSON.toJSONString(map));
                //传参不为空,查关联菜单list变化(查之前关联得菜单list)
                List<TAuMenu> menuList = getMenuListByRoleId(roleResourceRequestDto.getRoleId());
                List<Integer> menuIdList = new ArrayList<>();
                for (TAuMenu tAuMenu: menuList) {
                    menuIdList.add(tAuMenu.getId());
                }
                //先看传参中菜单对应原有菜单
                for (TAuMenu tAuMenu: menuList) {
                    if(menuIds.contains(tAuMenu.getId())){
                        //传参菜单id，之前数据也有，再看按钮变化
                        List<TAuButton> buttonList = getButtonListByRoleId(roleResourceRequestDto.getRoleId().toString(),tAuMenu.getId());
                        List<Integer> buttonIdList = new ArrayList<>();
                        for (TAuButton tAuButton: buttonList) {
                            buttonIdList.add(tAuButton.getId());
                        }
                        //开始按钮比较
                        for (TAuButton tAuButton: buttonList) {
                            if(!(map.get(tAuMenu.getId())).contains(tAuButton.getId())){
                                //传参菜单下按钮id，之前有，不操作
                                //之前有，但是传参没有。
                                TAuRoleResourceExample example1 = new TAuRoleResourceExample();
                                TAuRoleResourceExample.Criteria criteria1 = example1.createCriteria();
                                criteria1.andRoleIdEqualTo(roleResourceRequestDto.getRoleId());
                                criteria1.andResourceTypeEqualTo(ConstantUtil.BUTTON);
                                criteria1.andResourceItemIdEqualTo(tAuButton.getId());
                                tAuRoleResourceMapper.deleteByExample(example1);
                                deleteCount++;
                                log.info("删除角色id为："+roleResourceRequestDto.getRoleId()+" 菜单id为："+tAuMenu.getId()+"按钮id为："+tAuButton.getId()+" 的角色授权关联数量："+deleteCount);
                            }
                        }
                        for (Integer buttonId:map.get(tAuMenu.getId())) {
                            if(!buttonIdList.contains(buttonId)){
                                //传参多与原有，新增
                                TAuRoleResource tAuRoleResource = new TAuRoleResource();
                                tAuRoleResource.setResourceItemId(buttonId);
                                tAuRoleResource.setState("1");
                                tAuRoleResource.setResourceType(ConstantUtil.BUTTON);
                                tAuRoleResource.setRoleId(roleResourceRequestDto.getRoleId());
                                tAuRoleResource.setCreateTime(new Date());
                                tAuRoleResource.setCreateUser(loginUserId);
                                tAuRoleResource.setUpdateTime(new Date());
                                tAuRoleResource.setUpdateUser(loginUserId);
                                tAuRoleResourceMapper.insertSelective(tAuRoleResource);
                                record++;
                                log.info("============角色授权按钮新增操作，对应授权按钮变化=============");
                            }

                        }
                    }else {
                        //之前菜单id，传参没有，删除数据---菜单对应得按钮数据也删除（与组织用户维护不同之处，那边值有表中关联关系，可以一起删，这里只是删自己）
                        List<TAuButton> buttonList = getButtonListByRoleId(roleResourceRequestDto.getRoleId().toString(),tAuMenu.getId());
                        for (TAuButton tAuButton: buttonList) {
                            TAuRoleResourceExample example1 = new TAuRoleResourceExample();
                            TAuRoleResourceExample.Criteria criteria1 = example1.createCriteria();
                            criteria1.andRoleIdEqualTo(roleResourceRequestDto.getRoleId());
                            criteria1.andResourceItemIdEqualTo(tAuButton.getId());
                            criteria1.andResourceTypeEqualTo(ConstantUtil.BUTTON);
                            tAuRoleResourceMapper.deleteByExample(example1);
                            deleteCount++;
                        }
                        TAuRoleResourceExample example1 = new TAuRoleResourceExample();
                        TAuRoleResourceExample.Criteria criteria1 = example1.createCriteria();
                        criteria1.andRoleIdEqualTo(roleResourceRequestDto.getRoleId());
                        criteria1.andResourceItemIdEqualTo(tAuMenu.getId());
                        criteria1.andResourceTypeEqualTo(ConstantUtil.MENU);
                        tAuRoleResourceMapper.deleteByExample(example1);
                        deleteCount++;
                        log.info("删除角色id为："+roleResourceRequestDto.getRoleId()+" 菜单id为："+tAuMenu.getId()+" 息以及对应按钮的授权信的数量："+deleteCount);
                    }
                }
                //再看原有用户的对应传参用户
                for (Integer menuId: menuIds) {
                    if (!menuIdList.contains(menuId)){
                        //传参在原有中不存在，新增数据
                        TAuRoleResource tAuRoleResource = new TAuRoleResource();
                        tAuRoleResource.setResourceItemId(menuId);
                        tAuRoleResource.setState("1");
                        tAuRoleResource.setResourceType(ConstantUtil.MENU);
                        tAuRoleResource.setRoleId(roleResourceRequestDto.getRoleId());
                        tAuRoleResource.setCreateTime(new Date());
                        tAuRoleResource.setCreateUser(loginUserId);
                        tAuRoleResource.setUpdateTime(new Date());
                        tAuRoleResource.setUpdateUser(loginUserId);
                        tAuRoleResourceMapper.insertSelective(tAuRoleResource);
                        record2++;
                        log.info("菜单id："+menuId+"之前不存在，新增角色菜单资源授权");
                        if(!CollectionUtils.isEmpty(map.get(menuId))){
                            //对应菜单得按钮为空---只是授权菜单
                            //对应用户角色list存在
                            for (Integer buttonId:map.get(menuId)) {
                                tAuRoleResource = new TAuRoleResource();
                                tAuRoleResource.setResourceItemId(buttonId);
                                tAuRoleResource.setState("1");
                                tAuRoleResource.setResourceType(ConstantUtil.BUTTON);
                                tAuRoleResource.setRoleId(roleResourceRequestDto.getRoleId());
                                tAuRoleResource.setCreateTime(new Date());
                                tAuRoleResource.setCreateUser(loginUserId);
                                tAuRoleResource.setUpdateTime(new Date());
                                tAuRoleResource.setUpdateUser(loginUserId);
                                tAuRoleResourceMapper.insertSelective(tAuRoleResource);
                                record++;
                            }
                            log.info("菜单id："+menuId+"之前不存在，并有对应按钮信息，新增角色按钮资源授权");
                        }
                    }
                }
                restResponseHeader.setMessage("保存组织用户维护关系成功---修改调整操作");
                restResponseHeader.setCode(ConstantUtil.SUCCESS);
            }
        }
        countDto.setAddCount(record+record2);
        countDto.setDeleteCount(deleteCount);
        return RestResponse.resultSuccess(countDto,restResponseHeader);
    }

    /**
     * 查询某角色下得有权菜单list--权限平台用
     * @param roleId
     * @return
     */
    @Override
    public RestResponse getMenuResourceListByRoleId(Integer roleId) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        //角色拥有menu
        List<TAuMenu> menuList = getMenuListByRoleId(roleId);
        List<MenuTree> menuTrees = new ArrayList<>();
        try {
            for (TAuMenu tAuMenu: menuList) {
                MenuTree menuTree = new MenuTree();
                BeanUtils.copyProperties(menuTree,tAuMenu);
                //查角色菜单下的按钮
                List<TAuButton> buttonList = getButtonListByRoleId(roleId.toString(),tAuMenu.getId());
                List<ButtonResourceResponseDto> list = new ArrayList<>();
                for (TAuButton tAuButton: buttonList) {
                    ButtonResourceResponseDto buttonResourceResponseDto = new ButtonResourceResponseDto();
                    BeanUtils.copyProperties(buttonResourceResponseDto,tAuButton);
                    list.add(buttonResourceResponseDto);
                }
                menuTree.setButtonList(list);
                menuTrees.add(menuTree);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("角色拥有菜单和按钮展示成功 ");
        return RestResponse.resultSuccess(menuTrees,restResponseHeader);
    }

    /**
     * 查多角色下的菜单树--KSF用
     * @param roleIds
     * @return
     */
    @Override
    public RestResponse getMenuTreeByRoleIds(String roleIds) {
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        //所有menu
        TAuMenuExample example = new TAuMenuExample();
        TAuMenuExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria.andDataStateEqualTo(DataStateCode.AUDITED);
        criteria.andProhibitStateEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuMenu> allMmenuList = tAuMenuMapper.selectByExample(example);
        //角色拥有menu
        List<TAuMenu> menuList = getMenuListByRoleIds(roleIds);
        List<MenuTree> allMenuTrees = new ArrayList<>();
        List<MenuTree> menuTrees = new ArrayList<>();
        try {
            for (TAuMenu tAuMenu: allMmenuList) {
                MenuTree menuTree = new MenuTree();
                BeanUtils.copyProperties(menuTree,tAuMenu);
                allMenuTrees.add(menuTree);
            }
            for (TAuMenu tAuMenu: menuList) {
                MenuTree menuTree = new MenuTree();
                BeanUtils.copyProperties(menuTree,tAuMenu);
                menuTrees.add(menuTree);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Object> list = new ArrayList<>();
        MenuTreeUtils menuTreeUtils = new MenuTreeUtils();
        if(Boolean.valueOf(userHolder.getIsSuperAdmin())){
            //如果是系统管理员，显示所有有效菜单
            list = menuTreeUtils.treeMenu(allMenuTrees);
        }else {
            //不是就只展示角色拥有的菜单
            list= menuTreeUtils.treeMenu(menuTrees);
        }
        restResponseHeader.setCode(ConstantUtil.SUCCESS);
        restResponseHeader.setMessage("（用户）多角色拥有菜单展示成功");
        return RestResponse.resultSuccess(list,restResponseHeader);
    }

    /**
     * 根据角色id 菜单id 查询按钮列表（无角色id：有效 有角色：有权会标识）-ksf
     * @return
     */
    @Override
    public RestResponse getButtonResourceListByRoleIdMenuId(ButtonResourceRequestDto resourceRequestDto) {
        RestResponse restResponse = new RestResponse();
        RestResponseHeader restResponseHeader = new RestResponseHeader();
        if(resourceRequestDto.getRoleIds() == null){
            //角色id传值为空，只是展示菜单下有效按钮
            restResponse = buttonService.getButtonListByMenuId(resourceRequestDto.getMenuId());
        }else {
            //菜单下按钮，角色拥有
            List<TAuButton> tAuButtonList = new ArrayList<>();
            if(resourceRequestDto.getMenuId()!=null && resourceRequestDto.getParentButtonId()==null){
                if(Boolean.valueOf(userHolder.getIsSuperAdmin())){
                    tAuButtonList =getButtonListByMenuId(resourceRequestDto.getMenuId());
                }else {
                    tAuButtonList = getButtonListByRoleId(resourceRequestDto.getRoleIds(),resourceRequestDto.getMenuId());
                }
            }else if(resourceRequestDto.getParentButtonId()!=null && resourceRequestDto.getMenuId()==null){
                if(Boolean.valueOf(userHolder.getIsSuperAdmin())){
                    tAuButtonList = getButtonListByParentId(resourceRequestDto.getParentButtonId());
                }else {
                    tAuButtonList = getButtonChildrenByParentId(resourceRequestDto.getRoleIds(),resourceRequestDto.getParentButtonId());
                }
            }else {
                throw new BusinessException(ConstantUtil.ERROR,"根据角色查询有权得按钮列表，传参有误，请保证菜单和父按钮id有且仅有其中一项");
            }
            restResponseHeader.setMessage("展示(菜单下|父按钮下)角色授权的按钮成功");
            restResponseHeader.setCode(ConstantUtil.SUCCESS);
            restResponse.setHeader(restResponseHeader);
            restResponse.setBody(tAuButtonList);
        }
        return restResponse;
    }


    /**
     * 查询某角色下得有权菜单list---权限平台
     * @param roleId
     * @return
     */
    private List<TAuMenu> getMenuListByRoleId(Integer roleId){
        TAuRoleResourceExample example = new TAuRoleResourceExample();
        TAuRoleResourceExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        criteria.andStateEqualTo("1");
        criteria.andResourceTypeEqualTo(ConstantUtil.MENU);
        List<TAuRoleResource> roleResourceList = tAuRoleResourceMapper.selectByExample(example);
        List<TAuMenu> menuList = new ArrayList<>();
        for (TAuRoleResource tAuRoleResource: roleResourceList) {
            Integer menuId = tAuRoleResource.getResourceItemId();
            TAuMenu tAuMenu = tAuMenuMapper.selectByPrimaryKey(menuId);
            menuList.add(tAuMenu);
        }
        return menuList;
    }

    /**
     * 查多个角色的菜单集合（去重）--ksf
     * @param roleIds
     * @return
     */
    private List<TAuMenu> getMenuListByRoleIds(String roleIds){
        String[] roleIdArray = roleIds.split(",");
        List<Integer> roleIdList = new ArrayList<>();
        for (int i = 0; i <roleIdArray.length ; i++) {
            roleIdList.add(Integer.valueOf(roleIdArray[i]));
        }
        List<TAuRoleResource> tAuRoleResourceList = tAuRoleResourceMapper.getMenuResourceByRoleIds(roleIdList);
        List<TAuMenu> menuList = new ArrayList<>();
        List<Integer> menuIds = new ArrayList<>();
        //组装多角色拥有菜单去重后结果。todo: 因为授权时候，有可见不可见---我们只返回可见得菜单
        for (TAuRoleResource tAuRoleResource: tAuRoleResourceList) {
            if(!menuIds.contains(tAuRoleResource.getResourceItemId())){
                Integer menuId = tAuRoleResource.getResourceItemId();
                menuIds.add(menuId);
                TAuMenu tAuMenu = tAuMenuMapper.selectByPrimaryKey(menuId);
                if(!ConstantUtil.UN_SEE.equalsIgnoreCase(tAuMenu.getProhibitState())){
                    menuList.add(tAuMenu);
                }
            }
        }
        return menuList;
    }

    /**
     * 查某菜单下多角色授权得按钮
     * @param roleIds
     * @return
     */
    private List<TAuButton> getButtonListByRoleId(String roleIds,Integer menuId){
        String[] roles = roleIds.split(",");
        List<Integer> buttonIdList = new ArrayList<>();
        for (int i = 0; i < roles.length; i++) {
            //查授权得按钮list
            TAuRoleResourceExample example = new TAuRoleResourceExample();
            TAuRoleResourceExample.Criteria criteria = example.createCriteria();
            criteria.andRoleIdEqualTo(Integer.valueOf(roles[i]));
            criteria.andStateEqualTo("1");
            criteria.andResourceTypeEqualTo(ConstantUtil.BUTTON);
            List<TAuRoleResource> roleResourceList = tAuRoleResourceMapper.selectByExample(example);
            List<Integer> buttonIdList1 = new ArrayList<>();
            for (TAuRoleResource tAuRoleResource: roleResourceList) {
                Integer buttonId = tAuRoleResource.getResourceItemId();
                buttonIdList1.add(buttonId);
            }
            buttonIdList.addAll(buttonIdList1);
        }

        //查菜单下得可用按钮list
        TAuButtonExample example1 =new TAuButtonExample();
        TAuButtonExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andMenuIdEqualTo(menuId);
        criteria1.andDataStateEqualTo(DataStateCode.AUDITED);
        criteria1.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria1.andProhibitStateEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuButton> tAuButtons =  tAuButtonMapper.selectByExample(example1);
        //取交集
        List<TAuButton> buttonList = new ArrayList<>();
        buttonList = tAuButtons.stream().filter((TAuButton t) -> buttonIdList.contains(t.getId()))
                .collect(Collectors.toList());
        return buttonList;
    }

    /**
     * 根据父按钮id 子按钮（和角色授权得按钮进行过滤）-------ksf用
     * @param roleIds
     * @param parentButtonId
     * @return
     */
    private List<TAuButton> getButtonChildrenByParentId(String roleIds,Integer parentButtonId){
        String[] roles = roleIds.split(",");
        List<Integer> buttonIdList = new ArrayList<>();
        for (int i = 0; i < roles.length; i++) {
            //查===>授权得按钮list
            TAuRoleResourceExample example = new TAuRoleResourceExample();
            TAuRoleResourceExample.Criteria criteria = example.createCriteria();
            criteria.andRoleIdEqualTo(Integer.valueOf(roles[i]));
            criteria.andResourceTypeEqualTo(ConstantUtil.BUTTON);
            criteria.andStateEqualTo("1");
            List<TAuRoleResource> roleResourceList = tAuRoleResourceMapper.selectByExample(example);
            List<Integer> buttonIdList1 = new ArrayList<>();
            for (TAuRoleResource tAuRoleResource: roleResourceList) {
                Integer buttonId = tAuRoleResource.getResourceItemId();
                buttonIdList1.add(buttonId);
            }
            buttonIdList.addAll(buttonIdList1);
        }
        //查父按钮下得可用按钮list
        TAuButtonExample example1 =new TAuButtonExample();
        TAuButtonExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andParentIdEqualTo(parentButtonId);
        criteria1.andDataStateEqualTo(DataStateCode.AUDITED);
        criteria1.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria1.andProhibitStateEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuButton> tAuButtons =  tAuButtonMapper.selectByExample(example1);
        //取交集
        List<TAuButton> buttonList = new ArrayList<>();
        buttonList = tAuButtons.stream().filter((TAuButton t) -> buttonIdList.contains(t.getId()))
                .collect(Collectors.toList());
        return buttonList;
    }

    /**
     * 查菜单下所有可用按钮--ksf（超级管理员）
     * @return
     */
    private List<TAuButton> getButtonListByMenuId(Integer menuId){
        //查菜单下得可用按钮list
        TAuButtonExample example1 =new TAuButtonExample();
        TAuButtonExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andMenuIdEqualTo(menuId);
        criteria1.andDataStateEqualTo(DataStateCode.AUDITED);
        criteria1.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria1.andProhibitStateEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuButton> tAuButtons =  tAuButtonMapper.selectByExample(example1);
        return tAuButtons;
    }

    /**
     * 查父按钮所有可用按钮---ksf(超级管理)
     * @return
     */
    private List<TAuButton> getButtonListByParentId(Integer parentButtonId){
        //查菜单下得可用按钮list
        //查父按钮下得可用按钮list
        TAuButtonExample example1 =new TAuButtonExample();
        TAuButtonExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andParentIdEqualTo(parentButtonId);
        criteria1.andDataStateEqualTo(DataStateCode.AUDITED);
        criteria1.andIsDeleteEqualTo(ConstantUtil.YES_OR_NO_N);
        criteria1.andProhibitStateEqualTo(ConstantUtil.YES_OR_NO_N);
        List<TAuButton> tAuButtons =  tAuButtonMapper.selectByExample(example1);
        return tAuButtons;
    }
}
