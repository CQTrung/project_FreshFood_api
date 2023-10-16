package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.ProductDto;
import com.example.freshfoodapi.dto.request.ProductRequest;
import com.example.freshfoodapi.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAll(ProductRequest criteria);
    ProductResponse getProductById(Long id);
    ProductResponse save(ProductDto productDto);
    ProductResponse create(ProductDto productDto);
    boolean delete(Long id);
}
