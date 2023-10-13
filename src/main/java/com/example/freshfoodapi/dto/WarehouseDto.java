package com.example.freshfoodapi.dto;

import com.example.freshfoodapi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;


import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
public class WarehouseDto extends PageDto{
    private long id;
    private int quantity;
//    private List<Product> productList;
}
