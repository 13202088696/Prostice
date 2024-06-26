package com.itbaizhan.shopping_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.shopping_common.pojo.Goods;

//商品服务类
public interface GoodsService {
    //新增商品
    void add(Goods goods);
    //修改商品
    void update(Goods goods);
    //根据id查询商品
    Goods findById(Long id);
    //上架、下架商品
    void putAway(Long id,Boolean isMarketable);
    //分页查询
    Page<Goods> search(Goods goods,int page,int size);
}
