package com.example.freshfoodapi.dto;

import com.example.freshfoodapi.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto extends PageDto{
    private long id;
    private String paymentMethod;
}
