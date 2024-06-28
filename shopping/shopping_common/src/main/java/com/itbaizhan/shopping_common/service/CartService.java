package com.itbaizhan.shopping_common.service;

import com.itbaizhan.shopping_common.pojo.CartGoods;

import java.util.List;

public interface CartService {
    //新增商品购物车
    void addCart(Long userId, CartGoods cartGoods);
    //修改商品购品数量
    void handleCart(Long userId,Long goodId,Integer num);
    //删除商品购物车
    void deleteCartOption(Long userId,Long goodId);
    //获取用户购物车
    List<CartGoods> findCartList(Long userId);
    //更新redis中的商品数据，在管理员更新商品后执行
     void refreshCartGoods(CartGoods cartGoods);
    //删除redis的商品数据，在管理员下架都运行
    void deleteCartGoods(Long goodId);
}
