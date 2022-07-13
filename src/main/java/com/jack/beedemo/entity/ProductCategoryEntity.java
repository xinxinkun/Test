package com.jack.beedemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @Author: cx
 * @Date: 2022/7/13 16:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_product_category")
public class ProductCategoryEntity {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO, value = "id")
    private Long id;
    private Long parentId;
    private String name;
    private Integer level;
    private Integer sort;
    private String description;
}
