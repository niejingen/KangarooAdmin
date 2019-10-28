package com.vacomall.controller.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.vacomall.common.anno.Log;
import com.vacomall.common.anno.Permission;
import com.vacomall.common.bean.Response;
import com.vacomall.common.controller.SuperController;
import com.vacomall.entity.SysIndexauth;
import com.vacomall.service.ISysIndexauthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 报表控制器
 */
@Controller
@RequestMapping("/system/indexauth")
public class IndexauthController extends SuperController{

	@Autowired private ISysIndexauthService sysIndexauthService;

	/**
	 * 分页查询
	 */
	@Permission("listIndexauth")
    @RequestMapping("/list/{pageNumber}")
    public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize, String search,Model model){

		Page<SysIndexauth> page = getPage(pageNumber,pageSize);
		model.addAttribute("pageSize", pageSize);
		// 查询分页
		EntityWrapper<SysIndexauth> ew = new EntityWrapper<SysIndexauth>();
		if(StringUtils.isNotBlank(search)){
			ew.like("authName",search);
			model.addAttribute("search",search);
		}
		Page<SysIndexauth> pageData = sysIndexauthService.selectPage(page, ew);
		model.addAttribute("pageData", pageData);
		return "system/indexauth/list";
    }

    /**
     * 新增
     */
	@Permission("addIndexauth")
    @RequestMapping("/add")
    public  String add(Model model){
		return "system/indexauth/add";
    }

    /**
     * 执行新增
     */
	@Permission("addIndexauth")
    @Log("创建报表")
    @RequestMapping("/doAdd")
    public  String doAdd(SysIndexauth indexauth,String[] roleId){

    	sysIndexauthService.insert(indexauth);
		return redirectTo("/system/indexauth/list/1.html");
    }
    /**
     * 删除
     */
	@Permission("deleteIndexauth")
    @Log("删除")
    @RequestMapping("/delete")
    @ResponseBody
    public  Response delete(String id){
    	sysIndexauthService.deleteById(id);
    	return new Response().success();
    }

	/**
	 * 编辑
	 */
	@Permission("editIndexauth")
    @RequestMapping("/edit/{id}")
    public  String edit(@PathVariable String id,Model model){
    	SysIndexauth indexauth = sysIndexauthService.selectById(id);

    	model.addAttribute("indexauth",indexauth);
    	return "system/indexauth/edit";
    }
    /**
     * 执行编辑
     */
	@Permission("editIndexauth")
    @Log("编辑报表")
    @RequestMapping("/doEdit")
    public  String doEdit(SysIndexauth indexauth,Model model){
    	sysIndexauthService.updateById(indexauth);
    	return redirectTo("/system/indexauth/list/1.html");
    }

}
