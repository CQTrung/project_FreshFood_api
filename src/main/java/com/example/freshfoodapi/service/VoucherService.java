package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.VoucherDto;
import com.example.freshfoodapi.dto.request.VoucherAssignToUserRequest;
import com.example.freshfoodapi.entity.Voucher;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VoucherService {
    List<VoucherDto> getAll(VoucherDto criteria);
    VoucherDto getVoucherById(Long id);
    VoucherDto save(VoucherDto VoucherDto);
    boolean delete(Long id);
    List<VoucherDto> findVouchersValidNow();
    boolean assignVoucherToUsers(VoucherAssignToUserRequest request);

}
