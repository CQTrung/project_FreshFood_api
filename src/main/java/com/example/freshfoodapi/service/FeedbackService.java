package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.FeedbackDto;
import com.example.freshfoodapi.dto.response.FeedbackResponse;

import java.util.List;

public interface FeedbackService {
    List<FeedbackResponse> getAll(FeedbackDto criteria);
    FeedbackResponse getFeedbackById(Long id);
    FeedbackResponse save(FeedbackDto FeedbackDto);
    boolean delete(Long id);
}
