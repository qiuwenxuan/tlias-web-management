package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 **/
@RestControllerAdvice // @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 表示捕获所有异常
    public Result exception(Exception e) {
        e.printStackTrace();
        return Result.error("对不起，操作失败，请联系管理员！");
    }
}
