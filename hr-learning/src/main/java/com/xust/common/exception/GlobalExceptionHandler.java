package com.xust.common.exception;

import com.xust.common.util.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public CommonResult sqlException(SQLException e){
        if(e instanceof SQLIntegrityConstraintViolationException){
            return CommonResult.error("该数据有关联，操作失败！");
        }
        return CommonResult.error("数据库异常，操作失败！");
    }
}
