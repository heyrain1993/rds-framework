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
import com.heyu.framework.model.SysRole;
import com.heyu.framework.service.SysRoleService;
/**
 * 
 * @author heyu
 * @email 614457294@qq.com
 * @date 2018-05-16 14:41:55
 *
 */
@Controller
@RequestMapping(value="framework/sysRole")
public class SysRoleController extends BaseController{
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@RequestMapping()
	public String get(@RequestParam(value="id")String id,Model model) {
		SysRole sysRole = sysRoleService.findById(id);
		model.addAttribute("sysRole", sysRole);
		return "framework/sysRoleForm";
	}
	
	/**
	 * 根据条件分页查询
	 * @param request 请求
	 * @param response 响应
	 * @param sysRole 实体类
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,HttpServletResponse response,SysRole sysRole,Model model){
		Page<SysRole> page = new Page<SysRole>(request);
		Page<SysRole> result = sysRoleService.findPage(page,sysRole);
		model.addAttribute("page", result);
		model.addAttribute("sysRole", sysRole);
		return "framework/sysRoleList";
	}
	
	/**
	 * 根据条件查询所有
	 * @param sysRole 实体类
	 * @return
	 */
	@RequestMapping(value = "findAll",method=RequestMethod.POST)
	@ResponseBody
	public List<SysRole> findAll(SysRole sysRole){
		List<SysRole> lists = sysRoleService.findList(sysRole);
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
		SysRole sysRole = new SysRole();

		model.addAttribute("sysRole",sysRole);
		return "framework/sysRoleForm";
	}
	
	/**
	 * 保存-使用validate校验
	 * @param sysRole
	 * @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(SysRole sysRole) {

		sysRoleService.save(sysRole);
		return "redirect:/framework/sysRole/list";
	}
	
	/**
	 * 删除实体类
	 * @param sysRole
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public String delete(@RequestParam(value="id")String id) {
		sysRoleService.delete(id);
		return "redirect:/framework/sysRole/list";
	}
	
}
