package com.jiang.server.controller;


import com.jiang.server.pojo.Joblevel;
import com.jiang.server.service.impl.JoblevelServiceImpl;
import com.jiang.server.utils.RespBean;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private JoblevelServiceImpl joblevelService;


    @ApiModelProperty(value = "获取所有职称")
    @GetMapping("/")
    public List<Joblevel> getAllJoblevel(){
        return joblevelService.list();
    }


    @ApiModelProperty(value = "添加职称")
    @PostMapping("/")
    public RespBean addJoblevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if(joblevelService.save(joblevel)){
            return RespBean.Success("添加成功");
        }

        return RespBean.error("添加失败");
    }


    @ApiModelProperty(value = "更新职称")
    @PutMapping("/")
    public RespBean updateJoblevel(@RequestBody Joblevel joblevel){
        if(joblevelService.updateById(joblevel)){
            return RespBean.Success("更新成功");
        }

        return RespBean.error("更新失败");
    }

    @ApiModelProperty(value = "删除职称")
    @DeleteMapping("/{id}")
    public RespBean deleteJoblevel(@PathVariable Integer id){
        if(joblevelService.removeById(id)){
            return RespBean.Success("删除成功");
        }

        return RespBean.error("删除失败");
    }

    @ApiModelProperty(value = "删除职称")
    @DeleteMapping("/")
    public RespBean deleteJoblevelByIDs(@PathVariable Integer[] ids){
        if(joblevelService.removeByIds(Arrays.asList(ids))){
            return RespBean.Success("删除成功");
        }

        return RespBean.error("删除失败");
    }





}
