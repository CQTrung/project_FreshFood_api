package com.example.freshfoodapi.spec;


import com.example.freshfoodapi.dto.VoucherDto;
import com.example.freshfoodapi.entity.Voucher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class VoucherSpecification {
    public Specification<Voucher>filter(final VoucherDto criteria){
        return  (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.isFalse(root.get("isDeleted")));

            if (criteria.getStartDay() != null) {
                // Add a condition to filter by start time if provided
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startDay"), criteria.getStartDay()));
            }

            if (criteria.getEndDay() != null) {
                // Add a condition to filter by end time if provided
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endDay"), criteria.getEndDay()));
            }

            if (StringUtils.isNotEmpty(criteria.getVoucherCode())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("voucherCode")), "%" + criteria.getVoucherCode() + "%"));
            }

            return criteriaBuilder.and(predicates.stream()
                    .toArray(Predicate[]::new));
        };
    }
}
