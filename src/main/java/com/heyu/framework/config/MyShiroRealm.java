package com.heyu.framework.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.heyu.framework.model.SysUser;
import com.heyu.framework.service.SysUserService;

public class MyShiroRealm extends AuthorizingRealm{

	@Autowired
	private SysUserService sysUserService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 身份认证 ，用来验证用户登录认证
	 * 认证内部实现机制中最终会交给realm处理；
	 * shiro中最终通过realm获取程序中用户、角色、权限信息
	 * realm通过数据源获取shiro认证所需的信息——realm专用于shiro安全框架的DAO
	 * shiro认证最终会交给realm执行，realm调用doGetAuthenticationInfo(token)方法
	 * 方法作用：
	 * 1.检查提交的认证令牌信息
	 * 2.根据令牌信息从数据源中获取用户信息
	 * 3.对用户信息进行匹配验证
	 * 4.验证通过返回AuthenticationInfo实例
	 * 5.验证失败抛出AuthenticationException异常
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {

		System.out.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
		String username = (String)token.getPrincipal();
		SysUser user = sysUserService.findByUsername(username);
		if(user == null) {
			System.out.println("用户不存在");
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,
				user.getPassword(),user.getUsername());
		
		return authenticationInfo;
	}

}
