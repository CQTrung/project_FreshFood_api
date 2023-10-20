package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.OrderDetailDto;
import com.example.freshfoodapi.dto.response.OrderDetailResponse;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.service.OrderDetailService;
import com.example.freshfoodapi.service.impl.OrderDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1/order-detail/")
public class OrderDetailController extends BaseController{
    @Autowired
    OrderDetailServiceImpl service;


    @PostMapping(value = "")
    public ResponseEntity<List<OrderDetailResponse>> gets(@RequestBody OrderDetailDto criteria, HttpServletRequest request) {
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        List<OrderDetailResponse> list = service.getAll(criteria);

        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "save")
    public ResponseEntity<OrderDetailResponse> save(@RequestBody OrderDetailDto orderDetailDto, HttpServletRequest request) {
        if (Objects.isNull(orderDetailDto)) {
            return null;
        }
        OrderDetailResponse result = service.save(orderDetailDto);
        if (!Objects.isNull(result)) {
            return ResponseEntity.ok(result);
        }
        return null;
    }

    @GetMapping(value = "detail")
    public ResponseEntity<OrderDetailResponse> getDetail(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException("400","id invalid");
        }
        OrderDetailResponse orderDetailDto = service.getOrderDetailById(id);

        if (Objects.isNull(orderDetailDto)) {
            throw  new BusinessException("order-detail empty");
        }

        return ResponseEntity.ok(orderDetailDto);
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
