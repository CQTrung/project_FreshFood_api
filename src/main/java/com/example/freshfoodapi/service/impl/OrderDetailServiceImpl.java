package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.constant.Status;
import com.example.freshfoodapi.dto.OrderDetailDto;
import com.example.freshfoodapi.dto.response.OrderDetailResponse;
import com.example.freshfoodapi.entity.*;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.mapper.OrderDetailMapper;
import com.example.freshfoodapi.repository.OrderDetailRepository;
import com.example.freshfoodapi.repository.OrderRepository;
import com.example.freshfoodapi.repository.PaymentRepository;
import com.example.freshfoodapi.repository.ProductRepository;
import com.example.freshfoodapi.service.OrderDetailService;
import com.example.freshfoodapi.service.UserService;
import com.example.freshfoodapi.spec.OrderDetailSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository repository;
    @Autowired
    OrderDetailSpecification specification;
    @Autowired
    OrderDetailMapper mapper;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserService userService;
    @Autowired
    PaymentRepository paymentRepository;

    
    @Override
    public List<OrderDetailResponse> getAll(OrderDetailDto criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<OrderDetail> orderDetails = repository.findAll(specification.filter(criteria), pageable);
        return orderDetails.getContent()
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailResponse getOrderDetailById(Long id) {
        if (id < 0){
            throw new BusinessException("orderDetail.invalid");
        }

        Optional<OrderDetail> orderDetailOptional = repository.findById(id);
        if(orderDetailOptional.isEmpty()){
            throw new BusinessException("Not found orderDetail");
        }

        OrderDetailResponse orderDetailResponse = mapper.entityToResponse(orderDetailOptional.get());
        return orderDetailResponse;
    }

    @Override
    public OrderDetailResponse save(OrderDetailDto orderDetailDto) {
        if (orderDetailDto.getId() != 0) {
            Optional<OrderDetail> orderDetailOptional = repository.findById(orderDetailDto.getId());
            if (orderDetailOptional.isEmpty()){
                throw new BusinessException("not found OrderDetail");
            }

            OrderDetail orderDetail = orderDetailOptional.get();
            orderDetail.setQuantity(orderDetailDto.getQuantity());
            orderDetail.setTotalPrice(orderDetailDto.getTotalPrice());

            List<Long> orderIds = orderDetailDto.getOrderId();
            for (Long orderId : orderIds){
            Optional<Order> orderOptional = orderRepository.findById(orderId);
                if (orderOptional.isEmpty()){
                    throw  new BusinessException( "not found order");
                }
                orderDetail.getOrders().add(orderOptional.get());
            }
            if(orderDetail.getStatus() == Status.COMPLETED && orderDetailDto.getStatus() == Status.CANCEL){
                throw new BusinessException("Order cannot be cancelled");
            }
            if(orderDetail.getStatus() == Status.COMPLETED || orderDetailDto.getStatus() == Status.COMPLETED){
                BigDecimal price = orderDetailDto.getTotalPrice();
                int point = price.divide(new BigDecimal("100")).intValue();
                int op = orderDetail.getUser().getPoint();
                orderDetail.getUser().setPoint(op + point);
            }
            orderDetail.setStatus(orderDetailDto.getStatus());
            repository.save(orderDetail);
            return  mapper.entityToResponse(orderDetail);
        }
        //create
        OrderDetail orderDetail = mapper.dtoToEntity(orderDetailDto);

        for (Long orderId : orderDetailDto.getOrderId()){
            Optional<Order> orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isEmpty()){
                throw  new BusinessException( "not found order");
            }
            Order order = orderOptional.get();
             for (Product product :order.getProducts()){
                int qt =product.getQuantity();
                int odt = orderDetailDto.getQuantity();
                int pqt = qt-odt;
                if (pqt == 0){
                    throw  new BusinessException("product sold out");
                }
                product.setQuantity(pqt);
                productRepository.save(product);
            }
             orderDetail.getOrders().add(orderOptional.get());
        }
        Optional<Payment> paymentOptional = paymentRepository.findById(orderDetailDto.getPaymentId());
        if (paymentOptional.isEmpty()){
            throw new BusinessException("not found payment");
        }
        orderDetail.setPayment(paymentOptional.get());

        User user = userService.getUserCurrent();
        orderDetail.setUser(user);

        OrderDetail result = repository.save(orderDetail);

        OrderDetailResponse response = mapper.entityToResponse(result);
        return  response;
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        Optional<OrderDetail> orderDetailOptional = repository.findById(id);
        if (orderDetailOptional.isEmpty()){
            return false;
        }
        OrderDetail orderDetail = orderDetailOptional.get();
        orderDetail.setIsDeleted(true);
        repository.save(orderDetail);
        return true;
    }
}
