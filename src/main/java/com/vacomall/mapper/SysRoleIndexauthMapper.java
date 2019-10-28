package com.vacomall.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.vacomall.entity.SysRoleIndexauth;

import java.util.List;

/**
 *
 * SysRoleIndexauth 表数据库控制层接口
 *
 */
public interface SysRoleIndexauthMapper extends BaseMapper<SysRoleIndexauth> {

	/**
	 * 根据用户Id获取用户所在角色的权限
	 */
	public List<SysRoleIndexauth> selectRoleIndexauthByUserId(String uid);

}
