package com.example.freshfoodapi.mapper;


import com.example.freshfoodapi.dto.ImageDto;
import com.example.freshfoodapi.entity.Image;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ImageMapper {
    Image dtoToEntity(ImageDto a);
    ImageDto entityToDto(Image a);
}

