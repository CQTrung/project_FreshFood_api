package com.example.freshfoodapi.service.impl;

import com.example.freshfoodapi.dto.ImageDto;
import com.example.freshfoodapi.entity.Image;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.mapper.ImageMapper;
import com.example.freshfoodapi.repository.ImageRepository;
import com.example.freshfoodapi.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository repository;
    @Autowired
    ImageMapper mapper;

//    @Override
//    public List<ImageDto> getAll(ImageDto criteria) {
//        return null;
//    }


    @Override
    public ImageDto getImageById(Long id) {
        Optional<Image> imageOptional = repository.findById(id);
        if (imageOptional.isEmpty()){
            throw new BusinessException("Image not found");
        }
        return mapper.entityToDto(imageOptional.get());
    }

    @Override
    public ImageDto save(ImageDto imageDto) {
        if (imageDto.getId() !=0) {
            Optional<Image> imageOptional = repository.findById(imageDto.getId());
            if (imageOptional.isEmpty()){
                throw  new BusinessException("Not found image");
            }
            Image image = imageOptional.get();
            image.setName(imageDto.getName());
            image.setType(imageDto.getType());
            image.setSize(imageDto.getSize());
            image.setData(imageDto.getData());
        }
        Image image = repository.save(mapper.dtoToEntity(imageDto));
        return mapper.entityToDto(image);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        Optional<Image> orderDetailOptional = repository.findById(id);
        if (orderDetailOptional.isEmpty()){
            return false;
        }
        Image image = orderDetailOptional.get();
        image.setIsDeleted(true);
        repository.save(image);
        return true;
    }
}
