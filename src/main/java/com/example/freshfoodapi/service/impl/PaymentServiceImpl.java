package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.dto.PaymentDto;
import com.example.freshfoodapi.entity.Payment;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.mapper.PaymentMapper;
import com.example.freshfoodapi.repository.PaymentRepository;
import com.example.freshfoodapi.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository repository;

    @Autowired
    PaymentMapper mapper;


    @Override
    public List<PaymentDto> getAll() {
        List<Payment> payments = repository.findAll();
        return payments.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public PaymentDto getPaymentById(Long id) {
        return null;
    }

    @Override
    public PaymentDto save(PaymentDto paymentDto) {
        if (paymentDto.getId() != 0) {
            Optional<Payment> paymentOptional = repository.findById(paymentDto.getId());
            if (paymentOptional.isEmpty()){
                throw new BusinessException("not found payment");
            }
            Payment payment = paymentOptional.get();
            payment.setPaymentMethod(paymentDto.getPaymentMethod());
            repository.save(payment);
            return  mapper.entityToDto(payment);
        }
        Payment payment = mapper.dtoToEntity(paymentDto);
        Payment result = repository.save(payment);
        return mapper.entityToDto(result);
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
