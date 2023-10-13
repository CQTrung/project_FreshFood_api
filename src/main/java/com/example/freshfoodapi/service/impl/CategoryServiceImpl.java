package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.dto.CategoryDto;
import com.example.freshfoodapi.dto.CategoryDto;
import com.example.freshfoodapi.dto.RestErrorDto;
import com.example.freshfoodapi.entity.Category;
import com.example.freshfoodapi.entity.Category;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.mapper.CategoryMapper;
import com.example.freshfoodapi.repository.CategoryRepository;
import com.example.freshfoodapi.service.CategoryService;
import com.example.freshfoodapi.spec.CategorySpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService { 
    @Autowired
    CategoryRepository repository;
    @Autowired
    CategorySpecification specification;
    @Autowired
    CategoryMapper mapper;
    
    
    @Override
    public List<CategoryDto> getAll(CategoryDto criteria) {
    Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
    Page<Category> categorys = repository.findAll(specification.filter(criteria), pageable);
    return categorys.getContent()
            .stream()
            .map(mapper::entityToDto)
            .collect(Collectors.toList());
}

    @Override
    public CategoryDto getCategoryById(Long id) {
        if (id < 0){
            throw new BusinessException("Category.invalid");
        }

        Optional<Category> categoryOptional = repository.findById(id);
        if(categoryOptional.isEmpty()){
            throw new BusinessException("Not found Category");
        }

        CategoryDto categoryDto = mapper.entityToDto(categoryOptional.get());
        return categoryDto;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        if (categoryDto.getId() != 0) {
            Optional<Category> categoryOptional = repository.findById(categoryDto.getId());
            if (categoryOptional.isEmpty()){
                throw new BusinessException("not found Category");
            }
            Category category = categoryOptional.get();
            category.setName(categoryDto.getName());
            repository.save(category);
            return  mapper.entityToDto(category);
        }
        Category result = repository.save(mapper.dtoToEntity(categoryDto));
        return mapper.entityToDto(result);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        Optional<Category> categoryOptional = repository.findById(id);
        if (categoryOptional.isEmpty()){
            return false;
        }
        Category category = categoryOptional.get();
        category.setIsDeleted(true);
        repository.save(category);
        return true;
    }
}
