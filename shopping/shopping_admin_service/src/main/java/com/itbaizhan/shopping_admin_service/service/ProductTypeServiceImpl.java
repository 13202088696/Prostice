package com.itbaizhan.shopping_admin_service.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.shopping_admin_service.mapper.ProductTypeMapper;
import com.itbaizhan.shopping_common.exception.BusException;
import com.itbaizhan.shopping_common.pojo.ProductType;
import com.itbaizhan.shopping_common.result.CodeEnum;
import com.itbaizhan.shopping_common.service.ProductTypeService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@DubboService
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Override
    public void add(ProductType productType) {
        //根据id查询父类
        ProductType productType1 = productTypeMapper.selectById(productType.getParentId());
        if(productType1 == null){
            productType.setLevel(1);
        }else if (productType1.getLevel() <3){
            productType.setLevel(productType1.getLevel() + 1);
        }else if(productType1.getLevel() == 3){
            throw new BusException(CodeEnum.INSERT_PRODUCT_TYPE_ERROR);
        }
        productTypeMapper.insert(productType);
    }

    @Override
    public void update(ProductType productType) {
        ProductType productType1 = productTypeMapper.selectById(productType.getParentId());
        if(productType1 == null){
            productType.setLevel(1);
        }else if (productType1.getLevel() <3){
            productType.setLevel(productType1.getLevel() + 1);
        }else if(productType1.getLevel() == 3){
            throw new BusException(CodeEnum.SYSTEM_ERROR);
        }
        productTypeMapper.updateById(productType);
    }

    @Override
    public ProductType findById(Long id) {
        return productTypeMapper.selectById(id);
    }

    @Override
    public void delete(Long id) {
        QueryWrapper<ProductType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentId",id);
        List<ProductType> productTypes = productTypeMapper.selectList(queryWrapper);
        if(!CollectionUtils.isEmpty(productTypes)){
            throw  new BusException(CodeEnum.DELETE_PRODUCT_TYPE_ERROR);
        }
        productTypeMapper.deleteById(id);
    }

    @Override
    public Page<ProductType> search(ProductType productType, int page, int size) {
        QueryWrapper<ProductType> queryWrapper = new QueryWrapper<>();
        if(productType != null){
            if(StringUtils.hasText(productType.getName())){
                queryWrapper.like("name",productType.getName());
            }
            if(productType.getParentId() != null){
                queryWrapper.eq("parentId",productType.getParentId());
            }
        }
        return productTypeMapper.selectPage(new Page<>(page,size),queryWrapper);
    }

    @Override
    public List<ProductType> findProductionType(ProductType productType) {
        QueryWrapper<ProductType> queryWrapper = new QueryWrapper<>();
        if(productType != null){
            if(StringUtils.hasText(productType.getName())){
                queryWrapper.like("name",productType.getName());
            }
            if(productType.getParentId() != null){
                queryWrapper.eq("parentId",productType.getParentId());
            }
        }
        return productTypeMapper.selectList(queryWrapper);
    }
}
