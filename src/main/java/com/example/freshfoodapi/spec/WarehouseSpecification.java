package com.example.freshfoodapi.spec;


import com.example.freshfoodapi.dto.WarehouseDto;
import com.example.freshfoodapi.entity.Warehouse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class WarehouseSpecification {
    public Specification<Warehouse>filter(final WarehouseDto criteria){
        return  (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.isFalse(root.get("isDeleted")));
            return criteriaBuilder.and(predicates.stream()
                    .toArray(Predicate[]::new));
        };
    }
}
