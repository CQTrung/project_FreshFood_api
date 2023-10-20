package com.example.freshfoodapi.mapper;


import com.example.freshfoodapi.dto.OrderDetailDto;
import com.example.freshfoodapi.dto.response.OrderDetailResponse;
import com.example.freshfoodapi.entity.OrderDetail;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetail dtoToEntity(OrderDetailDto a);
    OrderDetailDto entityToDto(OrderDetail a);
    OrderDetailResponse entityToResponse(OrderDetail a);
}

