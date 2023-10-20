package com.example.freshfoodapi.dto;

import com.example.freshfoodapi.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@AllArgsConstructor
public class OrderDto extends PageDto{
    private long id;
    private String address;
    private BigDecimal unitPrice;
    private String phone;
    private String firstName;
    private String lastName;
    private long userId;
}
