package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.constant.Status;
import com.example.freshfoodapi.dto.OrderDto;
import com.example.freshfoodapi.dto.OrderDto;
import com.example.freshfoodapi.dto.response.OrderDetailResponse;
import com.example.freshfoodapi.dto.response.OrderResponse;
import com.example.freshfoodapi.entity.*;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.mapper.OrderMapper;
import com.example.freshfoodapi.repository.OrderRepository;
import com.example.freshfoodapi.repository.UserRepository;
import com.example.freshfoodapi.service.OrderService;
import com.example.freshfoodapi.service.UserService;
import com.example.freshfoodapi.spec.OrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository repository;
    @Autowired
    OrderSpecification specification;
    @Autowired
    OrderMapper mapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Override
    public List<OrderResponse> getAll(OrderDto criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Order> orders = repository.findAll(specification.filter(criteria), pageable);
        return orders.getContent()
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        if (id < 0){
            throw new BusinessException("order.invalid");
        }

        Optional<Order> orderOptional = repository.findById(id);
        if(orderOptional.isEmpty()){
            throw new BusinessException("Not found order");
        }

        OrderResponse orderResponse = mapper.entityToResponse(orderOptional.get());
        return orderResponse;
    }

    @Override
    public OrderResponse save(OrderDto orderDto) {
        if (orderDto.getId() != 0) {
            Optional<Order> orderOptional = repository.findById(orderDto.getId());
            if (orderOptional.isEmpty()){
                throw new BusinessException("not found Order");
            }
            Order order = orderOptional.get();
            order.setUnitPrice(orderDto.getUnitPrice());
            order.setFirstName(orderDto.getFirstName());
            order.setLastName(orderDto.getLastName());
            order.setPhone(orderDto.getPhone());
            order.setAddress(orderDto.getAddress());

            repository.save(order);
            return  mapper.entityToResponse(order);
        }
        Order result = repository.save(mapper.dtoToEntity(orderDto));
        return mapper.entityToResponse(result);
    }

    @Override
    public OrderResponse create(OrderDto orderDto) {
        Order order = mapper.dtoToEntity(orderDto);
        orderDto.setUserId(userService.getUserCurrent().getId());
        User user = userRepository.getById(orderDto.getUserId());
        order.setUser(user);
        Order result = repository.save(order);
        OrderResponse response = mapper.entityToResponse(result);
        return response;
    }


    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        Optional<Order> orderOptional = repository.findById(id);
        if (orderOptional.isEmpty()){
            return false;
        }
        Order order = orderOptional.get();
        order.setIsDeleted(true);
        repository.save(order);
        return true;
    }
}
