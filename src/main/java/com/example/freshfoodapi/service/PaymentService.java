package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> getAll();
    PaymentDto getPaymentById(Long id);
    PaymentDto save(PaymentDto PaymentDto);
    boolean delete(Long id);
}
