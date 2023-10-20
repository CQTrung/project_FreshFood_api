package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.dto.PaymentDto;
import com.example.freshfoodapi.repository.PaymentRepository;
import com.example.freshfoodapi.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository repository;

    @Override
    public List<PaymentDto> getAll(PaymentDto criteria) {
        return null;
    }

    @Override
    public PaymentDto getPaymentById(Long id) {
        return null;
    }

    @Override
    public PaymentDto save(PaymentDto PaymentDto) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
