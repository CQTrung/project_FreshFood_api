package com.example.freshfoodapi.dto;

import com.example.freshfoodapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Data
@SuperBuilder

public class FeedbackDto extends PageDto{
    private long id;
    private String subjectName;
    private String note;
    private long userId;
}
