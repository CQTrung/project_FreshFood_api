package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/category")
public class CategoryController {
    @Autowired
    CategoryServiceImpl categoryService;
}
