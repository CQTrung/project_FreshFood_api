package com.example.freshfoodapi.dto;

import com.example.freshfoodapi.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class VoucherDto extends PageDto{
    private long id;
    private String voucherCode;
    private Float value;
    private Date startDay;
    private Date endDay;
    private List<User> userList;
}
