package com.example.freshfoodapi.mapper;


import com.example.freshfoodapi.dto.FeedbackDto;
import com.example.freshfoodapi.entity.Feedback;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    Feedback dtoToEntity(FeedbackDto a);
    FeedbackDto entityToDto(Feedback a);
}

