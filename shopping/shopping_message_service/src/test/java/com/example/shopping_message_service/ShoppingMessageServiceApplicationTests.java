package com.example.shopping_message_service;

import com.itbaizhan.shopping_common.result.BaseResult;
import com.itbaizhan.shopping_common.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class ShoppingMessageServiceApplicationTests {

    @Autowired
    private MessageService messageService;
    @Test
    void contextLoads() throws ExecutionException, InterruptedException {
        BaseResult sendMessage = messageService.sendMessage("13202088696", "1234");
        System.out.println(sendMessage);
    }

}
