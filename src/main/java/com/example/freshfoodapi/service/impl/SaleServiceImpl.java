package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.repository.SaleRepository;
import com.example.freshfoodapi.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    SaleRepository saleRepository;
}
