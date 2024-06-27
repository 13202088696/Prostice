package com.itbaizhan.shopping_common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回状态结果
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {
    //正常
    SUCCESS(200,"OK"),
    //系统异常
    SYSTEM_ERROR(500,"系统异常"),
    //业务异常
    PARAMETER_ERROR(601,"参数异常"),
    INSERT_PRODUCT_TYPE_ERROR(601,"插入数据异常"),
    DELETE_PRODUCT_TYPE_ERROR(602,"删除数据异常"),
    UPLOAD_FILE_ERROR(603,"文件上传异常"),
    REGISTER_CODE_ERROR(605,"验证码错误"),
    REGISTER_REPEAT_PHONE_ERROR(606,"手机信息存在"),
    REGISTER_REPEAT_NAME_ERROR(607,"用户名信息存在"),
    ;
    private final Integer code;
    private final String message;
}
