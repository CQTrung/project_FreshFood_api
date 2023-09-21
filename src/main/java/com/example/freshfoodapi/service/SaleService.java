package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.SaleDto;

import java.util.List;

public interface SaleService {
    SaleDto save(SaleDto coachDto);
    List<SaleDto> findAll(SaleDto criteria);
    SaleDto getsaleById(Long id);
    boolean delete(Long id);
}
