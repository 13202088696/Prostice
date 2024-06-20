package com.itbaizhan.shopping_common.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResult<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> BaseResult<T> ok(){
        return new BaseResult<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMessage(), null);
    }
    public static <T> BaseResult<T> ok(T data){
        return new BaseResult<>(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMessage(), data);
    }
    public static <T> BaseResult<T> error(){
        return new BaseResult<>(CodeEnum.ERROR.getCode(),CodeEnum.ERROR.getMessage(),null);
    }
    public static <T> BaseResult<T> error(T data){
        return new BaseResult<>(CodeEnum.ERROR.getCode(),CodeEnum.ERROR.getMessage(),data);
    }
}
