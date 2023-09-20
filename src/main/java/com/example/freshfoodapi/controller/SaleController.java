package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.service.impl.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/product")
public class SaleController {
    @Autowired
    SaleServiceImpl saleService;
}
