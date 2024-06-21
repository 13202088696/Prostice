package com.itbaizhan.shopping_admin_service.service;

import com.itbaizhan.shopping_admin_service.mapper.AdminMapper;
import com.itbaizhan.shopping_common.pojo.Admin;
import com.itbaizhan.shopping_common.service.AdminService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

@DubboService
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public void add(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public void update(Admin admin) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Admin findById(Long id) {
        return null;
    }

    @Override
    public Page<Admin> search(int page, int size) {
        return null;
    }

    @Override
    public void updateRoleToAdmin(Long aid, Long[] rids) {

    }
}
