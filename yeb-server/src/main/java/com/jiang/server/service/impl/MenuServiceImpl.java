package com.jiang.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.server.mapper.MenuMapper;
import com.jiang.server.pojo.Admin;
import com.jiang.server.pojo.Menu;
import com.jiang.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate<String ,Object> redisTemplate;
    /***
     * 通过用户ID 获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenuByAdminId() {

        //通过Security上下文环境拿到当前登录用户
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
//        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
//        List<Menu> menus = (List<Menu>)ops.get("Menu_" + adminId);


//        //如果从redis中的数据为空
//        if(CollectionUtils.isEmpty(menus)){
//            menus = menuMapper.getMenuByAdminId(adminId);
//            //将数据设置到redis
//            ops.set("Menu_" + adminId,menus);
//        }
        List<Menu> menus = menuMapper.getMenuByAdminId(adminId);


        return menus;

    }


    @Override
    public List<Menu> getMenusByRoles() {

        return menuMapper.getMenusByRoles();
    }


    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
