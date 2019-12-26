package com.tensquare.tensquare_base.controller;


import com.tensquare.tensquare_common.entity.Result;
import com.tensquare.tensquare_common.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注解@ControllerAdvice: 控制器增强
 * 注解@ExceptionHandler: 可以处理异常
 */
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
