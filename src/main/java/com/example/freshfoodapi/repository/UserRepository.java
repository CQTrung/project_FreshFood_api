package com.example.freshfoodapi.repository;

import com.example.freshfoodapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

 
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
