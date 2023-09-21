package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.dto.SaleDto;
import com.example.freshfoodapi.entity.Sale;
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
    private SaleRepository repository;
    @Autowired
    private SaleMapper mapper;
    @Autowired
    private SaleSpecification specification;


    @Override
    public SaleDto save(SaleDto saleDto) {
        if (saleDto.getId() != 0) {
            Optional<Sale> saleFindById = repository.findById(saleDto.getId());
            if (saleFindById.isEmpty()){
                return  null;
            }
            saleDto.setCodeSale(saleFindById.get().getCodeSale());
            saleDto.setPersent(saleFindById.get().getPersent());
            saleDto.setStartDay(saleFindById.get().getStartDay());
            saleDto.setEndDay(saleFindById.get().getEndDay());
        }

        Sale result = repository.save(mapper.dtoToEntity(saleDto));
        return mapper.entityToDto(result);
    }

    @Override
    public List<SaleDto> findAll(SaleDto criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Sale> sales = repository.findAll(specification.filter(criteria), pageable);
        return sales.getContent()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SaleDto getsaleById(Long id) {
        Optional<Sale> saleOptional = repository.findById(id);
        if (saleOptional.isEmpty()) {
            // Handle the case when the sale is not found.
            return null; // Or throw an exception, return an empty saleDto, etc.
        }
        SaleDto saleDto = mapper.entityToDto(saleOptional.get());
        return saleDto;
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }

        Optional<Sale> saleFindById = repository.findById(id);
        if (saleFindById.isEmpty()){
            return  false;
        }
        Sale sale = saleFindById.get();
        sale.setIsDeleted(true);
        Sale result = repository.save(sale);
        return true;

    }
}
