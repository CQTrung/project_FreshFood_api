package com.example.freshfoodapi.dto.response;

import com.example.freshfoodapi.dto.PageDto;
import com.example.freshfoodapi.entity.*;
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
    private String fullName;
    private String phone;
    private String address;
    private String note;
    private BigDecimal totalPrice;
    private  int status;
    private User user;
    private List<OrderDetail> orderDetails;
    private List<Payment> payments;
}
