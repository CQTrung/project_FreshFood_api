package com.example.freshfoodapi.spec;


import com.example.freshfoodapi.dto.UserDto;
import com.example.freshfoodapi.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification {
    public Specification<User>filter(final UserDto criteria){
        return  (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.isFalse(root.get("isDeleted")));
            if (StringUtils.isNotEmpty(criteria.getUsername())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + criteria.getUsername() + "%"));
            }
            return criteriaBuilder.and(predicates.stream()
                    .toArray(Predicate[]::new));
        };
    }
}
