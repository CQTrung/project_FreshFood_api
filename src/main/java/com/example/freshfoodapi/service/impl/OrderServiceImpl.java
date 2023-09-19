package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.repository.OrderRepository;
import com.example.freshfoodapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
}
