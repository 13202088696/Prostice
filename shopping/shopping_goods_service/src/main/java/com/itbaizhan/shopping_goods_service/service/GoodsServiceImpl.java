package com.itbaizhan.shopping_goods_service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.shopping_common.pojo.Goods;
import com.itbaizhan.shopping_common.pojo.GoodsImage;
import com.itbaizhan.shopping_common.pojo.Specification;
import com.itbaizhan.shopping_common.pojo.SpecificationOption;
import com.itbaizhan.shopping_common.service.GoodsService;
import com.itbaizhan.shopping_goods_service.mapper.GoodsImageMapper;
import com.itbaizhan.shopping_goods_service.mapper.GoodsMapper;
import io.netty.util.internal.StringUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

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
        Long goodsId = goods.getId();
        QueryWrapper<GoodsImage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goodsId",goodsId);
        goodsImageMapper.delete(queryWrapper);
        //删除旧规格各项数据
        goodsMapper.deleteGoodsSpecificationOption(goodsId);
        goodsMapper.updateById(goods);
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
    public Goods findById(Long id) {
        return goodsMapper.findById(id);
    }

    @Override
    public void putAway(Long id, Boolean isMarketable) {
        goodsMapper.putAway(id,isMarketable);
    }

    @Override
    public Page<Goods> search(Goods goods, int page, int size) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        if(goods != null && StringUtils.hasText(goods.getGoodsName())){
            queryWrapper.like("goodsName",goods.getGoodsName());
        }
        return goodsMapper.selectPage(new Page<>(page, size), queryWrapper);
    }
}
