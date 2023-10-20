package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.CategoryDto;
import com.example.freshfoodapi.dto.CategoryDto;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1/category/")
public class CategoryController extends BaseController{
    @Autowired
    CategoryService service;


    @PostMapping(value = "")
    public ResponseEntity<List<CategoryDto>> gets(@RequestBody CategoryDto criteria, HttpServletRequest request) {
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        List<CategoryDto> list = service.getAll(criteria);

        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "save")
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto CategoryDto, HttpServletRequest request) {
        if (Objects.isNull(CategoryDto)) {
            return null;
        }
        CategoryDto result = service.save(CategoryDto);
        if (!Objects.isNull(result)) {
            return ResponseEntity.ok(result);
        }
        return null;
    }

    @GetMapping(value = "detail")
    public ResponseEntity<?> getDetail(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException("400","id invalid");
        }
        CategoryDto CategoryDto = service.getCategoryById(id);

        if (Objects.isNull(CategoryDto)) {
            return ResponseEntity.ok("not data");
        }

        return ResponseEntity.ok(CategoryDto);
    }

    @DeleteMapping(value ="delete")
    public ResponseEntity<?> delete(@RequestParam(required = false,value = "id") Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException("400","id invalid");
        }
        boolean result = service.delete(id);
        if(result){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok(false);
    }
    
}
