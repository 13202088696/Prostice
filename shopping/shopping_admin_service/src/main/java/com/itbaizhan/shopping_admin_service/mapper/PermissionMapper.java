package com.itbaizhan.shopping_admin_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbaizhan.shopping_common.pojo.Permission;

public interface PermissionMapper extends BaseMapper<Permission> {
    //删除所有角色——权限修改数据
    void deletePermissionAllRole(Long pid);
}
