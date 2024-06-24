package com.itbaizhan.shopping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.shopping_common.pojo.Role;
import com.itbaizhan.shopping_common.result.BaseResult;
import com.itbaizhan.shopping_common.service.RoleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @DubboReference
    private RoleService roleService;

    /**
     * 新增角色
     * @param role 对象
     * @return 结果返回
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody Role role){
        roleService.add(role);
        return BaseResult.ok();
    }

    @PutMapping("/update")
    public BaseResult update(@RequestBody Role role){
        roleService.update(role);
        return BaseResult.ok();
    }

    @DeleteMapping("/delete")
    public BaseResult delete(Long rid){
        roleService.delete(rid);
        return BaseResult.ok();
    }

    @GetMapping("/findById")
    public BaseResult<Role> findById(Long rid){
        Role role = roleService.findById(rid);
        return BaseResult.ok(role);
    }

    @GetMapping("/search")
    //@PreAuthorize("hasAnyAuthority('/role/search')")
    public BaseResult<Page<Role>> search(int page,int size){
        Page<Role> rolePage = roleService.search(page, size);
        return BaseResult.ok(rolePage);
    }

    @GetMapping("/findAll")
    public BaseResult<List<Role>> findAdd(){
        List<Role> roleList = roleService.findAll();
        return BaseResult.ok(roleList);
    }

    @PutMapping("/updatePermissionToRole")
    public BaseResult updatePermissionToRole(Long rid,Long[] pids){
        roleService.updatePermissionRole(rid,pids);
        return BaseResult.ok();
    }
}
