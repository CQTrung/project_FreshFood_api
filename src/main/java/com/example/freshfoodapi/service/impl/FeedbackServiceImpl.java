package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.dto.FeedbackDto;
import com.example.freshfoodapi.dto.FeedbackDto;
import com.example.freshfoodapi.dto.response.FeedbackResponse;
import com.example.freshfoodapi.entity.Feedback;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.mapper.FeedbackMapper;
import com.example.freshfoodapi.repository.FeedbackRepository;
import com.example.freshfoodapi.repository.UserRepository;
import com.example.freshfoodapi.service.FeedbackService;
import com.example.freshfoodapi.service.UserService;
import com.example.freshfoodapi.spec.FeedbackSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackServiceImpl service;
    @Autowired
    FeedbackRepository repository;
    @Autowired
    FeedbackSpecification specification;
    @Autowired
    FeedbackMapper mapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Override
    public List<FeedbackResponse> getAll(FeedbackDto criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Feedback> feedbacks = repository.findAll(specification.filter(criteria), pageable);
        return feedbacks.getContent()
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackResponse getFeedbackById(Long id) {
        if (id < 0){
            throw new BusinessException("feedback.invalid");
        }

        Optional<Feedback> feedbackOptional = repository.findById(id);
        if(feedbackOptional.isEmpty()){
            throw new BusinessException("Not found feedback");
        }

        FeedbackResponse feedbackDto = mapper.entityToResponse(feedbackOptional.get());
        return feedbackDto;
    }

    @Override
    public FeedbackResponse save(FeedbackDto feedbackDto) {
        if (feedbackDto.getId() != 0) {
            Optional<Feedback> feedbackOptional = repository.findById(feedbackDto.getId());
            if (feedbackOptional.isEmpty()){
                throw new BusinessException("not found Feedback");
            }
            Feedback feedback = feedbackOptional.get();
            feedback.setSubjectName(feedbackDto.getSubjectName());
            feedback.setNote(feedbackDto.getNote());
            repository.save(feedback);
            return  mapper.entityToResponse(feedback);
        }
        Feedback feedback = mapper.dtoToEntity(feedbackDto);
        feedback.setUser(userService.getUserCurrent());
        Feedback result = repository.save(feedback);
        return mapper.entityToResponse(result);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        Optional<Feedback> feedbackOptional = repository.findById(id);
        if (feedbackOptional.isEmpty()){
            return false;
        }
        Feedback feedback = feedbackOptional.get();
        feedback.setIsDeleted(true);
        repository.save(feedback);
        return true;
    }
}
