package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.FeedbackDto;
import com.example.freshfoodapi.dto.PaymentDto;
import com.example.freshfoodapi.dto.response.FeedbackResponse;
import com.example.freshfoodapi.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1/payment/")
public class PaymentController extends  BaseController{
    @Autowired
    PaymentServiceImpl service;

    @PostMapping(value = "save")
    public ResponseEntity<PaymentDto> save(@RequestBody PaymentDto paymentDto, HttpServletRequest request) {
        PaymentDto result = service.save(paymentDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<PaymentDto>> gets( HttpServletRequest request) {
        List<PaymentDto> list = service.getAll();

        return ResponseEntity.ok(list);
    }
}
