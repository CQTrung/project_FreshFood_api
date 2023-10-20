package com.example.freshfoodapi.dto.request;

import com.example.freshfoodapi.dto.PageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
//@SuperBuilder
public class ProductRequest extends PageDto {
    private String name;
    private int price;
    private float weight;
    private String madeIn;
    private long categoryId;
    private long saleId;
    private int quantity;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minWeight;
    private Integer maxWeight;
}
