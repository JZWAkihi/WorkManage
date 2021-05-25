package com.jiang.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiang.server.pojo.Employee;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
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
public interface EmployeeMapper extends BaseMapper<Employee> {

    IPage<Employee> getEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee,@Param("beginDateScope") LocalDate[] beginDateScope);

    List<Employee> getEmployee(Integer id);
}
