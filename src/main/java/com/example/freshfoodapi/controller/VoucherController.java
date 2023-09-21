package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.VoucherDto;
import com.example.freshfoodapi.properties.CommonProperties;
import com.example.freshfoodapi.service.VoucherService;
import com.example.freshfoodapi.service.VoucherService;
import com.example.freshfoodapi.service.impl.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1/voucher")
public class VoucherController {
    @Autowired
    VoucherService service;

    @Autowired
    CommonProperties commonProperties;

    @PostMapping(value = "")
    public ResponseEntity<List<VoucherDto>> gets(@RequestBody VoucherDto criteria, HttpServletRequest request) {
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        List<VoucherDto> VoucherDtoList = service.findAll(criteria);

        return ResponseEntity.ok(VoucherDtoList);
    }

    @GetMapping(value = "/detail")
    public ResponseEntity<?> get(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (id == null) {
            return ResponseEntity.ok("not data");
        }
        VoucherDto Voucher = service.getVoucherById(id);
        if (Objects.isNull(Voucher)) {
            return ResponseEntity.ok("not data");
        }
        return ResponseEntity.ok(Voucher);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody VoucherDto VoucherDto, HttpServletRequest request) {
        if (Objects.isNull(VoucherDto)) {
            return ResponseEntity.ok("not data");
        }
        VoucherDto result = service.save(VoucherDto);
        if (!Objects.isNull(result)) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok("not data");
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> delete(@RequestBody VoucherDto VoucherDto, HttpServletRequest request) {
        if (Objects.isNull(VoucherDto)) {
            return ResponseEntity.ok(false);
        }
        boolean result = service.delete(VoucherDto.getId());
        if (result) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(result);
    }

}
