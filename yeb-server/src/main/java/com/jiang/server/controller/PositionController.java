package com.jiang.server.controller;


import com.jiang.server.pojo.Position;
import com.jiang.server.service.IPositionService;
import com.jiang.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
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
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;


    @ApiModelProperty(value = "获取所有职位")
    @GetMapping("/")
    public List<Position> getAllPositons(){
        return positionService.list();
    }


    @ApiModelProperty(value = "添加职位信息")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());

        if(positionService.save(position)){
            return RespBean.Success("添加成功");
        }

        return RespBean.error("添加失败");

    }


    @ApiModelProperty(value = "更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if(positionService.updateById(position)){
            return RespBean.Success("更新成功");
        }

        return RespBean.error("更新失败");
    }

    @ApiModelProperty(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if(positionService.removeById(id)){
            return RespBean.Success("删除成功");
        }

        return RespBean.error("删除失败");
    }


    @ApiModelProperty(value = "批量删除职位信息")
    @DeleteMapping("/")
    public RespBean deletePositionByIDs(Integer[] ids){
        if(positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.Success("批量删除成功");
        }

        return RespBean.error("批量删除失败");
    }






}
