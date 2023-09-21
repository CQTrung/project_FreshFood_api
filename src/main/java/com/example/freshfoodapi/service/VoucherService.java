package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.VoucherDto;

import java.util.List;

public interface VoucherService {
    VoucherDto save(VoucherDto coachDto);
    List<VoucherDto> findAll(VoucherDto criteria);
    VoucherDto getVoucherById(Long id);
    boolean delete(Long id);
}
