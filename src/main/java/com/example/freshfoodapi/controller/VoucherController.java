package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.VoucherDto;
import com.example.freshfoodapi.dto.request.VoucherAssignToUserRequest;
import com.example.freshfoodapi.email.EmailService;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.service.impl.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1/voucher/")
public class VoucherController extends BaseController{
    @Autowired
    VoucherServiceImpl service;



    @PostMapping(value = "")
    public ResponseEntity<List<VoucherDto>> gets(@RequestBody VoucherDto criteria, HttpServletRequest request) {
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        List<VoucherDto> list = service.getAll(criteria);

        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "list")
    public ResponseEntity<List<VoucherDto>> list( HttpServletRequest request) {
        List<VoucherDto> list = service.findVouchersValidNow();
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "save")
    public ResponseEntity<VoucherDto> save(@RequestBody VoucherDto voucherDto, HttpServletRequest request) {
        if (Objects.isNull(voucherDto)) {
            return null;
        }
        VoucherDto result = service.save(voucherDto);
        if (!Objects.isNull(result)) {
            return ResponseEntity.ok(result);
        }
        return null;
    }

    @PostMapping("assign")
    public ResponseEntity<?> assignVoucherToUsers(@RequestBody VoucherAssignToUserRequest voucherAssignToUserRequest, HttpServletRequest request) {
            boolean result = service.assignVoucherToUsers(voucherAssignToUserRequest);
            if (result) {
                ResponseEntity.ok(result);
            }
            return ResponseEntity.ok(false);
    }

    @GetMapping(value = "detail")
    public ResponseEntity<?> getDetail(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException("400","id invalid");
        }
        VoucherDto VoucherDto = service.getVoucherById(id);

        if (Objects.isNull(VoucherDto)) {
            return ResponseEntity.ok("not data");
        }

        return ResponseEntity.ok(VoucherDto);
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
