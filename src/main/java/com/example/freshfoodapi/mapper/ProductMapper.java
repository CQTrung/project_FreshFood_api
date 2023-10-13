package com.example.freshfoodapi.mapper;


import com.example.freshfoodapi.dto.ProductDto;
import com.example.freshfoodapi.dto.response.ProductResponse;
import com.example.freshfoodapi.entity.Category;
import com.example.freshfoodapi.entity.Product;
import com.example.freshfoodapi.entity.Sale;
import com.example.freshfoodapi.entity.Warehouse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product dtoToEntity(ProductDto a);
    ProductDto entityToDto(Product a);
    ProductResponse entityToResponse(Product a);
}

