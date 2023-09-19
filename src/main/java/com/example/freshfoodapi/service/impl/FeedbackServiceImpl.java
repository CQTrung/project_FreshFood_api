package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.repository.FeedbackRepository;
import com.example.freshfoodapi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;
}
