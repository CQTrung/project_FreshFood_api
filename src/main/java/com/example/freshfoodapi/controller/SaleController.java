package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.SaleDto;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.service.impl.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1/sale/")
public class SaleController extends BaseController{
    @Autowired
    SaleServiceImpl service;

    @PostMapping(value = "")
    public ResponseEntity<List<SaleDto>> gets(@RequestBody SaleDto criteria, HttpServletRequest request) {
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        List<SaleDto> list = service.getAll(criteria);

        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "save")
    public ResponseEntity<SaleDto> save(@RequestBody SaleDto saleDto, HttpServletRequest request) {
        if (Objects.isNull(saleDto)) {
            return null;
        }
        SaleDto result = service.save(saleDto);
        if (!Objects.isNull(result)) {
            return ResponseEntity.ok(result);
        }
        return null;
    }

    @GetMapping(value = "detail")
    public ResponseEntity<?> getDetail(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException("400","id invalid");
        }
        SaleDto saleDto = service.getSaleById(id);

        if (Objects.isNull(saleDto)) {
            return ResponseEntity.ok("not data");
        }

        return ResponseEntity.ok(saleDto);
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
}
