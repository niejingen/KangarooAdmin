package com.vacomall.service;

import com.baomidou.mybatisplus.service.IService;
import com.vacomall.entity.SysRoleIndexauth;

import java.util.List;

/**
 *
 * SysRoleIndexauth 表数据服务层接口
 *
 */
public interface ISysRoleIndexauthService extends IService<SysRoleIndexauth> {


	/**
	 * 角色报表授权
	 */
	void addAuthother(String roleId, String[] menuIds);

	/**
	 * 获取指定角色的权限
	 */
	List<SysRoleIndexauth> selectByRole(String roleId);


}
