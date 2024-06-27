package com.example.shopping_category_service.sevice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shopping_category_service.mapper.CategoryMapper;
import com.itbaizhan.shopping_common.pojo.Category;
import com.itbaizhan.shopping_common.service.CategoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
@DubboService
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateById(category);
        refreshRedisCategory();
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Category category = categoryMapper.selectById(id);
        category.setStatus(status);
        categoryMapper.updateById(category);
        refreshRedisCategory();
    }

    @Override
    public void delete(Long[] ids) {
        categoryMapper.deleteBatchIds(Arrays.asList(ids));
        refreshRedisCategory();
    }

    @Override
    public Category findById(Long id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public Page<Category> search(Category category, int page, int size) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        if(category != null && StringUtils.hasText(category.getTitle())){
            categoryQueryWrapper.like("title",category.getTitle());
        }
        return categoryMapper.selectPage(new Page<>(page,size),categoryQueryWrapper);
    }

    @Override
    public List<Category> findAll() {
        //1.从redis查询data的list对象
        //1.1获得操作对象redis的list
        ListOperations<String,Category> listOperations = redisTemplate.opsForList();
        //1.2redis获得所有启动的广告
        List<Category> categoryList = listOperations.range("categories", 0, -1);
        //
        if(categoryList !=null && categoryList.size() > 0){
            return categoryList;
        }else {
            QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("status",1);
            List<Category> categories = categoryMapper.selectList(queryWrapper);
            //同步到redis
            listOperations.leftPushAll("categories",categories);
            return categories;
        }

    }

    /**
     * 更新redis广告数据
     */
    public void refreshRedisCategory(){
        //从数据库查询广告
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",1);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        redisTemplate.delete("categories");
        ListOperations<String,Category> listOperations = redisTemplate.opsForList();
        listOperations.leftPushAll("categories",categories);
    }
}
