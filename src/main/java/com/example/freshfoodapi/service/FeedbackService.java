package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {
    List<FeedbackDto> getAll(FeedbackDto criteria);
    FeedbackDto getFeedbackById(Long id);
    FeedbackDto save(FeedbackDto FeedbackDto);
    boolean delete(Long id);
}
