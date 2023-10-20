package com.example.freshfoodapi.dto;


import com.example.freshfoodapi.entity.Voucher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Data
@SuperBuilder
@AllArgsConstructor
public class UserDto extends PageDto{
    private long id;
    private String username;
    private String email;
    private String tel;
    private int point;
}
