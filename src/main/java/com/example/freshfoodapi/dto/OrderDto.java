package com.example.freshfoodapi.dto;

import com.example.freshfoodapi.entity.OrderDetail;
import com.example.freshfoodapi.entity.Payment;
import com.example.freshfoodapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder

public class OrderDto extends PageDto{
    private long id;
    private String address;
    private BigDecimal totalPrice;
    private  int status;
    private User user;
    private List<OrderDetail> orderDetails;
    private List<Payment> payments;
}
