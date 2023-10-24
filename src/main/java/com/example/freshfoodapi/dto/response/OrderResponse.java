package com.example.freshfoodapi.dto.response;

import com.example.freshfoodapi.constant.Status;
import com.example.freshfoodapi.dto.PageDto;
import com.example.freshfoodapi.dto.ProductDto;
import com.example.freshfoodapi.dto.UserDto;
import com.example.freshfoodapi.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class OrderResponse extends PageDto {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String note;
    private int quantity;
    private BigDecimal unitPrice;
    private List<ProductDto>product;
}
