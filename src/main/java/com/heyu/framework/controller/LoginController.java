package com.heyu.framework.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.heyu.framework.model.SysUser;

@Controller
public class LoginController {

	/**
	 * 访问登录页面
	 */
	@RequestMapping(value = "/login",method = RequestMethod.GET )
	public String login(Model model) {
		model.addAttribute("user", new SysUser());
		//JSON.toJSONString(model);
		return "login";
	}
	
	/**
	 * 用户登录，登录成功返回首页；否则返回登录页面
	 * @param request
	 * @param user
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login(HttpServletRequest request,SysUser user,Model model,RedirectAttributes redirectAttributes) {
		System.out.println("快速登录使用");

		if(user == null || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
			redirectAttributes.addFlashAttribute("message", "用户名或密码不能为空");
			return "redirect:login";//登录失败
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
		try {
			//内部会先调用realm中的getCachedAuthenticationInfo方法获取缓存中的用户信息
			// 如果没有在调用Realm类中的doGetAuthenticationInfo方法获取用户信息
			subject.login(token);
			System.out.println("index");
			//登录成功
			return "admin/partial/index";
		} catch (AuthenticationException  e) {
			token.clear();
			redirectAttributes.addAttribute("message", "用户名或密码不存在");
			System.out.println("\"redirect:/\"");
			return "redirect:login";//登录失败
		}
	}
	
}
