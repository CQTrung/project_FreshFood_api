package com.example.freshfoodapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class PageDto {
    //    @JsonIgnore
    private Integer pageSize;
    //    @JsonIgnore
    private Integer pageNumber;
}
