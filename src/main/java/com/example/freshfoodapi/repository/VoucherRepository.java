package com.example.freshfoodapi.repository;

import com.example.freshfoodapi.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long>, JpaSpecificationExecutor {
    Voucher findByVoucherCodeAndIsDeletedFalse(String code);
}
