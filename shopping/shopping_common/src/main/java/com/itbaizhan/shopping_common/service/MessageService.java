package com.itbaizhan.shopping_common.service;

import com.itbaizhan.shopping_common.result.BaseResult;

import java.util.concurrent.ExecutionException;

public interface MessageService {

    /**
     *
     * 验证码
     * @param phoneNumber 手机号
     * @param code 验证码
     * @return 返回结果
     */
    BaseResult sendMessage(String phoneNumber, String code) throws ExecutionException, InterruptedException;
}
