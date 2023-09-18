package com.example.freshfoodapi.dto;

import com.example.freshfoodapi.entity.Category;
import com.example.freshfoodapi.entity.OrderDetail;
import com.example.freshfoodapi.entity.Sale;
import com.example.freshfoodapi.entity.Warehouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ProductDto extends PageDto{
    private long id;
    private String productName;
    private BigDecimal price;
    private float weight;
    private String description;
    private String image;
    private String madeIn;
    private Warehouse warehouse;
    private Sale sale;
    private List<Category> categoryList;
    private List<OrderDetail> orderDetailList;

}
