package com.jiang.server.controller;


import com.jiang.server.pojo.Menu;
import com.jiang.server.service.impl.AdminServiceImpl;
import com.jiang.server.service.impl.MenuServiceImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private MenuServiceImpl menuService;

    @ApiOperation(value = "通过用户ID查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenusByAdminId(){
        return menuService.getMenuByAdminId();
    }


}
