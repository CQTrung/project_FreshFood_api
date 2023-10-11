package com.example.freshfoodapi.mapper;


import com.example.freshfoodapi.dto.VoucherDto;
import com.example.freshfoodapi.entity.Voucher;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface VoucherMapper {
    Voucher dtoToEntity(VoucherDto a);
    VoucherDto entityToDto(Voucher a);
}

