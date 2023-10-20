package com.example.freshfoodapi.mapper;


import com.example.freshfoodapi.dto.OrderDto;
import com.example.freshfoodapi.dto.response.OrderResponse;
import com.example.freshfoodapi.entity.Order;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order dtoToEntity(OrderDto a);
    OrderDto entityToDto(Order a);
    OrderResponse entityToResponse(Order a);

}

