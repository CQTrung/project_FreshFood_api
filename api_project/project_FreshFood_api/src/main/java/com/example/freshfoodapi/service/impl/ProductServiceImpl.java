package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.dto.ProductDto;
import com.example.freshfoodapi.dto.RestErrorDto;
import com.example.freshfoodapi.entity.Product;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.repository.ProductRepository;
import com.example.freshfoodapi.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

}
