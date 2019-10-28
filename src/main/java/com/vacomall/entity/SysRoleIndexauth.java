package com.vacomall.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;


/**
 * <p>
 * 角色报表关联表
 * </p>
 *
 */
@TableName("sys_role_indexauth")
public class SysRoleIndexauth extends Model<SysRoleIndexauth> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getIndexAuth() {
		return indexAuth;
	}

	public void setIndexAuth(String indexAuth) {
		this.indexAuth = indexAuth;
	}

	/**
     * 角色主键
     */
	private String roleId;
    /**
     * 报表主键
     */
	private String indexAuth;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
