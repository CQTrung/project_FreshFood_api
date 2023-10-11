package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.WarehouseDto;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.service.impl.WarehouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1/warehouse/")
public class WarehouseController extends BaseController{
    @Autowired
    WarehouseServiceImpl service;
    
    @PostMapping(value = "")
    public ResponseEntity<List<WarehouseDto>> gets(@RequestBody WarehouseDto criteria, HttpServletRequest request) {
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        List<WarehouseDto> list = service.getAll(criteria);

        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "save")
    public ResponseEntity<WarehouseDto> save(@RequestBody WarehouseDto WarehouseDto, HttpServletRequest request) {
        if (Objects.isNull(WarehouseDto)) {
            return null;
        }
        WarehouseDto result = service.save(WarehouseDto);
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
        WarehouseDto WarehouseDto = service.getWarehouseById(id);

        if (Objects.isNull(WarehouseDto)) {
            return ResponseEntity.ok("not data");
        }

        return ResponseEntity.ok(WarehouseDto);
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
