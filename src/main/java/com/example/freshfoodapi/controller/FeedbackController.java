package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.FeedbackDto;
import com.example.freshfoodapi.dto.response.FeedbackResponse;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.service.impl.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "api/v1/feedback/")
public class FeedbackController extends BaseController{
    @Autowired
    FeedbackServiceImpl service;

    @PostMapping(value = "")
    public ResponseEntity<List<FeedbackResponse>> gets(@RequestBody FeedbackDto criteria, HttpServletRequest request) {
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        List<FeedbackResponse> list = service.getAll(criteria);

        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "save")
    public ResponseEntity<FeedbackResponse> save(@RequestBody FeedbackDto feedbackDto, HttpServletRequest request) {
        if (Objects.isNull(feedbackDto)) {
            return null;
        }
        FeedbackResponse result = service.save(feedbackDto);
        if (!Objects.isNull(result)) {
            return ResponseEntity.ok(result);
        }
        return null;
    }

    @GetMapping(value = "detail")
    public ResponseEntity<FeedbackResponse> getDetail(@RequestParam(required = false) Long id, HttpServletRequest request) {
        if (id == null) {
            throw new BusinessException("400","id invalid");
        }
        FeedbackResponse feedbackDto = service.getFeedbackById(id);
        return ResponseEntity.ok(feedbackDto);
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
