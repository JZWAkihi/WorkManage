package com.jiang.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.google.common.util.concurrent.ExecutionList;
import com.jiang.server.pojo.*;
import com.jiang.server.service.IEmployeeEcService;
import com.jiang.server.service.IPositionService;
import com.jiang.server.service.impl.*;
import com.jiang.server.utils.RespBean;
import com.jiang.server.utils.RespPageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.ss.formula.functions.Na;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
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
@RequestMapping("/system/basic/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;


    @Autowired
    private PoliticsStatusServiceImpl politicsStatusService;


    @Autowired
    private JoblevelServiceImpl joblevelService;


    @Autowired
    private NationServiceImpl nationService;


    @Autowired
    private PositionServiceImpl positionService;


    @Autowired
    private DepartmentServiceImpl departmentService;


    @ApiModelProperty(value = "获取所有员工")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1")Integer currentPage,
                                    @RequestParam(defaultValue = "10")Integer size,
                                    Employee employee,
                                    LocalDate[] beginDateScope){


        return employeeService.getEmployeeByPage(currentPage,size,employee,beginDateScope);


    }


    @ApiModelProperty(value = "获取所有政治面貌")
    @GetMapping("/paliticsstatus")
    public List<PoliticsStatus> getAllPoliticsStatus(){
        return politicsStatusService.list();
    }

    @ApiModelProperty(value = "获取所有职称")
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJoblevel(){
        return joblevelService.list();
    }


    @ApiModelProperty(value = "获取所有民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return nationService.list();
    }

    @ApiModelProperty(value = "获取所有职位")
    @GetMapping("/positions")
    public List<Position> getAllpositions(){
        return positionService.list();
    }


    @ApiModelProperty(value = "获取所有部门")
    @GetMapping("/deps")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartments();
    }


    @ApiModelProperty(value = "获取工号")
    @GetMapping("/maxWorkID")
    public RespBean maxWordID(){
        return employeeService.maxWorkID();
    }


    @ApiModelProperty(value = "添加员工")
    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee employee){
        return employeeService.addEmp(employee);
    }

    @ApiModelProperty(value = "更新员工")
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee){
        if(employeeService.updateById(employee)){
            return RespBean.Success("更新成功");
        }

        return RespBean.error("更新失败");
    }


    @ApiModelProperty(value = "删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id){
        if(employeeService.removeById(id)){
            return RespBean.Success("删除成功");
        }

        return RespBean.error("删除失败");
    }


    @ApiModelProperty(value = "导入导出数据")
    @GetMapping(value = "/export",produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response){
        List<Employee> employeeExport = employeeService.getEmployee(null);
        ExportParams exportParams = new ExportParams("员工表","员工表", ExcelType.HSSF);

        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Employee.class, employeeExport);
        ServletOutputStream outputStream = null;
        try {
            //流形式
            response.setHeader("content-type","application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition","attachment;filename=" + URLEncoder.encode("员工表","UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if(null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }


    @ApiModelProperty(value = "导入数据")
    @PostMapping("/import")
    public RespBean importEmployee(MultipartFile file){
        ImportParams params = new ImportParams();

        //去掉
        params.setTitleRows(1);
        List<Nation> nationList = nationService.list();
        List<PoliticsStatus> politicsStatuses = politicsStatusService.list();
        List<Position> positions = positionService.list();
        List<Department> departments = departmentService.list();
        List<Joblevel> joblevels = joblevelService.list();
        try {
            List<Employee> list = ExcelImportUtil.importExcel(file.getInputStream(),Employee.class,params);

            list.forEach(employee -> {
                employee.setNationId(nationList.get(nationList.indexOf(new Nation(employee.getNation().getName()))).getId());
                //政治面貌id
                employee.setPoliticId(politicsStatuses.get(politicsStatuses.indexOf(new PoliticsStatus(employee.getPoliticsStatus().getName()))).getId());
                //部门id
                employee.setDepartmentId(departments.get(departments.indexOf(new Department(employee.getDepartment().getName()))).getId());
                //职称id
                employee.setJobLevelId(joblevels.get(joblevels.indexOf(new Joblevel(employee.getJoblevel().getName()))).getId());
                //职位id
                employee.setPosId(positions.get(positions.indexOf(new Position(employee.getPosition().getName()))).getId());

            });

            if(employeeService.saveBatch(list)){
                return RespBean.Success("导入成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败");
    }






}
