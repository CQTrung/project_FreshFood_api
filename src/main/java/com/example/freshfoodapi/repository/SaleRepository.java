package com.example.freshfoodapi.repository;

import com.example.freshfoodapi.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long>, JpaSpecificationExecutor {
    Sale findByCodeSaleAndIsDeletedFalse(String code);
}
