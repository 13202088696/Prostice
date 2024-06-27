package com.itbaizhan.shopping_common.service;

import com.itbaizhan.shopping_common.pojo.ShoppingUser;

/*商城用户服务*/
public interface ShoppingUserService {
    // 注册先redis保存少验证码
    void saveRegisterCheckCode(String phone,String checkCode);
    // 注册时手机号+验证码
    void registerCheckCode(String phone,String checkCode);
    // 用户注册
    void register(ShoppingUser shoppingUser);
    // 用户名密码登录
    void loginPassword(String username,String password);
    // 登录是向redis保存手机号和验证码
    void saveLoginCheckCode(String phone,String checkCode);
    //手机号登录验证码登录
    void loginCheckCode(String phone,String checkCode);
    //获得用户名
    String getName(String token);
    //根据id获得用户
    ShoppingUser getLoginUser(Long id);
}
