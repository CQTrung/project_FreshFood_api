package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.repository.VoucherRepository;
import com.example.freshfoodapi.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    VoucherRepository voucherRepository;
}
