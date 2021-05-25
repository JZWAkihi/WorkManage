package com.jiang.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.server.pojo.Department;
import com.jiang.server.utils.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
public interface IDepartmentService extends IService<Department> {


    List<Department> getAllDepartments();

    /**
     * 添加部门
     * @param department
     * @return
     */
    RespBean addDep(Department department);

    /***
     * 删除部门
     * @param id
     * @return
     */
    RespBean deleteDep(Integer id);
}
