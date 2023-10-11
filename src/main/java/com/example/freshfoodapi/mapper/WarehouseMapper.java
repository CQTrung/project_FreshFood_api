package com.example.freshfoodapi.mapper;


import com.example.freshfoodapi.dto.WarehouseDto;
import com.example.freshfoodapi.entity.Warehouse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    Warehouse dtoToEntity(WarehouseDto a);
    WarehouseDto entityToDto(Warehouse a);
}

