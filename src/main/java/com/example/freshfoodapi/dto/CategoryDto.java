package com.example.freshfoodapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CategoryDto extends PageDto{
    private  long id;
    private  String name;
}
