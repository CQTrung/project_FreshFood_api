package com.example.freshfoodapi.service.impl;
import com.example.freshfoodapi.dto.ProductDto;
import com.example.freshfoodapi.dto.request.ProductRequest;
import com.example.freshfoodapi.dto.response.ProductResponse;
import com.example.freshfoodapi.entity.*;
import com.example.freshfoodapi.entity.Product;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.mapper.ProductMapper;
import com.example.freshfoodapi.repository.CategoryRepository;
import com.example.freshfoodapi.repository.ImageRepository;
import com.example.freshfoodapi.repository.ProductRepository;
import com.example.freshfoodapi.repository.SaleRepository;
import com.example.freshfoodapi.service.ProductService;
import com.example.freshfoodapi.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductSpecification specification;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ProductMapper mapper;

    @Override
    public List<ProductResponse> getAll(ProductRequest criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Product> products = repository.findAll(specification.filter(criteria), pageable);
        return products.getContent()
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        if (id < 0){
            throw new BusinessException("product.invalid");
        }

        Optional<Product> productOptional = repository.findById(id);
        if(productOptional.isEmpty()){
            throw new BusinessException("Not found product");
        }
        ProductResponse productResponse =mapper.entityToResponse(productOptional.get());
        return productResponse;
    }

    @Override
    public ProductResponse save(ProductDto productDto) {
        Optional<Product> productOptional = repository.findById(productDto.getId());
        Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
        Optional<Sale> saleOptional = saleRepository.findById(productDto.getSaleId());
        if (productDto.getId() != 0) {
            if (productOptional.isEmpty()){
                throw new BusinessException("not found Product");
            }
            Product product = productOptional.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setMadeIn(productDto.getMadeIn());
            product.setWeight(productDto.getWeight());
            List<Long> list = productDto.getImageId();
            List<Image> images = new ArrayList<>();
            for (Long imageId : list) {
                Optional<Image> imageOptional = imageRepository.findById(imageId);
                if (imageOptional.isEmpty()){
                    throw  new BusinessException("not found image");
                }
                Image image = imageOptional.get();
                images.add(image);
                image.setProduct(product);
//            product.getImages().add(image);
            }
            product.setImages(images);
            if(categoryOptional.isEmpty()){
                throw new BusinessException("not found category");
            }
            Category category = categoryOptional.get();
            product.setCategory(category);
            if (productDto.getSaleId() != 0){
                if (saleOptional.isEmpty()){
                    throw new BusinessException("not found sale");
                }
                Sale sale = saleOptional.get();
                product.setSale(sale);
            }
            Product result = repository.save(product);
            return  mapper.entityToResponse(result);
        }
        throw  new BusinessException("product id invalid");
    }

    @Override
    public ProductResponse create(ProductDto productDto) {
        Product product = mapper.dtoToEntity(productDto);
        Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
        if (categoryOptional.isEmpty()){
            throw new BusinessException("category not found");
        }

        List<Long> list = productDto.getImageId();
        List<Image> images = new ArrayList<>();
        for (Long imageId : list) {
            Optional<Image> imageOptional = imageRepository.findById(imageId);
            if (imageOptional.isEmpty()){
                throw  new BusinessException("not found image");
            }
            Image image = imageOptional.get();
            images.add(image);
            image.setProduct(product);
//            product.getImages().add(image);
        }
        product.setImages(images);

        Category category = categoryOptional.get();
        product.setCategory(category);
        if(productDto.getSaleId() != 0){
            Optional<Sale> saleOptional = saleRepository.findById(productDto.getSaleId());
            if (saleOptional.isEmpty()){
                throw new BusinessException("sale not found");
            }
            Sale sale = saleOptional.get();
            product.setSale(sale);
        }
        repository.save(product);
        return mapper.entityToResponse(product);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isEmpty()){
            return false;
        }
        Product product = productOptional.get();
        product.setIsDeleted(true);
        repository.save(product);
        return true;
    }
}
