package com.jiang.server.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping ("hello")
    public String hello(){
        return "Hello World";
    }


    /***
     * 测试请求权限
     * @return
     */
    @GetMapping("/salary/month/hello")
    public String hello1(){
        return "/salary/month/hello";
    }


    @GetMapping("/employee/base/hello")
    public String hello2(){
        return "/employee/base/hello";
    }


    @GetMapping("/statistics/personnel/hello")
    public String hello3(){
        return "/statistics/personnel/hello";
    }

    @GetMapping("/salary/table/hello")
    public String hello4(){
        return "/salary/table/hello";
    }

    @GetMapping("/employee/basic/hello")
    public String hello5(){
        return "/employee/basic/hello";
    }

}
