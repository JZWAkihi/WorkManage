package com.jiang.server.controller;


import com.jiang.server.pojo.Department;
import com.jiang.server.service.impl.DepartmentServiceImpl;
import com.jiang.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;


    @ApiModelProperty(value = "获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }


    @ApiModelProperty(value = "添加部门")
    @PostMapping("/")
    public RespBean addDep(@RequestBody Department department) {

        return departmentService.addDep(department);

    }


    @ApiModelProperty(value = "删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDep(@PathVariable Integer id){
        return departmentService.deleteDep(id);
    }




}
