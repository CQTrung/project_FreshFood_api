package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.SaleDto;

import java.util.List;

public interface SaleService {
    List<SaleDto>getAll(SaleDto criteria);
    SaleDto getSaleById(Long id);
    SaleDto save(SaleDto SaleDto);
    boolean delete(Long id);
}
