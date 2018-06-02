package com.heyu.framework.config;

import com.heyu.framework.model.SysPermission;
import com.heyu.framework.model.SysRole;
import com.heyu.framework.model.SysUser;
import com.heyu.framework.service.SysPermissionService;
import com.heyu.framework.service.SysRoleService;
import com.heyu.framework.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;
    /**
     * 授权，每次需要权限鉴定的时候授权，因此可使用缓存，避免数据库查询频繁
     * 定义完AOP之后，遇到RequiredRoles,RequierdPermission,RequiresUser,RequiresGuest,RequiresAuthentication拦截
     * 会先调用Subject的checkXXX方法，checkXXX方法内部调用securityManager的isXXX方法，isXXX方法内部调用realm的
     * getAuthorizationInfo方法，之后再调用securityManager重载的isXXX方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权中。。。。。" + principalCollection.toString());

        SysUser sysUser = sysUserService.findByUsername((String) principalCollection.getPrimaryPrincipal());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //查询用户拥有角色
        List<SysRole> sysRoles = sysRoleService.findByUserId(sysUser.getId());
        if(sysRoles != null && sysRoles.size() != 0){
            for (SysRole sysRole:sysRoles){
                info.addRole(sysRole.getName());
            }
        }

        //查询用户所拥有的权限
        List<SysPermission> sysPermissionss = sysPermissionService.findByUserId(sysUser.getId());
        if(sysPermissionss != null && sysPermissionss.size() != 0){
            for (SysPermission sysPermission:sysPermissionss){
                info.addStringPermission(sysPermission.getName());
            }

        }

        return info;
    }

    /**
     * 认证,每次登录的时候验证，调用subjeect.login(token)中调用该方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        System.out.println("认证中。。。。。"+authenticationToken.toString());

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        String username = token.getUsername();//获取登录用户名

        SysUser sysUser = sysUserService.findByUsername(username);
        if (sysUser != null){
            //principal用户、credentials凭证,也可传入盐
            return new SimpleAuthenticationInfo(username,sysUser.getPassword(),this.getName());
        }else {
            return null;
        }
    }

    public static void main(String[] args) {
        SimpleHash simpleHash  =  new SimpleHash("MD5","lisi",null,1);


        System.out.println(simpleHash);
    }
}
