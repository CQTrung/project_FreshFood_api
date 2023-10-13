package com.example.freshfoodapi.dto.response;

import com.example.freshfoodapi.dto.PageDto;
import com.example.freshfoodapi.entity.Category;
import com.example.freshfoodapi.entity.OrderDetail;
import com.example.freshfoodapi.entity.Sale;
import com.example.freshfoodapi.entity.Warehouse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
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
    private Category category;
    private Warehouse warehouse;
    private Sale sale;
    private List<OrderDetail> orderDetailList;
}
