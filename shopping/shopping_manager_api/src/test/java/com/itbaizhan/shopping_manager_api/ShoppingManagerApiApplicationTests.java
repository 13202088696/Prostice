package com.itbaizhan.shopping_manager_api;

import com.itbaizhan.shopping_common.pojo.Admin;
import com.itbaizhan.shopping_common.pojo.Permission;
import com.itbaizhan.shopping_common.service.AdminService;
import org.apache.dubbo.config.annotation.DubboService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class ShoppingManagerApiApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminService adminService;
    @Test
    void contextLoads() {
        String encode = passwordEncoder.encode("admin");
        System.out.println("enode:"+encode);
        List<Permission> allPermission = adminService.findAllPermission("baizhan");
        Admin baizhan = adminService.findByAdminName("baizhan123");
        System.out.println(baizhan);
        for (Permission permission : allPermission) {
            System.out.println(permission);
        }
    }

}
