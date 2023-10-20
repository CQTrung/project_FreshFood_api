package com.example.freshfoodapi.mapper;


import com.example.freshfoodapi.dto.PaymentDto;
import com.example.freshfoodapi.entity.Payment;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment dtoToEntity(PaymentDto a);
    PaymentDto entityToDto(Payment a);
}

