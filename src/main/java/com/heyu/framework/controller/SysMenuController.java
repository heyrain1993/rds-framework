package com.heyu.framework.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heyu.framework.exception.PageException;
import com.heyu.framework.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.heyu.framework.controller.BaseController;
import com.heyu.framework.model.Page;
import com.heyu.framework.model.SysMenu;
import com.heyu.framework.service.SysMenuService;
/**
 * 
 * @author heyu
 * @email 614457294@qq.com
 * @date 2018-05-23 14:06:11
 *
 */
@Controller
@RequestMapping(value="framework/sysMenu")
public class SysMenuController extends BaseController{
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping()
	public String get(@RequestParam(value="id")String id,Model model) {
		SysMenu sysMenu = sysMenuService.findById(id);
		model.addAttribute("sysMenu", sysMenu);
		return "framework/sysMenuForm";
	}
	
	/**
	 * 根据条件分页查询
	 * @param request 请求
	 * @param response 响应
	 * @param sysMenu 实体类
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,HttpServletResponse response,SysMenu sysMenu,Model model){
		/*Page<SysMenu> page = new Page<SysMenu>(request);
		Page<SysMenu> result = sysMenuService.findPage(page,sysMenu);
		model.addAttribute("page", result);*/
		List<SysMenu> list = sysMenuService.findList(sysMenu);
		model.addAttribute("list", list);
		return "framework/sysMenuList";
	}
	
	/**
	 * 根据条件查询所有
	 * @param sysMenu 实体类
	 * @return
	 */
	@RequestMapping(value = "findAll",method=RequestMethod.POST)
	@ResponseBody
	public List<SysMenu> findAll(SysMenu sysMenu){
		List<SysMenu> lists = sysMenuService.findList(sysMenu);
		return lists;
	}
	
	/**
	 * 根据id查询实体类
	 * @param parentId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="form",method=RequestMethod.GET)
	public String form(@RequestParam(value = "parentId",required = false)String parentId, Model model) {
		SysMenu sysMenu = new SysMenu();
		sysMenu.setParentId(parentId);
		model.addAttribute("sysMenu",sysMenu);
		return "framework/sysMenuForm";
	}
	
	/**
	 * 保存-使用validate校验
	 * @param sysMenu
	 * @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(SysMenu sysMenu) {
		try {
			sysMenuService.save(sysMenu);
		}catch (Exception e){
			e.printStackTrace();
			throw new PageException(e.getMessage());
		}
		return "redirect:/framework/sysMenu/list";
	}
	
	/**
	 * 删除实体类
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public String delete(@RequestParam(value="id")String id) {
		sysMenuService.delete(id);
		return "redirect:/framework/sysMenu/list";
	}
	
}
