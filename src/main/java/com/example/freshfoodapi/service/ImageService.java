package com.example.freshfoodapi.service;

import com.example.freshfoodapi.dto.ImageDto;

import java.util.List;

public interface ImageService {
//    List<ImageDto>getAll(ImageDto criteria);
    ImageDto getImageById(Long id);
    ImageDto save(ImageDto ImageDto);
    boolean delete(Long id);
}
