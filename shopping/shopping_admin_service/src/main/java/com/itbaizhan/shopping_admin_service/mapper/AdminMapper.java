package com.itbaizhan.shopping_admin_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbaizhan.shopping_common.pojo.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper extends BaseMapper<Admin> {
    //删除管理员所有角色
    void deleteAdminAllRole(Long aid);

    //查询role角色、权限
    Admin findById(Long aid);

    //给管理员添加角色
    void addRoleToAdmin(@Param("aid") Long aid,@Param("rid") Long rid);
}
