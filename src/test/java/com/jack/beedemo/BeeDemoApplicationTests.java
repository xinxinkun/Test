package com.jack.beedemo;

import com.jack.beedemo.entity.ProductCategoryEntity;
import com.jack.beedemo.entity.ProductEntity;
import com.jack.beedemo.mapper.ProductCategoryMapper;
import com.jack.beedemo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BeeDemoApplicationTests {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductService productService;
    @Test
    void contextLoads() {
    }

    @Test
    public void testInsert(){
        ProductCategoryEntity entity = new ProductCategoryEntity();
        entity.setParentId(0L);
        entity.setName("Electory");
        entity.setLevel(0);
        entity.setSort(0);
        entity.setDescription("Electory");
        productCategoryMapper.insert(entity);
        entity = new ProductCategoryEntity();
        entity.setParentId(1L).setName("Computer").setLevel(1);
        productCategoryMapper.insert(entity);
        entity = new ProductCategoryEntity();
        entity.setParentId(1L).setName("TV").setLevel(1);
        productCategoryMapper.insert(entity);
        entity = new ProductCategoryEntity();
        entity.setParentId(1L).setName("Camera").setLevel(1);
        productCategoryMapper.insert(entity);
        entity = new ProductCategoryEntity();
        entity.setParentId(1L).setName("CellPhone").setLevel(1);
        productCategoryMapper.insert(entity);
        entity = new ProductCategoryEntity();
        entity.setParentId(0L).setName("Books").setLevel(0);
        productCategoryMapper.insert(entity);
    }

    @Test
    public void testSelect(){
        List<ProductEntity> allProductsByProductCategoryId = productService.getAllProductsByProductCategoryId(1L);
        allProductsByProductCategoryId.forEach(q->{
            System.out.println(q.toString());
        });
    }

    @Test
    public void testSelectPath(){
        List<ProductEntity> allProductsByProductCategoryId = productService.getAllProductsByProductCategoryId(1L);
        allProductsByProductCategoryId.forEach(q->{
            System.out.println(q.toString());
        });
    }



}
