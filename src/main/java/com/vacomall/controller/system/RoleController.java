package com.vacomall.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vacomall.entity.vo.TreeIndexAllowAccess;
import com.vacomall.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.vacomall.common.anno.Log;
import com.vacomall.common.anno.Permission;
import com.vacomall.common.bean.Response;
import com.vacomall.common.controller.SuperController;
import com.vacomall.entity.SysRole;
import com.vacomall.entity.SysRoleMenu;
import com.vacomall.entity.SysRoleIndexauth;
import com.vacomall.entity.SysUser;
import com.vacomall.entity.SysUserRole;
import com.vacomall.entity.vo.TreeMenuAllowAccess;

/**
 * 角色控制器
 * @author Gaojun.Zhou
 * @date 2016年12月13日 上午10:23:41
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends SuperController{

	/**
	 * 角色服务
	 */
	@Autowired private ISysRoleService sysRoleService;
	/**
	 * 角色用户服务
	 */
	@Autowired private ISysUserRoleService sysUserRoleService;
	/**
	 * 用户服务
	 */
	@Autowired private ISysUserService sysUserService;
	/**
	 * 菜单服务
	 */
	@Autowired private ISysMenuService sysMenuService;

	/**
	 * 报表服务
	 */
	@Autowired private ISysIndexauthService sysIndexauthService;
	/**
	 * 角色权限服务
	 */
	@Autowired private ISysRoleMenuService sysRoleMenuService;

	/**
	 * 角色报表权限服务
	 */
	@Autowired private ISysRoleIndexauthService sysRoleIndexauthService;

	/**
	 * 分页查询角色
	 */
	@Permission("listRole")
    @RequestMapping("/list/{pageNumber}")
    public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize, String search,Model model){

		Page<SysRole> page = getPage(pageNumber,pageSize);
		page.setOrderByField("createTime");
		page.setAsc(false);
		model.addAttribute("pageSize",pageSize);
		// 查询分页
		EntityWrapper<SysRole> ew = new EntityWrapper<SysRole>();
		if(StringUtils.isNotBlank(search)){
			ew.like("roleName",search);
			model.addAttribute("search",search);
		}
		Page<SysRole> pageData = sysRoleService.selectPage(page, ew);
		model.addAttribute("pageData", pageData);
		return "system/role/list";
    }

    /**
     * 新增角色
     */
	@Permission("addRole")
    @RequestMapping("/add")
    public  String add(Model model){
		return "system/role/add";
    }

    /**
     * 执行新增角色
     */
	@Permission("addRole")
    @Log("创建角色")
    @RequestMapping("/doAdd")
    public  String doAdd(SysRole role){
    	role.setCreateTime(new Date());
    	sysRoleService.insert(role);
		return redirectTo("/system/role/list/1.html");

    }

    /**
     * 删除角色
     */
	@Permission("deleteRole")
    @Log("删除角色")
    @RequestMapping("/delete")
    @ResponseBody
    public  Response delete(String id){
    	sysRoleService.deleteById(id);
    	return new Response().success();
    }

    /**
     * 批量删除角色
     */
	@Permission("deleteBatchRole")
    @Log("批量删除角色")
    @RequestMapping("/deleteBatch")
    @ResponseBody
    public Response deleteBatch(@RequestParam("id[]") List<String> ids){
    	sysRoleService.deleteBatchIds(ids);
    	return new Response().success();
    }

    /**
     * 编辑角色
     */
	@Permission("editRole")
    @RequestMapping("/edit/{id}")
    public  String edit(@PathVariable String id,Model model){
    	SysRole sysRole = sysRoleService.selectById(id);
    	model.addAttribute(sysRole);
    	return "system/role/edit";
    }

    /**
     * 执行编辑角色
     */
	@Permission("editRole")
    @Log("编辑角色")
    @RequestMapping("/doEdit")
    public  String doEdit(SysRole sysRole,Model model){
    	sysRoleService.updateById(sysRole);
    	return redirectTo("/system/role/list/1.html");
    }

    /**
     * 权限
     */
	@Permission("authRole")
    @RequestMapping("/auth/{id}")
    public  String auth(@PathVariable String id,Model model){

    	SysRole sysRole = sysRoleService.selectById(id);

    	List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.selectList(new EntityWrapper<SysRoleMenu>().addFilter("roleId = {0}", id));
    	List<String> menuIds = Lists.transform(sysRoleMenus, new Function<SysRoleMenu, String>() {
			@Override
			public String apply(SysRoleMenu input) {
				// TODO Auto-generated method stub
				return input.getMenuId();
			}
		});

    	List<TreeMenuAllowAccess> treeMenuAllowAccesses = sysMenuService.selectTreeMenuAllowAccessByMenuIdsAndPid(menuIds, "0");

    	model.addAttribute("sysRole", sysRole);
    	model.addAttribute("treeMenuAllowAccesses", treeMenuAllowAccesses);

    	return "system/role/auth";
    }

	/**
	 * 权限
	 */
	@Permission("authRole")
	@RequestMapping("/authother/{id}")
	public  String authother(@PathVariable String id,Model model){

		SysRole sysRole = sysRoleService.selectById(id);

		List<SysRoleIndexauth> sysRoleMenus = sysRoleIndexauthService.selectList(new EntityWrapper<SysRoleIndexauth>().addFilter("roleId = {0}", id));
		List<String> menuIds = Lists.transform(sysRoleMenus, new Function<SysRoleIndexauth, String>() {
			@Override
			public String apply(SysRoleIndexauth input) {
				// TODO Auto-generated method stub
				return input.getIndexAuth();
			}
		});

		List<TreeIndexAllowAccess> treeMenuAllowAccesses = sysIndexauthService.selectTreeMenuAllowAccessByMenuIdsAndPid(menuIds, "0");

		model.addAttribute("sysRole", sysRole);
		model.addAttribute("treeMenuAllowAccesses", treeMenuAllowAccesses);

		return "system/role/authother";
	}


    /**
     * 权限
     */
	@Permission("authRole")
    @Log("角色分配权限")
    @RequestMapping("/doAuth")
    public  String doAuth(String roleId,String[] mid,Model model){
    	sysRoleMenuService.addAuth(roleId,mid);
    	model.addAttribute("info","OK,授权成功,1分钟后生效");
    	this.auth(roleId, model);
    	return "system/role/auth";
    }




	/**
	 * 报表权限
	 */
	@Permission("authRole")
	@Log("角色分配报表权限")
	@RequestMapping("/doAuthother")
	public  String doAuthother(String roleId,String[] mid,Model model){
		sysRoleIndexauthService.addAuthother(roleId,mid);
		model.addAttribute("info","OK,授权成功");
		this.authother(roleId, model);
		return "system/role/authother";
	}

	/**
	 * 获取角色下的所有用户
	 */
	@RequestMapping("/getUsers")
	public String getUsers(String roleId,Model model){

		List<SysUserRole> sysUserRoles = sysUserRoleService.selectList(new EntityWrapper<SysUserRole>().addFilter("roleId = {0}", roleId));

		List<String> userIds = Lists.transform(sysUserRoles, new Function<SysUserRole, String>() {
			@Override
			public String apply(SysUserRole input) {
				// TODO Auto-generated method stub
				return input.getUserId();
			}
		});

		List<SysUser> users  = new ArrayList<SysUser>();

		if(userIds.size() > 0){
			EntityWrapper<SysUser> ew = new EntityWrapper<SysUser>();
			ew.in("id", userIds);
			users= sysUserService.selectList(ew);
		}

		model.addAttribute("users",users);
		return "system/role/users";
	}

	/**
	 * 获取指定角色的用户数量
	 */
	@RequestMapping("/getCount")
	@ResponseBody
	public String getCount(String roleId){

		int count =  sysUserRoleService.selectCount(new EntityWrapper<SysUserRole>().addFilter("roleId = {0}", roleId));
		return String.valueOf(count);
	}

}
