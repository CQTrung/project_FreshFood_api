package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.CategoryDto;
import com.example.freshfoodapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/category")
public class CategoryController extends BaseController{
    @Autowired
    CategoryService service;


    
}
