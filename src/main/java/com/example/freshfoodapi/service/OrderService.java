package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.OrderDto;
import com.example.freshfoodapi.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAll(OrderDto criteria);
    OrderResponse getOrderById(Long id);
    OrderResponse save(OrderDto OrderDto);
    boolean delete(Long id);
}
