package com.itbaizhan.shopping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.shopping_common.pojo.Goods;
import com.itbaizhan.shopping_common.result.BaseResult;
import com.itbaizhan.shopping_common.service.GoodsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @DubboReference
    private GoodsService goodsService;

    /**
     * 新增商品实体类
     * @param goods 商品
     * @return 返回结果
     */
    @RequestMapping("/add")
    public BaseResult add(@RequestBody Goods goods){
        goodsService.add(goods);
        return BaseResult.ok();
    }

    @PutMapping("/update")
    public BaseResult update(@RequestBody Goods good){
        goodsService.update(good);
        return BaseResult.ok();
    }

    @PutMapping("/uptAway")
    public BaseResult upAway(Long id ,Boolean isMarketable){
        goodsService.putAway(id,isMarketable);
        return BaseResult.ok();
    }

    @GetMapping("/findById")
    public BaseResult<Goods> findById(long id){
        Goods goods = goodsService.findById(id);
        return BaseResult.ok(goods);
    }

    @GetMapping("/search")
    public BaseResult<Page<Goods>> search(Goods goods,int page,int size){
        Page<Goods> goodsPage = goodsService.search(goods, page, size);
        return BaseResult.ok(goodsPage);
    }

}
