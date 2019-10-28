package com.vacomall.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.vacomall.entity.SysIndexauth;
import com.vacomall.entity.SysRoleIndexauth;
import com.vacomall.entity.vo.TreeIndexAllowAccess;
import com.vacomall.service.ISysIndexauthService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.vacomall.entity.SysMenu;
import com.vacomall.entity.SysRoleMenu;
import com.vacomall.entity.vo.TreeMenu;
import com.vacomall.entity.vo.TreeMenuAllowAccess;
import com.vacomall.mapper.SysMenuMapper;
import com.vacomall.mapper.SysRoleIndexauthMapper;
import com.vacomall.mapper.SysRoleMenuMapper;
import com.vacomall.service.ISysMenuService;
import com.vacomall.service.ISysRoleMenuService;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * SysMenu 表数据服务层接口实现类
 *
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

	/**
	 * 角色权限服务
	 */
	@Autowired private ISysRoleMenuService sysRoleMenuService;

	@Autowired private SysMenuMapper sysMenuMapper;

	@Autowired private SysRoleIndexauthMapper sysRoleIndexauthMapper;

	@Autowired private SysRoleMenuMapper sysRoleMenuMapper;

	@Autowired private ISysIndexauthService sysIndexauthService;

	@Autowired
	protected HttpServletRequest request;

	@Cacheable(value = "permissionCache", key = "#uid")
	@Override
	public List<String> selectMenuIdsByuserId(String uid) {
		// TODO Auto-generated method stub
		return sysMenuMapper.selectMenuIdsByuserId(uid);
	}

	@Override
	public List<TreeMenu> selectTreeMenuByMenuIdsAndPid(final List<String> menuIds,
			String pid) {
		// TODO Auto-generated method stub


		EntityWrapper<SysMenu> ew = new EntityWrapper<SysMenu>();
		ew.orderBy("sort", true);
		ew.addFilter("pid = {0} ", pid);
		ew.in("id", menuIds.size() > 0 ? menuIds : Lists.newArrayList(RandomStringUtils.randomNumeric(30)));
		List<SysMenu> sysMenus = this.selectList(ew);

		List<TreeMenu> treeMenus = Lists.transform(sysMenus, new Function<SysMenu, TreeMenu>() {
			@Override
			public TreeMenu apply(SysMenu sysMenu) {
				// TODO Auto-generated method stub
				TreeMenu treeMenu = new TreeMenu();
				treeMenu.setSysMenu(sysMenu);
				/**
				 * 子节点
				 */
				if(sysMenu.getDeep() < 2){
					treeMenu.setChildren(selectTreeMenuByMenuIdsAndPid(menuIds,sysMenu.getId()));
				}
				List<TreeIndexAllowAccess> treeIndexAllowAccesss = selectTreeIndexAllowAccessByMenuIdsAndPid(menuIds,null);

				treeMenu.setTreeIndexAllowAccess(treeIndexAllowAccesss);

				return treeMenu;
			}
		});

		return treeMenus;

	}

	@Cacheable(value = "menuCache", key = "#uid")
	@Override
	public List<TreeMenu> selectTreeMenuByUserId(String uid) {
		// TODO Auto-generated method stub
		/**
		 * 当前用户二级菜单权限
		 */
		List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectRoleMenuByUserId(uid);
		/**
		 * 当前用户菜单主键
		 */
		List<String> menuIds = Lists.transform(sysRoleMenus, new Function<SysRoleMenu, String>() {
			@Override
			public String apply(SysRoleMenu input) {
				// TODO Auto-generated method stub
				return input.getMenuId();
			}
		});
		return selectTreeMenuByMenuIdsAndPid(menuIds, "0");
	}

	@Override
	public List<TreeMenuAllowAccess> selectTreeMenuAllowAccessByMenuIdsAndPid(
			final List<String> menuIds, String pid) {
		// TODO Auto-generated method stub

		EntityWrapper<SysMenu> ew = new EntityWrapper<SysMenu>();
		ew.orderBy("sort", true);
		ew.addFilter("pid = {0} ", pid);
		List<SysMenu> sysMenus = this.selectList(ew);
		List<TreeMenuAllowAccess> treeMenuAllowAccesss = Lists.transform(sysMenus, new Function<SysMenu, TreeMenuAllowAccess>() {
			@Override
			public TreeMenuAllowAccess apply(SysMenu sysMenu) {
				// TODO Auto-generated method stub
				TreeMenuAllowAccess treeMenuAllowAccess = new TreeMenuAllowAccess();
				treeMenuAllowAccess.setSysMenu(sysMenu);
				/**
				 * 是否有权限
				 */
				if(menuIds.contains(sysMenu.getId())){
					treeMenuAllowAccess.setAllowAccess(true);
				}
				/**
				 * 子节点
				 */
				if(sysMenu.getDeep() < 3){
					treeMenuAllowAccess.setChildren(selectTreeMenuAllowAccessByMenuIdsAndPid(menuIds,sysMenu.getId()));
				}
				return treeMenuAllowAccess;
			}
		});
		return treeMenuAllowAccesss;
	}

	public List<SysRoleIndexauth> selectIndexAuthByuserId(String uid) {

		return sysRoleIndexauthMapper.selectRoleIndexauthByUserId(uid);
	}

	public List<TreeIndexAllowAccess> selectTreeIndexAllowAccessByMenuIdsAndPid(
			final List<String> menuIds, String pid) {
		// TODO Auto-generated method stub

		EntityWrapper<SysIndexauth> ew = new EntityWrapper<SysIndexauth>();
		//ew.orderBy("sort", true);
		//本用户所有角色所有报表id
		//获取当前登录用户id
		SSOToken tk = SSOHelper.attrToken(request);
		if ( tk == null ) {
			throw new RuntimeException("-1,The user does not exist, please relogin.");
		}
		String userid = tk.getUid();
		List<SysRoleIndexauth> indexAuths =selectIndexAuthByuserId(userid);
		List<String> indexAuth = new ArrayList();
		if(indexAuths!=null&&indexAuths.size()>0){
			for(int i=0;i<indexAuths.size();i++){
				indexAuth.add(indexAuths.get(i).getIndexAuth());
			}
			ew.in("id", indexAuth);
		}

		List<SysIndexauth> sysMenus = sysIndexauthService.selectList(ew);
		List<TreeIndexAllowAccess> treeMenuAllowAccesss = Lists.transform(sysMenus, new Function<SysIndexauth, TreeIndexAllowAccess>() {
			@Override
			public TreeIndexAllowAccess apply(SysIndexauth sysMenu) {
				// TODO Auto-generated method stub
				TreeIndexAllowAccess treeMenuAllowAccess = new TreeIndexAllowAccess();
				treeMenuAllowAccess.setSysIndexauth(sysMenu);
				/**
				 * 是否有权限
				 */
				if(menuIds.contains(sysMenu.getId())){
					treeMenuAllowAccess.setAllowAccess(true);
				}
				return treeMenuAllowAccess;
			}
		});
		return treeMenuAllowAccesss;
	}
}
