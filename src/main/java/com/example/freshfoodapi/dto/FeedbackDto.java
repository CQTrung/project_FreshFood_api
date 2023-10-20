package com.example.freshfoodapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
public class FeedbackDto extends PageDto{
    private long id;
    private String subjectName;
    private String note;
    private long userId;
}
