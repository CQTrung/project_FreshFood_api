package com.example.freshfoodapi.dto;

import com.example.freshfoodapi.entity.Category;
import com.example.freshfoodapi.entity.OrderDetail;
import com.example.freshfoodapi.entity.Sale;
import com.example.freshfoodapi.entity.Warehouse;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class ProductDto extends PageDto{
    private long id;
    private String name;
    private int price;
    private float weight;
    private String description;
    private String image;
    private String madeIn;
    private long categoryId;
    private long warehouseId;
    private long saleId;
}
