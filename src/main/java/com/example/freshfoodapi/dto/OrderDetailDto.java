package com.example.freshfoodapi.dto;

import com.example.freshfoodapi.constant.Status;
import com.example.freshfoodapi.entity.Order;
import com.example.freshfoodapi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@SuperBuilder
@AllArgsConstructor
public class OrderDetailDto extends PageDto{
    private long id;
    private long productId;
    private long orderId;
    private int quantity;
    private Status status;
    private BigDecimal totalPrice;
}
