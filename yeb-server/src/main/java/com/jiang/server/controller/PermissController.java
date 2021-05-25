package com.jiang.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiang.server.pojo.Menu;
import com.jiang.server.pojo.MenuRole;
import com.jiang.server.pojo.Role;
import com.jiang.server.service.IMenuService;
import com.jiang.server.service.IRoleService;
import com.jiang.server.service.impl.MenuRoleServiceImpl;
import com.jiang.server.utils.RespBean;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private MenuRoleServiceImpl menuRoleService;



    @ApiModelProperty(value = "获取角色")
    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.list();
    }

    @ApiModelProperty(value = "添加角色")
    @PostMapping("/role")
    public RespBean addRoles(@RequestBody Role role){
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_" + role.getName());
        }

        if(roleService.save(role)){
            return RespBean.Success("添加成功");
        }
        return RespBean.error("添加失败");
    }


    @ApiModelProperty(value = "删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid){
        if(roleService.removeById(rid)){
            return RespBean.Success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiModelProperty(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }


    @ApiModelProperty(value = "根据角色ID查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid){
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid",rid))
                .stream().map(MenuRole::getMid).collect(Collectors.toList());
    }



    @ApiModelProperty(value = "更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenusRole(Integer rid,Integer[] mids){
        return menuRoleService.updateMenusRoles(rid,mids);
    }



}








