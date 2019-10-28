package com.vacomall.entity.vo;

import com.vacomall.entity.SysIndexauth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 报表+是否有权限表示
 */
public class TreeIndexAllowAccess implements Serializable{

	/**
	* @Fields serialVersionUID : TODO()
	*/

	private static final long serialVersionUID = 1L;

	/**
	 * 报表
	 */
	private SysIndexauth sysIndexauth;
	/**
	 * 是否允许访问
	 */
	private boolean allowAccess = false;

	public SysIndexauth getSysIndexauth() {
		return sysIndexauth;
	}

	public void setSysIndexauth(SysIndexauth sysIndexauth) {
		this.sysIndexauth = sysIndexauth;
	}

	public boolean isAllowAccess() {
		return allowAccess;
	}

	public void setAllowAccess(boolean allowAccess) {
		this.allowAccess = allowAccess;
	}
}
