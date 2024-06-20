package com.example.shopping_manager_api.controller;

import com.itbaizhan.shopping_common.pojo.Brand;
import com.itbaizhan.shopping_common.result.BaseResult;
import com.itbaizhan.shopping_common.service.BrandService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @DubboReference
    private BrandService brandService;

    @GetMapping("/findBrandById")
    public BaseResult<Brand> findBrandById(Long id){
        Brand brand = brandService.findById(id);
        return BaseResult.ok(brand);
    }
}
