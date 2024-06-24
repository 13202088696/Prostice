package com.itbaizhan.shopping_admin_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbaizhan.shopping_common.pojo.Specification;

import java.util.List;

public interface specificationMapper extends BaseMapper<Specification> {

    Specification findById(Long id);

    List<Specification> findByProductTypeId(Long productTypeId);
}
