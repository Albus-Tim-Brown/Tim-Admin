package cn.tim.std.config;

import cn.tim.std.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @Author: albus
 * @Date: 2023/8/8 11:04
 * @Project: Tim-Admin-SpringBoot-Project
 * @Package: cn.tim.std.config
 * @Description: Shiro配置
 */
@Configuration
public class ShiroConfig {

    @Bean //UserRealm
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    @Bean //Shiro过滤器
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //!!!!!必须设置
        factoryBean.setSecurityManager(securityManager);
        //anon 放行   authc 认证后访问
        //放行内容放前，拦截内容放后
        HashMap<String, String> map = new HashMap<>();
//        map.put("/login","anon");//登录请求放行
//        map.put("/register","anon");//注册请求放行
//        map.put("/**","authc");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }
}
