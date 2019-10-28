package com.vacomall.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.vacomall.entity.SysIndexauth;
import com.vacomall.entity.SysMenu;
import com.vacomall.entity.vo.TreeIndexAllowAccess;
import com.vacomall.entity.vo.TreeMenuAllowAccess;
import com.vacomall.mapper.SysIndexauthMapper;
import com.vacomall.service.ISysIndexauthService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * SysIndexauth 表数据服务层接口实现类
 *
 */
@Service
public class SysIndexauthServiceImpl extends ServiceImpl<SysIndexauthMapper, SysIndexauth> implements ISysIndexauthService {

    @Override
    public List<TreeIndexAllowAccess> selectTreeMenuAllowAccessByMenuIdsAndPid(
            final List<String> menuIds, String pid) {
        // TODO Auto-generated method stub

        EntityWrapper<SysIndexauth> ew = new EntityWrapper<SysIndexauth>();
        //ew.orderBy("sort", true);
        //ew.addFilter("pid = {0} ", pid);
        List<SysIndexauth> sysMenus = this.selectList(ew);
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
