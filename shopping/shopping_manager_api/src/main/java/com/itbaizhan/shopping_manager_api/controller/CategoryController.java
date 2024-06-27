package com.itbaizhan.shopping_manager_api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbaizhan.shopping_common.pojo.Category;
import com.itbaizhan.shopping_common.result.BaseResult;
import com.itbaizhan.shopping_common.service.CategoryService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @DubboReference
    private CategoryService categoryService;

    @GetMapping("/search")
    public BaseResult<Page<Category>> search(Category category,int page,int size){
        Page<Category> page1 = categoryService.search(category, page, size);
        return BaseResult.ok(page1);
    }

    @PostMapping("/add")
    public BaseResult add(@RequestBody Category category){
        categoryService.add(category);
        return BaseResult.ok();
    }


    @PutMapping("/update")
    public BaseResult update(@RequestBody Category category){
        categoryService.update(category);

        return BaseResult.ok();
    }

    @PutMapping("/updateStatus")
    public BaseResult updateStatus(@RequestBody Long id,Integer status){
        categoryService.updateStatus(id,status);
        return BaseResult.ok();
    }

    @GetMapping("/findById")
    public BaseResult<Category> findById(Long id){
        Category category = categoryService.findById(id);
        return BaseResult.ok(category);
    }

    @DeleteMapping("/date")
    public BaseResult delete(Long[] ids){
        categoryService.delete(ids);
        return BaseResult.ok();
    }
}
