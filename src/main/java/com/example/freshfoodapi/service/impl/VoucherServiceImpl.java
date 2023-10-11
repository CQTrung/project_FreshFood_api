package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.dto.VoucherDto;
import com.example.freshfoodapi.entity.Sale;
import com.example.freshfoodapi.entity.Voucher;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.mapper.VoucherMapper;
import com.example.freshfoodapi.repository.VoucherRepository;
import com.example.freshfoodapi.service.VoucherService;
import com.example.freshfoodapi.spec.VoucherSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    VoucherRepository repository;
    @Autowired
    VoucherSpecification specification;
    @Autowired
    VoucherMapper mapper;

    @Override
    public List<VoucherDto> getAll(VoucherDto criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Voucher> vouchers = repository.findAll(specification.filter(criteria), pageable);
        return vouchers.getContent()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VoucherDto getVoucherById(Long id) {
        Optional<Voucher> voucherOptional = repository.findById(id);
        if(voucherOptional.isEmpty()){
            throw new BusinessException("Not found sale");
        }
        return mapper.entityToDto(voucherOptional.get());
    }

    @Override
    public VoucherDto save(VoucherDto voucherDto) {
        if (voucherDto.getId() != 0) {
            Optional<Voucher> voucherOptional = repository.findById(voucherDto.getId());
            if (voucherOptional.isEmpty()){
                throw new BusinessException("not found voucher");
            }
            Voucher voucher = voucherOptional.get();
            voucher.setDiscount(voucherDto.getDiscount());
            voucher.setVoucherCode(voucherDto.getVoucherCode());
            voucher.setStartDay(voucherDto.getStartDay());
            voucher.setEndDay(voucherDto.getEndDay());
            repository.save(voucher);
            return mapper.entityToDto(voucher);
        }
        Voucher voucher = repository.findByVoucherCodeAndIsDeletedFalse(voucherDto.getVoucherCode());
        if (voucher != null){
            throw new BusinessException("voucher code does exist");
        }
        Voucher voucherNew =repository.save(mapper.dtoToEntity(voucherDto));
        return mapper.entityToDto(voucherNew);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        Optional<Voucher> voucherOptional = repository.findById(id);
        if (voucherOptional.isEmpty()){
            return  false;
        }
        Voucher voucher = voucherOptional.get();
        voucher.setIsDeleted(true);
        repository.save(voucher);
        return true;
    }
}
