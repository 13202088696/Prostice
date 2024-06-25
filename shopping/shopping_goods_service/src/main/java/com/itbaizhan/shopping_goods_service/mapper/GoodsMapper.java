package com.itbaizhan.shopping_goods_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbaizhan.shopping_common.pojo.Goods;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper extends BaseMapper<Goods> {
    // 添加商品_规格项数据
    void addGoodsSpecificationOption(@Param("gid") Long gid, @Param("optionId")Long optionId);
    //删除是商品下所有规格
    void deleteGoodsSpecificationOption(Long gid);
    //商品上下架
    void putAway(@Param("id") Long id,@Param("isMarketable") Boolean isMarketable);
    //根据id查询goods
    Goods findById(Long id);
}
