package com.example.shopping_cart_service.service;

import com.itbaizhan.shopping_common.pojo.CartGoods;
import com.itbaizhan.shopping_common.service.CartService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@DubboService
public class CartServiceImpl implements CartService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void addCart(Long userId, CartGoods cartGoods) {
        //根据id获得购物车
        List<CartGoods> cartList = findCartList(userId);
        //查询购物车是否有该商品，有添加其数量
        for (CartGoods goods : cartList) {
            if(cartGoods.getGoodId().equals(goods.getGoodId())){
                int numNew = goods.getNum() + cartGoods.getNum();
                cartGoods.setNum(numNew);
                redisTemplate.boundHashOps("cartList").put(userId,cartList);
                return;
            }
        }
        //如果购物车没有该商品，将送添加到购物车
        cartList.add(cartGoods);
        redisTemplate.boundHashOps("cartList").put(userId,cartList);
    }

    @Override
    public void handleCart(Long userId, Long goodId, Integer num) {
        //获取用户购物车列表
        List<CartGoods> cartList = findCartList(userId);
        //遍历列表对应商品 && 改变数量
        for (CartGoods cartGoods : cartList) {
            if(goodId.equals(cartGoods.getGoodId())){
                cartGoods.setNum(num);
                break;
            }
        }
        redisTemplate.boundHashOps("cartList").put(userId,cartList);
        //将新的购物车列表保存到redis中
    }

    @Override
    public void deleteCartOption(Long userId, Long goodId) {
        List<CartGoods> cartList = findCartList(userId);
        for (CartGoods cartGoods : cartList) {
            if(goodId.equals(cartGoods.getGoodId())){
                cartList.remove(cartGoods);
                break;
            }
        }
        redisTemplate.boundHashOps("cartList").put(userId,cartList);
    }

    @Override
    public List<CartGoods> findCartList(Long userId) {
        Object cartList = redisTemplate.boundHashOps("cartList").get(userId);
        if(cartList == null ){
            return new ArrayList<CartGoods>();
        }else {
            return (List<CartGoods>)cartList;
        }
    }

    @Override
    public void refreshCartGoods(CartGoods cartGoods) {

    }

    @Override
    public void deleteCartGoods(Long goodId) {

    }
}
