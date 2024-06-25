package com.itbaizhan.shopping_goods_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.shopping_common.pojo.Goods;
import com.itbaizhan.shopping_common.pojo.GoodsImage;
import com.itbaizhan.shopping_common.pojo.Specification;
import com.itbaizhan.shopping_common.pojo.SpecificationOption;
import com.itbaizhan.shopping_common.service.GoodsService;
import com.itbaizhan.shopping_goods_service.mapper.GoodsImageMapper;
import com.itbaizhan.shopping_goods_service.mapper.GoodsMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@DubboService
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsImageMapper goodsImageMapper;
    @Override
    public void add(Goods goods) {
        //插入是商品数据
        goodsMapper.insert(goods);
        Long goodsId = goods.getId();
        List<GoodsImage> images = goods.getImages();
        for (GoodsImage image : images) {
            image.setGoodsId(goodsId);
            goodsImageMapper.insert(image);
        }

        //插入商品规格各项数据
        //1.获取规格
        List<Specification> specifications = goods.getSpecifications();
        //2.获取规格项
        ArrayList<SpecificationOption> options = new ArrayList<>();
        //便利规格，获取规格项中所有规格项
        for (Specification specification : specifications) {
            options.addAll(specification.getSpecificationOptions());
        }
        //遍历规格项，插入商品_规格数据
        for (SpecificationOption option : options) {
            goodsMapper.addGoodsSpecificationOption(goodsId, option.getId());
        }

    }

    @Override
    public void update(Goods goods) {
        //删除旧照片数据
        //删除旧规格各项数据
        //插入商品数据
        //插入图片数据
        //插入商品——规格项数据
        //遍历规格，获取规格中所有规格项
        //遍历规格插入，插入商品——规格项数据
    }

    @Override
    public void findById(Long id) {

    }

    @Override
    public void putAway(Long id, Boolean isMarketable) {

    }

    @Override
    public Page<Goods> search(Goods goods, int page, int size) {
        return null;
    }
}
