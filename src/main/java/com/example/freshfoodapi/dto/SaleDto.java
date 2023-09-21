package com.example.freshfoodapi.dto;

import com.example.freshfoodapi.entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class SaleDto extends PageDto{
    private long id;
    private String persent;
    private String codeSale;
    private Date startDay;
    private Date endDay;
    private List<Product> productList;
}
