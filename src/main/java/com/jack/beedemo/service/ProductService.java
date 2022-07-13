package com.jack.beedemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.beedemo.entity.ProductCategoryEntity;
import com.jack.beedemo.entity.ProductEntity;

import java.util.List;

/**
 * @Author: cx
 * @Date: 2022/7/13 16:54
 */
public interface ProductService extends IService<ProductEntity> {
    List<ProductEntity>  getAllProductsByProductCategoryId(Long productCategoryId);
    String getCategoryPathByProduct(Long productId);
}
