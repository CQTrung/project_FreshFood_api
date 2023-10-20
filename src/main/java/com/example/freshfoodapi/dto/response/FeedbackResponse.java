package com.example.freshfoodapi.dto.response;

import com.example.freshfoodapi.dto.PageDto;
import com.example.freshfoodapi.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class FeedbackResponse extends PageDto {
    private String subjectName;
    private String note;
    private UserDto user;
}
