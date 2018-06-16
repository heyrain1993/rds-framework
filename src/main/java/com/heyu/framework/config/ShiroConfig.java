package com.heyu.framework.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * securityManager配置
     * 该类集成了shiro的认证、授权、会话管理、缓存管理、连接realm等功能
     * 然后委托各个模块具体解决
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //内部方法调用realm的getAuthenticationInfo获取认证信息
        securityManager.setRealm(authRealm());
        securityManager.setCacheManager(redisCacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 是工厂类型的bean，为了生成shiroFilter，用于过滤 用户请求URL的
     * 主要保存三个对象：securityManager、filters、filterChainDefinitionManager(定义过滤的URL)
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager());
        factoryBean.setLoginUrl("/login");//设置登录URL
        factoryBean.setUnauthorizedUrl("/login");//设置未授权URL
        factoryBean.setSuccessUrl("/index");//设置登录成功后跳转URL


        Map<String,Filter> filters = new LinkedHashMap<>();//使用LinkedHashMap保持过滤器有序
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/login");//设置退出后重定向的URL
        filters.put("logoutFilter",logoutFilter);
        factoryBean.setFilters(filters);

        Map<String,String> filterChain = new LinkedHashMap<>();
        filterChain.put("/logout","logout");
        filterChain.put("/css/**","anon");
        filterChain.put("/fonts/**","anon");
        filterChain.put("/images/**","anon");
        filterChain.put("/js/**","anon");
        filterChain.put("/login","anon");
        filterChain.put("/**","authc");//设置在最后，按照顺序判断，如果匹配则返回
        factoryBean.setFilterChainDefinitionMap(filterChain);

        return factoryBean;
    }

    /**
     * 为了对密码进行编码，数据库密码不是明文保存，表单提交是明文
     * 配置matcher对表单密码加密
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");//设置加密算法
        matcher.setHashIterations(1);//设置加密次数
        return matcher;
    }

    /**
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * shirorealm，自定义的认证类，继承AuthorizingRealm
     * 负责用户的认证和授权，参考JdbcRealm
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthRealm authRealm(){
        AuthRealm realm = new AuthRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());//设置加密匹配器
        return realm;
    }

    /**
     * 缓存管理，用户成功后缓存用户认证信息和权限信息，避免每次请求查询数据库
     * @return
     */
    /*@Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public CacheManager cacheManager(){
        return new EhCacheManager();
    }*/

    /**
     * Spring的一个bean,有advisor决定对那些类实现AOP代理
     * 配置通过注解完成权限和角色验证
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisor = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisor.setProxyTargetClass(true);
        return defaultAdvisor;
    }

    /**
     * shiro类的实现Advisor的类，开启shiro的注解支持
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor
     * 拦截@RequiredRoles,@RequiredPermission注解的方法
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager cacheManager = new RedisCacheManager();
        return cacheManager;
    }

    @Bean
    public RedisSessionDao redisSessionDao(){
        RedisSessionDao redisSessionDao = new RedisSessionDao();
        return redisSessionDao;
    }

    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDao());
        return sessionManager;
    }

}
