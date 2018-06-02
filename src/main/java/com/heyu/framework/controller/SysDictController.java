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
import com.heyu.framework.model.SysDict;
import com.heyu.framework.service.SysDictService;
/**
 * 
 * @author heyu
 * @email 614457294@qq.com
 * @date 2018-05-25 15:09:58
 *
 */
@Controller
@RequestMapping(value="framework/sysDict")
public class SysDictController extends BaseController{
	
	@Autowired
	private SysDictService sysDictService;
	
	@RequestMapping()
	public String get(@RequestParam(value="id")String id,Model model) {
		SysDict sysDict = sysDictService.findById(id);
		model.addAttribute("sysDict", sysDict);
		return "framework/sysDictForm";
	}
	
	/**
	 * 根据条件分页查询
	 * @param request 请求
	 * @param response 响应
	 * @param sysDict 实体类
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,HttpServletResponse response,SysDict sysDict,Model model){
		Page<SysDict> page = new Page<SysDict>(request);
		Page<SysDict> result = sysDictService.findPage(page,sysDict);
		model.addAttribute("page", result);
		model.addAttribute("sysDict", sysDict);
		return "framework/sysDictList";
	}
	
	/**
	 * 根据条件查询所有
	 * @param sysDict 实体类
	 * @return
	 */
	@RequestMapping(value = "findAll",method=RequestMethod.POST)
	@ResponseBody
	public List<SysDict> findAll(SysDict sysDict){
		List<SysDict> lists = sysDictService.findList(sysDict);
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
		SysDict sysDict = new SysDict();

		model.addAttribute("sysDict",sysDict);
		return "framework/sysDictForm";
	}
	
	/**
	 * 保存-使用validate校验
	 * @param sysDict
	 * @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(SysDict sysDict) {

		sysDictService.save(sysDict);
		return "redirect:/framework/sysDict/list";
	}
	
	/**
	 * 删除实体类
	 * @param sysDict
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.GET)
	public String delete(@RequestParam(value="id")String id) {
		sysDictService.delete(id);
		return "redirect:/framework/sysDict/list";
	}
	
}
