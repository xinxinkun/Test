package com.jack.beedemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jack.beedemo.entity.ProductEntity;
import org.springframework.stereotype.Repository;

/**
 * @Author: cx
 * @Date: 2022/7/13 16:36
 */
@Repository
public interface ProductMapper  extends BaseMapper<ProductEntity> {
}
