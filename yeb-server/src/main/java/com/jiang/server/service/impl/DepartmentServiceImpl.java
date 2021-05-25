package com.jiang.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiang.server.mapper.DepartmentMapper;
import com.jiang.server.pojo.Department;
import com.jiang.server.service.IDepartmentService;
import com.jiang.server.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsById(-1);
    }

    @Override
    public RespBean addDep(Department department) {

        department.setEnabled(true);
        departmentMapper.addDep(department);
        if(1 == department.getResult()){
            return RespBean.error("添加成功",department);
        }

        return RespBean.error("添加失败");
    }

    @Override
    public RespBean deleteDep(Integer id) {
        Department department = new Department();

        department.setId(id);
        departmentMapper.deleteDep(department);

        Integer result = department.getResult();

        if(-2 == result){
            return RespBean.error("该部门下还有子部门,删除失败");
        }

        if(-1 == result){
            return RespBean.error("该部门下还有员工，删除失败");
        }

        if(1 == result){
            return RespBean.Success("删除成功");
        }

        return RespBean.error("删除失败");
    }
}
