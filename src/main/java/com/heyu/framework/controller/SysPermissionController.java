package com.heyu.framework.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.heyu.framework.controller.BaseController;
import com.heyu.framework.model.Page;
import com.heyu.framework.model.SysPermission;
import com.heyu.framework.service.SysPermissionService;
/**
 * 
 * @author heyu
 * @email 614457294@qq.com
 * @date 2018-05-30 19:48:47
 *
 */
@Controller
@RequestMapping(value="framework/sysPermission")
public class SysPermissionController extends BaseController{
	
	@Autowired
	private SysPermissionService sysPermissionService;
	
	@RequestMapping()
	public String get(@RequestParam(value="id")String id,Model model) {
		SysPermission sysPermission = sysPermissionService.findById(id);
		model.addAttribute("sysPermission", sysPermission);
		return "framework/sysPermissionForm";
	}
	
	/**
	 * 根据条件分页查询
	 * @param request 请求
	 * @param response 响应
	 * @param sysPermission 实体类
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,HttpServletResponse response,SysPermission sysPermission,Model model){
		Page<SysPermission> page = new Page<SysPermission>(request);
		Page<SysPermission> result = sysPermissionService.findPage(page,sysPermission);
		model.addAttribute("page", result);
		model.addAttribute("sysPermission", sysPermission);
		return "framework/sysPermissionList";
	}
	
	/**
	 * 根据条件查询所有
	 * @param sysPermission 实体类
	 * @return
	 */
	@RequestMapping(value = "findAll",method=RequestMethod.POST)
	@ResponseBody
	public List<SysPermission> findAll(SysPermission sysPermission){
		List<SysPermission> lists = sysPermissionService.findList(sysPermission);
		return lists;
	}
	
	/**
	 * 根据id查询实体类
	 * @param id 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="form",method=RequestMethod.GET)
	public String form(Model model) {
		SysPermission sysPermission = new SysPermission();

		model.addAttribute("sysPermission",sysPermission);
		return "framework/sysPermissionForm";
	}
	
	/**
	 * 保存-使用validate校验
	 * @param sysPermission
	 * @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(SysPermission sysPermission) {

		sysPermissionService.save(sysPermission);
		return "redirect:/framework/sysPermission/list";
	}
	
	/**
	 * 删除实体类
	 * @param sysPermission
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public String delete(@RequestParam(value="id")String id) {
		sysPermissionService.delete(id);
		return "redirect:/framework/sysPermission/list";
	}
	
}
