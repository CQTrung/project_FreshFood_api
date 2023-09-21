package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.SaleDto;
import com.example.freshfoodapi.properties.CommonProperties;
import com.example.freshfoodapi.service.SaleService;
import com.example.freshfoodapi.service.impl.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1/sale")
public class SaleController {
    @Autowired
    SaleService service;

    @Autowired
    CommonProperties commonProperties;

    @PostMapping(value = "")
    public ResponseEntity<List<SaleDto>> gets(@RequestBody SaleDto criteria, HttpServletRequest request) {
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        List<SaleDto> SaleDtoList = service.findAll(criteria);

        return ResponseEntity.ok(SaleDtoList);
    }

    @GetMapping(value = "/detail")
    public ResponseEntity<?> get(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (id == null) {
            return ResponseEntity.ok("not data");
        }
        SaleDto Sale = service.getsaleById(id);
        if (Objects.isNull(Sale)) {
            return ResponseEntity.ok("not data");
        }
        return ResponseEntity.ok(Sale);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody SaleDto SaleDto, HttpServletRequest request) {
        if (Objects.isNull(SaleDto)) {
            return ResponseEntity.ok("not data");
        }
        SaleDto result = service.save(SaleDto);
        if (!Objects.isNull(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok("not data");
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> delete(@RequestBody SaleDto SaleDto, HttpServletRequest request) {
        if (Objects.isNull(SaleDto)) {
            return ResponseEntity.ok(false);
        }
        boolean result = service.delete(SaleDto.getId());
        if (result) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }

}
