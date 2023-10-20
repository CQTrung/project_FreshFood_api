package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.OrderDetailDto;
import com.example.freshfoodapi.dto.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailResponse> getAll(OrderDetailDto criteria);
    OrderDetailResponse getOrderDetailById(Long id);
    OrderDetailResponse save(OrderDetailDto OrderDetailDto);
//    OrderDetailResponse create(OrderDetailDto OrderDetailDto);
    boolean delete(Long id);
}
