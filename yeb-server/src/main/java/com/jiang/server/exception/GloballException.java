package com.jiang.server.exception;


import com.jiang.server.utils.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
public class GloballException {

    @ExceptionHandler(SQLException.class)
    public RespBean mysqlException(SQLException e){
        if(e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有关联数据，操作失败");
        }
        return RespBean.error("数据库操作异常");
    }

}
