package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.ProductDto;
import com.example.freshfoodapi.dto.request.ProductRequest;
import com.example.freshfoodapi.dto.response.ProductResponse;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1/product/")
public class ProductController extends BaseController{
    @Autowired
    ProductService service;

    @PostMapping(value = "")
    public ResponseEntity<List<ProductResponse>> gets(@RequestBody ProductRequest criteria, HttpServletRequest request) {
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        List<ProductResponse> list = service.getAll(criteria);

        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "save")
    public ResponseEntity<ProductResponse> save(@RequestBody ProductDto productDto, HttpServletRequest request) {
        if (Objects.isNull(productDto)) {
            return null;
        }
        ProductResponse result = service.save(productDto);
        if (!Objects.isNull(result)) {
            return ResponseEntity.ok(result);
        }
        return null;
    }

    @GetMapping(value = "detail")
    public ResponseEntity<ProductResponse> getDetail(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException("400","id invalid");
        }
        ProductResponse productDto = service.getProductById(id);

        if (Objects.isNull(productDto)) {
            throw new BusinessException("product empty");
        }

        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping(value ="delete")
    public ResponseEntity<?> delete(@RequestParam(required = false,value = "id") Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException("400","id invalid");
        }
        boolean result = service.delete(id);
        if(result){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(false);
    }

    @PostMapping(value = "create")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductDto productDto, HttpServletRequest request) {
        if (Objects.isNull(productDto)) {
            return null;
        }
        ProductResponse result = service.create(productDto);
        return ResponseEntity.ok(result);
    }
}
