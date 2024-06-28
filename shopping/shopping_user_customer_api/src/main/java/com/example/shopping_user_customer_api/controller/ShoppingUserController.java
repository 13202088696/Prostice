package com.example.shopping_user_customer_api.controller;

import com.itbaizhan.shopping_common.pojo.ShoppingUser;
import com.itbaizhan.shopping_common.result.BaseResult;
import com.itbaizhan.shopping_common.service.MessageService;
import com.itbaizhan.shopping_common.service.ShoppingUserService;
import com.itbaizhan.shopping_common.util.RandomUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/shoppingUser")
public class ShoppingUserController {
    @DubboReference
    private ShoppingUserService shoppingUserService;
    @DubboReference
    private MessageService messageService;

    @GetMapping("/sendMessage")
    public BaseResult sendMessage(String phone) throws ExecutionException, InterruptedException {
        String checkCode = RandomUtil.buildCheckCode(4);
        BaseResult result = messageService.sendMessage(phone, checkCode);
        if (200 == result.getCode()){
            shoppingUserService.saveRegisterCheckCode(phone,checkCode);
            return BaseResult.ok();
        }else {
            return result;
        }
    }

    @GetMapping("/loginCheckCode")
    public BaseResult register(String phone,String checkCode){
        shoppingUserService.registerCheckCode(phone,checkCode);
        return BaseResult.ok();
    }

    @PostMapping("/register")
    public BaseResult register(@RequestBody ShoppingUser shoppingUser){
        shoppingUserService.register(shoppingUser);
        return BaseResult.ok();
    }

    @PostMapping("/loginPassword")
    public BaseResult loginPassword(@RequestBody ShoppingUser shoppingUser){
        shoppingUserService.loginPassword(shoppingUser.getUsername(),shoppingUser.getPassword());
        return BaseResult.ok();
    }

    @GetMapping("/sendLoginCheckCode")
    public BaseResult sendLoginCheckCode(String phone) throws ExecutionException, InterruptedException {
        shoppingUserService.checkPhone(phone);
        String checkCode = RandomUtil.buildCheckCode(4);
        BaseResult result = messageService.sendMessage(phone, checkCode);
        if(200 == result.getCode()){
            return BaseResult.ok();
        }else {
            return result;
        }
    }

    @PostMapping("/loginCheckCode")
    public BaseResult loginCheckCode(String phone,String checkCode){
        shoppingUserService.loginCheckCode(phone,checkCode);
        return BaseResult.ok();
    }


    
}
