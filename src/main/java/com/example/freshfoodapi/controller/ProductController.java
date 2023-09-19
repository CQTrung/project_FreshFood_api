package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.RestErrorDto;
import com.example.freshfoodapi.entity.Product;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.service.ProductService;
import com.example.freshfoodapi.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

}
