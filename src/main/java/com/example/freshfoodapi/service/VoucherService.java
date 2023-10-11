package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.VoucherDto;

import java.util.List;

public interface VoucherService {
    List<VoucherDto> getAll(VoucherDto criteria);
    VoucherDto getVoucherById(Long id);
    VoucherDto save(VoucherDto VoucherDto);
    boolean delete(Long id);
}
