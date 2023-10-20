package com.example.freshfoodapi.spec;


import com.example.freshfoodapi.dto.ProductDto;
import com.example.freshfoodapi.dto.request.ProductRequest;
import com.example.freshfoodapi.entity.Category;
import com.example.freshfoodapi.entity.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSpecification {
    public Specification<Product>filter(final ProductRequest criteria){
        return  (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.isFalse(root.get("isDeleted")));
            if (StringUtils.isNotEmpty(criteria.getName())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + criteria.getName() + "%"));
            }


            if (criteria.getMinPrice() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),criteria.getMinPrice()));
            }

            if (criteria.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),criteria.getMaxPrice()));

            }

            if (criteria.getMinWeight() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("weight"),criteria.getMinWeight()));

            }

            if (criteria.getMaxWeight() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("weight"),criteria.getMaxWeight()));

            }

            if (criteria.getCategoryId() != 0) {
                // Assuming there is a ManyToOne or OneToOne relationship between Product and Category
                Join<Product, Category> categoryJoin = root.join("category", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(categoryJoin.get("id"), criteria.getCategoryId()));
            }

            return criteriaBuilder.and(predicates.stream()
                    .toArray(Predicate[]::new));
        };
    }
}
