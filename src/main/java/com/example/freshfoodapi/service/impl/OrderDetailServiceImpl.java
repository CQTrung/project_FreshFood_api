package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.dto.OrderDetailDto;
import com.example.freshfoodapi.dto.response.OrderDetailResponse;
import com.example.freshfoodapi.entity.Order;
import com.example.freshfoodapi.entity.OrderDetail;
import com.example.freshfoodapi.entity.Product;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.mapper.OrderDetailMapper;
import com.example.freshfoodapi.repository.OrderDetailRepository;
import com.example.freshfoodapi.repository.OrderRepository;
import com.example.freshfoodapi.repository.ProductRepository;
import com.example.freshfoodapi.service.OrderDetailService;
import com.example.freshfoodapi.spec.OrderDetailSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
            orderDetail.setUnitPrice(orderDetailDto.getUnitPrice());
            Optional<Order> orderOptional = orderRepository.findById(orderDetailDto.getOrderId());
            if (orderOptional.isEmpty()){
                throw  new BusinessException( "not found order");
            }
            orderDetail.setOrder(orderOptional.get());
            Optional<Product> productOptional = productRepository.findById(orderDetailDto.getProductId());
            if (productOptional.isEmpty()){
                throw  new BusinessException("not found order");
            }
            orderDetail.setProduct(productOptional.get());

            repository.save(orderDetail);
            return  mapper.entityToResponse(orderDetail);
        }
        OrderDetail result = repository.save(mapper.dtoToEntity(orderDetailDto));
        return mapper.entityToResponse(result);
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
