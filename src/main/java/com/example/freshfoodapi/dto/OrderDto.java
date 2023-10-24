package com.example.freshfoodapi.dto;

import com.example.freshfoodapi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
public class OrderDto extends PageDto{
    private long id;
    private String address;
    private String phone;
    private String firstName;
    private String lastName;
    private String note;
    private int quantity;
    private BigDecimal unitPrice;
    private List<Long> productId;
}
