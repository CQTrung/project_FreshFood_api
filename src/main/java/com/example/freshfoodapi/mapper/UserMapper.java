package com.example.freshfoodapi.mapper;


import com.example.freshfoodapi.dto.UserDto;
import com.example.freshfoodapi.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToEntity(UserDto a);
    UserDto entityToDto(User a);
}

