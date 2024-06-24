package com.itbaizhan.shopping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.shopping_common.pojo.ProductType;
import com.itbaizhan.shopping_common.result.BaseResult;
import com.itbaizhan.shopping_common.service.ProductTypeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productType")
public class ProductTypeController {
    @DubboReference
    private ProductTypeService productTypeService;

    /**
     * 新增产品
     * @param productType 产品了些
     * @return 返回结果
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody ProductType productType){
        productTypeService.add(productType);
        return BaseResult.ok();
    }

    /**
     * 修改产品
     * @param productType 产品类型
     * @return 返回结果
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody ProductType productType){
        productTypeService.update(productType);
        return BaseResult.ok();
    }

    @DeleteMapping("/delete")
    public BaseResult delete(Long id){
        productTypeService.delete(id);
        return BaseResult.ok();
    }

    /**
     * 根据id查询商品类型
     * @param id 商品id
     * @return 查询结果
     */
    @GetMapping("/findById")
    public BaseResult<ProductType> findById(Long id){
        ProductType productType = productTypeService.findById(id);
        return BaseResult.ok(productType);
    }

    /**
     * 分页查询
     * @param productType 产品
     * @param page 页数
     * @param size 数量
     * @return 返回结果
     */
    @GetMapping("/search")
    public BaseResult<Page<ProductType>> search(ProductType productType,int page,int size){
        Page<ProductType> page1 = productTypeService.search(productType, page, size);
        return BaseResult.ok(page1);
    }

    /**
     * 查询所有
     * @param productType 产品
     * @return 返回结果
     */
    @GetMapping("/findProductType")
    public BaseResult<List<ProductType>> findProductType(ProductType productType){
        List<ProductType> productionType1 = productTypeService.findProductionType(productType);
        return BaseResult.ok(productionType1);
    }

    /**
     * 根据父类id查询商品列表
     * @param parentId 父类id
     * @return 查询结果
     */
    @GetMapping("/productionType1")
    public BaseResult<List<ProductType>> productionType1(Long parentId){
        ProductType productType = new ProductType();
        productType.setParentId(parentId);
        List<ProductType> productionType1 = productTypeService.findProductionType(productType);
        return BaseResult.ok(productionType1);
    }
}
