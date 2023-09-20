package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.repository.PaymentRepository;
import com.example.freshfoodapi.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
}
