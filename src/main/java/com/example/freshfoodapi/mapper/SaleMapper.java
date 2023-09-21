package com.example.freshfoodapi.mapper;

import com.example.freshfoodapi.dto.SaleDto;
import com.example.freshfoodapi.entity.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    Sale dtoToEntity(SaleDto a);
    SaleDto entityToDto(Sale a);
}
