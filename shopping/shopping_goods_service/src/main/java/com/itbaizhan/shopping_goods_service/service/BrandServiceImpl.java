package com.itbaizhan.shopping_goods_service.service;

import com.itbaizhan.shopping_common.exception.BusException;
import com.itbaizhan.shopping_common.result.CodeEnum;
import com.itbaizhan.shopping_goods_service.mapper.BrandMapper;
import com.itbaizhan.shopping_common.pojo.Brand;
import com.itbaizhan.shopping_common.service.BrandService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@DubboService
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * id查询方法
     * @param id
     * @return 查询的商品信息
     */
    @Override
    public Brand findById(Long id) {
        if(id == 0){int i =1/0; }else if(id == -1){ throw new BusException(CodeEnum.PARAMETER_ERROR); }
        return brandMapper.selectById(id);
    }
}
