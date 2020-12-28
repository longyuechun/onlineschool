package com.rulaifozu.onlineschool.config;

import com.rulaifozu.onlineschool.pojo.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice
public class MyControllerAdvice {


    @ResponseBody
    @ExceptionHandler(value = SQLException.class)
    public Result sqlHandler(Exception ex) {
        return Result.error(501,ex.getMessage());
    }


    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    /*@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result errorHandler(Exception ex) {
        return Result.error(500,ex.getMessage());
    }
*/
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result sqlHandler(MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();
        if (result.hasErrors()){
            return Result.error(508,result.getFieldError()==null ? "请求参数有误":result.getFieldError().getDefaultMessage());
        }
        return Result.error();
    }
}