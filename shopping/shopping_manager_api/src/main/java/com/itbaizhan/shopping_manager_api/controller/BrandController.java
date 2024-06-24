package com.itbaizhan.shopping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.shopping_common.pojo.Brand;
import com.itbaizhan.shopping_common.result.BaseResult;
import com.itbaizhan.shopping_common.service.BrandService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @DubboReference
    private BrandService brandService;

    /**
     * 通过id查询品牌
     * @param id 品牌id
     * @return 返回结果
     */
    @GetMapping("/findBrandById")
    public BaseResult<Brand> findBrandById(Long id){
        Brand brand = brandService.findById(id);
        return BaseResult.ok(brand);
    }

    /**
     * 查询所有品牌
     * @return 所有品牌结果
     */
    @GetMapping("/all")
    public BaseResult<List<Brand>> findAll(){
        List<Brand> brands = brandService.findAll();
        return BaseResult.ok(brands);
    }

    /**
     * 新增品牌
     * @param brand 品牌对象
     * @return 执行结果
     */
    @PostMapping("/add")
    public BaseResult add(@RequestBody Brand brand){
        brandService.add(brand);
        return BaseResult.ok();
    }

    /**
     * 修改品牌
     * @param brand 品牌对象
     * @return 执行结果
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody Brand brand){
        brandService.update(brand);
        return BaseResult.ok();
    }

    /**
     * 删除品牌
     * @param id 品牌id
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public BaseResult delete(Long id){
        brandService.delete(id);
        return BaseResult.ok();
    }

    /**
     * 分页搜索
     * @param brand 品牌
     * @param page 页码
     * @param size 数量
     * @return 返回结果
     */
    @GetMapping("/search")
    public BaseResult<Page<Brand>> search(Brand brand,int page,int size){
        Page<Brand> page1 = brandService.search(brand, page, size);
        return BaseResult.ok(page1);
    }
}
