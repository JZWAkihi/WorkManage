package com.jiang.server.controller;


import com.jiang.server.pojo.Admin;
import com.jiang.server.pojo.Role;
import com.jiang.server.service.IAdminService;
import com.jiang.server.service.IRoleService;
import com.jiang.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;


    @Autowired
    private IRoleService roleService;


    @ApiModelProperty(value = "获取所有操作员")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);
    }



    @ApiModelProperty(value = "更新操作员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin){
        if(adminService.updateById(admin)){
            return RespBean.Success("更新成功");
        }

        return RespBean.error("更新失败");
    }


    @ApiModelProperty(value = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id){
        if(adminService.removeById(id)){
            return RespBean.Success("删除成功");
        }

        return RespBean.error("删除失败");

    }



    @ApiModelProperty(value = "获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.list();
    }



    @ApiModelProperty(value = "更新操作员角色")
    @PutMapping("/role")
    public RespBean updateAdminRoles(Integer adminId,Integer[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }





}
