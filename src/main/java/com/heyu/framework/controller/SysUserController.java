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
import com.heyu.framework.model.SysUser;
import com.heyu.framework.service.SysUserService;
/**
 * 
 * @author heyu
 * @email 614457294@qq.com
 * @date 2018-05-15 16:20:57
 *
 */
@Controller
@RequestMapping(value="framework/sysUser")
public class SysUserController extends BaseController{
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping()
	public String get(@RequestParam(value="id")String id,Model model) {
		SysUser sysUser = sysUserService.findById(id);
		model.addAttribute("sysUser", sysUser);
		return "framework/sysUserForm";
	}
	
	/**
	 * 根据条件分页查询
	 * @param request 请求
	 * @param response 响应
	 * @param sysUser 实体类
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,HttpServletResponse response,SysUser sysUser,Model model){
		Page<SysUser> page = new Page<SysUser>(request);
		Page<SysUser> result = sysUserService.findPage(page,sysUser);
		model.addAttribute("page", result);
		return "framework/sysUserList";
	}
	
	/**
	 * 根据条件查询所有
	 * @param sysUser 实体类
	 * @return
	 */
	@RequestMapping(value = "findAll",method=RequestMethod.POST)
	@ResponseBody
	public List<SysUser> findAll(SysUser sysUser){
		List<SysUser> lists = sysUserService.findList(sysUser);
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
		SysUser sysUser = new SysUser();

		model.addAttribute("sysUser",sysUser);
		return "framework/sysUserForm";
	}
	
	/**
	 * 保存-使用validate校验
	 * @param sysUser
	 * @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(SysUser sysUser) {

		sysUserService.save(sysUser);
		return "redirect:/framework/sysUser/list";
	}
	
	/**
	 * 删除实体类
	 * @param sysUser
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public String delete(@RequestParam(value="id")String id) {
		sysUserService.delete(id);
		return "redirect:/framework/sysUser/list";
	}
	
}
