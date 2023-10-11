package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.dto.WarehouseDto;
import com.example.freshfoodapi.entity.Warehouse;
import com.example.freshfoodapi.entity.Warehouse;
import com.example.freshfoodapi.entity.Warehouse;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.mapper.WarehouseMapper;
import com.example.freshfoodapi.repository.WarehouseRepository;
import com.example.freshfoodapi.service.WarehouseService;
import com.example.freshfoodapi.spec.WarehouseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    WarehouseRepository repository;
    @Autowired
    WarehouseSpecification specification;
    @Autowired
    WarehouseMapper mapper;

    @Override
    public List<WarehouseDto> getAll(WarehouseDto criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Warehouse> warehouses = repository.findAll(specification.filter(criteria), pageable);
        return warehouses.getContent()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public WarehouseDto getWarehouseById(Long id) {
        Optional<Warehouse> warehouseOptional = repository.findById(id);
        if(warehouseOptional.isEmpty()){
            throw new BusinessException("Not found warehouse");
        }
        return mapper.entityToDto(warehouseOptional.get());
    }

    @Override
    public WarehouseDto save(WarehouseDto warehouseDto) {
        if (warehouseDto.getId() != 0) {
            Optional<Warehouse> warehouseOptional = repository.findById(warehouseDto.getId());
            if (warehouseOptional.isEmpty()){
                throw new BusinessException("not found Warehouse");
            }
            Warehouse warehouse = warehouseOptional.get();
            warehouse.setQuantity(warehouseDto.getQuantity());
            repository.save(warehouse);
            return mapper.entityToDto(warehouse);
        }
        Warehouse warehouseNew =repository.save(mapper.dtoToEntity(warehouseDto));
        return mapper.entityToDto(warehouseNew);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        Optional<Warehouse> warehouseOptional = repository.findById(id);
        if (warehouseOptional.isEmpty()){
            return  false;
        }
        Warehouse warehouse = warehouseOptional.get();
        warehouse.setIsDeleted(true);
        repository.save(warehouse);
        return true;
    }
}
