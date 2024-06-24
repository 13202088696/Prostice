package com.itbaizhan.shopping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.shopping_common.pojo.Admin;
import com.itbaizhan.shopping_common.result.BaseResult;
import com.itbaizhan.shopping_common.service.AdminService;
import com.itbaizhan.shopping_manager_api.security.SecurityConfig;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @DubboReference
    private AdminService adminService;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * 新增管理员
     * @param admin 管理员对象
     * @return 执行结果
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody Admin admin){
        String password = admin.getPassword();
        password = encoder.encode(password);
        admin.setPassword(password);
        adminService.add(admin);
        return BaseResult.ok();
    }

    /**
     * 修改管理员
     * @param admin 管理对象
     * @return 执行结果
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody Admin admin){
        String password = admin.getPassword();
        if(StringUtils.hasText(password)){
            password = encoder.encode(password);
            admin.setPassword(password);
        }
        adminService.update(admin);
        return BaseResult.ok();
    }

    /**
     * 删除管理员用户
     * @param aid 用户aid
     * @return 返回结果
     */
    @DeleteMapping("/delete")
    public BaseResult delete(Long aid){
        adminService.delete(aid);
        return BaseResult.ok();
    }

    /**
     * 查询用户，角色、权限
     * @param aid 用户id
     * @return 返回结果
     */
    @GetMapping("/findById")
    public BaseResult<Admin> findById(Long aid){
        Admin admin = adminService.findById(aid);
        return BaseResult.ok(admin);
    }

    /**
     * 分页查询管理员
     * @param page 分页
     * @param size 调试
     * @return 结果
     */
    @GetMapping("/search")
    //@PreAuthorize("hasAnyAuthority('/admin/search')")
    public BaseResult<Page<Admin>> search(int page,int size){
        Page<Admin> adminPage = adminService.search(page, size);
        return BaseResult.ok(adminPage);
    }

    /**
     * 修改角色
     * @param aid   管理员id
     * @param rids 角色id
     * @return 返回结果
     */
    @PutMapping("/updateRoleToAdmin")
    public BaseResult updateRoleToAdmin(Long aid,Long[] rids){
        adminService.updateRoleToAdmin(aid,rids);
        return BaseResult.ok();
    }

    @GetMapping("/getUserName")
    public BaseResult<String> gstUserName(){
        //获得会话对象
        SecurityContext context = SecurityContextHolder.getContext();
        //获得认证对象信息
        Authentication authentication = context.getAuthentication();
        //获得登录用户信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        return BaseResult.ok(username);
    }
}
