package com.itbaizhan.shopping_admin_service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.itbaizhan.shopping_admin_service.mapper")
@EnableDiscoveryClient
@EnableDubbo
public class ShoppingAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingAdminServiceApplication.class, args);
    }

}
