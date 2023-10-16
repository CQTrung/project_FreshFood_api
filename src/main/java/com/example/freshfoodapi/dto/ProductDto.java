package com.example.freshfoodapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
