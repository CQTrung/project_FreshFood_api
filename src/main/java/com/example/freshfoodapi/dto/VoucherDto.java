package com.example.freshfoodapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
public class VoucherDto extends PageDto{
    private long id;
    private String voucherCode;
    private int discount;
    private Date startDay;
    private Date endDay;
    private int status;
}
