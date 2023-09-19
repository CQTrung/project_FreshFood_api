package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.repository.WarehouseRepository;
import com.example.freshfoodapi.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;
}
