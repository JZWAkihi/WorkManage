package com.jiang.server.config;

import com.jiang.server.pojo.Menu;
import com.jiang.server.pojo.Role;
import com.jiang.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/***
 * 根据请求url分析出请求所需角色
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IMenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        //获取请求的url
        String requestUrl = ((FilterInvocation)object).getRequestUrl();
        System.out.println(requestUrl);
        //获取菜单
        List<Menu> menus = menuService.getMenusByRoles();
        System.out.println(menus);
        for (Menu menu:menus) {
            //判断请求的url与菜单角色是否匹配
            if(antPathMatcher.match(menu.getUrl(),requestUrl)){
                String[] str = menu.getRoles().stream().map(Role::getName).toArray(String[] :: new);
                System.out.println(str);
                return SecurityConfig.createList(str);
            }
        }

        //没匹配的url默认为登录即可访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
