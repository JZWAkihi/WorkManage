package com.jiang.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.server.pojo.Admin;
import com.jiang.server.pojo.Menu;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
public interface IMenuService extends IService<Menu> {


    /***
     * 通过用户ID 获取菜单列表
     * @return
     */
    List<Menu> getMenuByAdminId();


    List<Menu> getMenusByRoles();

    List<Menu> getAllMenus();
}
