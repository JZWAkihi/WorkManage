package com.jiang.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiang.server.pojo.Employee;
import com.jiang.server.utils.RespBean;
import com.jiang.server.utils.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JiangZW
 * @since 2021-05-09
 */
public interface IEmployeeService extends IService<Employee> {

    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    RespBean maxWorkID();

    RespBean addEmp(Employee employee);

    List<Employee> getEmployee(Integer id);
}
