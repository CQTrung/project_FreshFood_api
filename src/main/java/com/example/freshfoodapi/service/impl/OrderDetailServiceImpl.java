package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.repository.OrderDetailRepository;
import com.example.freshfoodapi.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
}
