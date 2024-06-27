package com.itbaizhan.shopping_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.shopping_common.pojo.Category;

import java.util.List;

//广告服务类
public interface CategoryService {
    //增加
    void add(Category category);
    //修改
    void update(Category category);
    //修改状态
    void updateStatus(Long id,Integer status);
    //删除
    void delete(Long[] ids);
    //查询
    Category findById(Long id);
    //分页搜索
    Page <Category> search(Category category, int page,int size);
    //查询
    List<Category> findAll();
}
