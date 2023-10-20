package com.example.freshfoodapi.mapper;


import com.example.freshfoodapi.dto.CategoryDto;
import com.example.freshfoodapi.entity.Category;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category dtoToEntity(CategoryDto a);
    CategoryDto entityToDto(Category a);
}

