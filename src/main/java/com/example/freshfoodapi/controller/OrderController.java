package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.OrderDto;
import com.example.freshfoodapi.dto.response.OrderResponse;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1/order/")
public class OrderController extends BaseController{
    @Autowired
    OrderServiceImpl service;
    @PostMapping(value = "")
    public ResponseEntity<List<OrderResponse>> gets(@RequestBody OrderDto criteria, HttpServletRequest request) {
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        List<OrderResponse> list = service.getAll(criteria);

        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "save")
    public ResponseEntity<OrderResponse> save(@RequestBody OrderDto orderDto, HttpServletRequest request) {
        if (Objects.isNull(orderDto)) {
            return null;
        }
        OrderResponse result = service.save(orderDto);
        if (!Objects.isNull(result)) {
            return ResponseEntity.ok(result);
        }
        return null;
    }

    @GetMapping(value = "detail")
    public ResponseEntity<OrderResponse> getDetail(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException("400","id invalid");
        }
        OrderResponse ordertDto = service.getOrderById(id);

        if (Objects.isNull(ordertDto)) {
            throw new BusinessException("order is empty");
        }

        return ResponseEntity.ok(ordertDto);
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
