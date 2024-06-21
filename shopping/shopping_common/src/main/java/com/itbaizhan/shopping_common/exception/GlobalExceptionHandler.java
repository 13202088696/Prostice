package com.itbaizhan.shopping_common.exception;

import com.itbaizhan.shopping_common.result.BaseResult;
import com.itbaizhan.shopping_common.result.CodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    //处理异常业务
    @ExceptionHandler(BusException.class)
    public BaseResult defaultExceptionHandler(BusException e){
        BaseResult baseResult = new BaseResult(e.getCode(), e.getMsg(), null);
        return baseResult;
    }
    //处理系统业务
    @ExceptionHandler(Exception.class)
    public BaseResult defaultExceptionHandler(HttpServletRequest req,Exception e){
        e.printStackTrace();
        BaseResult baseResult = new BaseResult(CodeEnum.SYSTEM_ERROR.getCode(), CodeEnum.SYSTEM_ERROR.getMessage(), null);
        return baseResult;
    }
}
