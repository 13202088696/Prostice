package com.itbaizhan.shopping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.shopping_common.pojo.Admin;
import com.itbaizhan.shopping_common.result.BaseResult;
import com.itbaizhan.shopping_common.service.AdminService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @DubboReference
    private AdminService adminService;

    /**
     * 新增管理员
     * @param admin 管理员对象
     * @return 执行结果
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody Admin admin){
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
}
