package com.vacomall.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.vacomall.entity.SysRoleIndexauth;
import com.vacomall.mapper.SysRoleIndexauthMapper;
import com.vacomall.service.ISysRoleIndexauthService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * SysRoleIndexauth 表数据服务层接口实现类
 *
 */
@Service
public class SysRoleIndexauthServiceImpl extends ServiceImpl<SysRoleIndexauthMapper, SysRoleIndexauth> implements ISysRoleIndexauthService {



    @Override
    public void addAuthother(String roleId, String[] menuIds) {
        // TODO Auto-generated method stub

        /**
         * 删除原有权限
         */
        this.delete(new EntityWrapper<SysRoleIndexauth>().eq("roleId",roleId));
        /**
         * 重新授权
         */
        if(ArrayUtils.isNotEmpty(menuIds)){
            for(String menuId : menuIds){
				SysRoleIndexauth sysRoleMenu2 = new SysRoleIndexauth();
                sysRoleMenu2.setRoleId(roleId);
                sysRoleMenu2.setIndexAuth(menuId);
                this.insert(sysRoleMenu2);
            }
        }
    }

	@Override
	public List<SysRoleIndexauth> selectByRole(String roleId) {
		// TODO Auto-generated method stub

		EntityWrapper<SysRoleIndexauth> ew = new EntityWrapper<SysRoleIndexauth>();
		ew.addFilter("roleId = {0}", roleId);
		return this.selectList(ew);

	}


}
