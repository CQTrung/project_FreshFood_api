package com.example.freshfoodapi.dto.response;

import com.example.freshfoodapi.dto.CategoryDto;
import com.example.freshfoodapi.dto.PageDto;
import com.example.freshfoodapi.dto.SaleDto;
import com.example.freshfoodapi.entity.Category;
import com.example.freshfoodapi.entity.OrderDetail;
import com.example.freshfoodapi.entity.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class ProductResponse extends PageDto {
    private long id;
    private String name;
    private int price;
    private float weight;
    private String description;
    private String image;
    private String madeIn;
    private CategoryDto category;
    private SaleDto sale;
}
