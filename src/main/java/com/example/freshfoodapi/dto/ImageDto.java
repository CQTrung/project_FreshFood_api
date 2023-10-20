package com.example.freshfoodapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Lob;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto extends PageDto{
    private long id;
    private String name;

    private String type;

    private long size;

    private byte[] data;
}
