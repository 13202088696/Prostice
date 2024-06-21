package com.itbaizhan.shopping_common.exception;

import com.itbaizhan.shopping_common.result.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusException extends RuntimeException{
    private Integer code;
    private String msg;
    public BusException(CodeEnum codeEnum){
        this.code =codeEnum.getCode();
        this.msg = codeEnum.getMessage();
    }
}
