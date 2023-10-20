package com.example.freshfoodapi.dto.response;

import com.example.freshfoodapi.constant.Status;
import com.example.freshfoodapi.dto.PageDto;
import com.example.freshfoodapi.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class OrderDetailResponse extends PageDto {
    private long id;
    private Order order;
    private Product product;
    private int quantity;
    private Status status;
    private BigDecimal totalPrice;
}
