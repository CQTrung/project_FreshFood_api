package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.WarehouseDto;

import java.util.List;

public interface WarehouseService {
    List<WarehouseDto> getAll(WarehouseDto criteria);
    WarehouseDto getWarehouseById(Long id);
    WarehouseDto save(WarehouseDto WarehouseDto);
    boolean delete(Long id);
}
