package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.dto.SaleDto;
import com.example.freshfoodapi.entity.Sale;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.mapper.SaleMapper;
import com.example.freshfoodapi.repository.SaleRepository;
import com.example.freshfoodapi.service.SaleService;
import com.example.freshfoodapi.spec.SaleSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    SaleRepository repository;
    @Autowired
    SaleSpecification specification;
    @Autowired
    SaleMapper mapper;

    @Override
    public List<SaleDto> getAll(SaleDto criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Sale> Sales = repository.findAll(specification.filter(criteria), pageable);
        return Sales.getContent()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SaleDto getSaleById(Long id) {
        if (id < 0){
            throw new BusinessException("sale.invalid");
        }

        Optional<Sale> saleOptional = repository.findById(id);
        if(saleOptional.isEmpty()){
            throw new BusinessException("Not found sale");
        }

        SaleDto saleDto = mapper.entityToDto(saleOptional.get());
        return saleDto;
    }

    @Override
    public SaleDto save(SaleDto saleDto) {
        if (saleDto.getId() != 0) {
            Optional<Sale> saleOptional = repository.findById(saleDto.getId());
            if (saleOptional.isEmpty()){
                throw new BusinessException("not found sale");
            }
            Sale sale = saleOptional.get();
            sale.setCodeSale(saleDto.getCodeSale());
            sale.setDiscount(saleDto.getDiscount());
            sale.setStartDay(saleDto.getStartDay());
            sale.setEndDay(saleDto.getEndDay());
            repository.save(sale);
            return  mapper.entityToDto(sale);
        }
        Sale sale = repository.findByCodeSaleAndIsDeletedFalse(saleDto.getCodeSale());
        if(sale != null){
            throw  new BusinessException("code sale does exist");
        }
        Sale result = repository.save(mapper.dtoToEntity(saleDto));
        return mapper.entityToDto(result);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        Optional<Sale> saleOptional = repository.findById(id);
        if (saleOptional.isEmpty()){
            return  false;
        }
        Sale sale = saleOptional.get();
        sale.setIsDeleted(true);
        repository.save(sale);
        return true;
    }

}
