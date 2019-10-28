package com.vacomall.service;

import com.baomidou.mybatisplus.service.IService;
import com.vacomall.entity.SysIndexauth;
import com.vacomall.entity.vo.TreeIndexAllowAccess;
import com.vacomall.entity.vo.TreeMenuAllowAccess;

import java.util.List;

/**
 *
 * SysIndexauth 表数据服务层接口
 *
 */
public interface ISysIndexauthService extends IService<SysIndexauth> {

    /**
     * 获取指定用户拥有权限
     * @param  menuIds 该角色拥有的权限ID集合
     * @param  pid 菜单父ID
     */
    List<TreeIndexAllowAccess> selectTreeMenuAllowAccessByMenuIdsAndPid(List<String> menuIds, String pid);
}
