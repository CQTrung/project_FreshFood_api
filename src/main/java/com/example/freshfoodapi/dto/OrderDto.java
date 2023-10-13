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
@AllArgsConstructor
public class OrderDto extends PageDto{
    private long id;
    private String address;
    private BigDecimal totalPrice;
    private String phone;
    private String fullName;
    private  int status;
    private long userId;
//    private List<OrderDetail> orderDetails;
//    private List<Payment> payments;
}
