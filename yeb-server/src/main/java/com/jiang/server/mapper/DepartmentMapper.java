package com.jiang.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiang.server.pojo.Department;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
@Component
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartmentsById(Integer id);

    void addDep(Department department);

    /**
     * 删除部门
     * @param department
     */
    void deleteDep(Department department);
}
