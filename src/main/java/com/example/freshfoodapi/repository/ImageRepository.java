package com.example.freshfoodapi.repository;

import com.example.freshfoodapi.entity.Category;
import com.example.freshfoodapi.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>, JpaSpecificationExecutor {

}
