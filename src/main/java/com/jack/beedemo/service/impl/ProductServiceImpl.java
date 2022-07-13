package com.jack.beedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.beedemo.entity.ProductCategoryEntity;
import com.jack.beedemo.entity.ProductEntity;
import com.jack.beedemo.mapper.ProductCategoryMapper;
import com.jack.beedemo.mapper.ProductMapper;
import com.jack.beedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: cx
 * @Date: 2022/7/13 16:55
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    @Override
    public List<ProductEntity> getAllProductsByProductCategoryId(Long productCategoryId) {
        ProductCategoryEntity entity = productCategoryMapper.selectById(productCategoryId);
        if(entity == null ){
            return Collections.emptyList();
        }
        Set<Long> productCategoryIds = new HashSet<>();
        productCategoryIds.add(productCategoryId);

        List<ProductCategoryEntity> allCategorys = productCategoryMapper.selectList(new LambdaQueryWrapper<>());
        Map<Long, List<ProductCategoryEntity>> map = allCategorys.stream().collect(
                Collectors.groupingBy(
                        ProductCategoryEntity -> ProductCategoryEntity.getParentId()
                ));
        Set<Long> childIds = map.get(productCategoryId).stream().map(ProductCategoryEntity::getId).collect(Collectors.toSet());
        productCategoryIds.addAll(childIds);
        getChilds(childIds,map,productCategoryIds);
        //查询商品
        LambdaQueryWrapper<ProductEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(productCategoryIds.size()==0){
            return Collections.emptyList();
        }
        lambdaQueryWrapper.in(ProductEntity::getProductCategoryId, productCategoryIds);
        List<ProductEntity> result = list(lambdaQueryWrapper);
        return result;
    }

    @Override
    public String getCategoryPathByProduct(Long productId) {
        ProductEntity productEntity = getById(productId);
        if(productEntity == null ){
            return "";
        }
        Set<Long> parentIds = new HashSet<>();
        Long productCategoryId = productEntity.getProductCategoryId();
        parentIds.add(productCategoryId);
        List<ProductCategoryEntity> allCategorys = productCategoryMapper.selectList(new LambdaQueryWrapper<>());
        Map<Long, ProductCategoryEntity> map = allCategorys.stream().collect(Collectors.toMap(ProductCategoryEntity::getId,Function.identity()));
        getParentId( parentIds,map,productCategoryId);

        String path = "";
        for(Long id: parentIds){
            path+="|"+id;
        }
        return path;
    }

    private void getParentId(Set<Long> parentIds, Map<Long, ProductCategoryEntity> map, Long productCategoryId) {
        ProductCategoryEntity entity = map.get(productCategoryId);
        if(entity == null){
            return;
        }
        Long parentId = entity.getParentId();
        parentIds.add(parentId);
        getParentId(parentIds,map,parentId);
    }


    private void getChilds(Set<Long> childIds, Map<Long, List<ProductCategoryEntity>> map, Set<Long> productCategoryIds) {
        for (Long id : childIds) {
            List<ProductCategoryEntity> list = map.get(id);
            if(list == null || list.size()==0){
                continue;
            }
            Set<Long> ids =list.stream().map(ProductCategoryEntity::getId).collect(Collectors.toSet());
            if (ids.isEmpty()) {
                continue;
            }
            productCategoryIds.addAll(ids);
            getChilds(ids, map, productCategoryIds);
        }
    }

}
