package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.CategoryDto;
import com.example.freshfoodapi.dto.RestErrorDto;
import com.example.freshfoodapi.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll(CategoryDto criteria);
    CategoryDto getCategoryById(Long id);
    CategoryDto save(CategoryDto CategoryDto);
    boolean delete(Long id);
}
