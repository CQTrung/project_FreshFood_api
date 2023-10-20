package com.example.freshfoodapi.repository;

import com.example.freshfoodapi.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long>, JpaSpecificationExecutor {
    Voucher findByVoucherCodeAndIsDeletedFalse(String code);
    List<Voucher> findByStartDayBeforeAndEndDayAfter(Date start, Date end);
}
