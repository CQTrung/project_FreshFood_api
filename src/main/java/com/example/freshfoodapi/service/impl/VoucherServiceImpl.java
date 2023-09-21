package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.dto.VoucherDto;
import com.example.freshfoodapi.entity.Sale;
import com.example.freshfoodapi.entity.Voucher;
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
    VoucherMapper mapper;
    @Autowired
    VoucherSpecification specification;

    @Override
    public VoucherDto save(VoucherDto voucherDto) {
        if (voucherDto.getId() != 0) {
            Optional<Voucher> voucherFindById = repository.findById(voucherDto.getId());
            if (voucherFindById.isEmpty()){
                return  null;
            }
            voucherDto.setVoucherCode(voucherFindById.get().getVoucherCode());
            voucherDto.setValue(voucherFindById.get().getValue());
            voucherDto.setStartDay(voucherFindById.get().getStartDay());
            voucherDto.setEndDay(voucherFindById.get().getEndDay());
        }

        Voucher result = repository.save(mapper.dtoToEntity(voucherDto));
        return mapper.entityToDto(result);
    }

    @Override
    public List<VoucherDto> findAll(VoucherDto criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Voucher> vouchers = repository.findAll(specification.filter(criteria), pageable);
        return vouchers.getContent()
                .stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VoucherDto getVoucherById(Long id) {
        Optional<Voucher> voucherOptional= repository.findById(id);
        if(voucherOptional.isEmpty()){
            return  null;
        }
        VoucherDto result = mapper.entityToDto(voucherOptional.get());
        return  result;
    }


    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }

        Optional<Voucher> voucherFindById = repository.findById(id);
        if (voucherFindById.isEmpty()){
            return  false;
        }
        Voucher voucher = voucherFindById.get();
        voucher.setIsDeleted(true);
        Voucher result = repository.save(voucher);
        return true;
    }
}
