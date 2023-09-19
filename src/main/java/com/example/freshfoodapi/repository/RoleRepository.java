package com.example.freshfoodapi.repository;


import com.example.freshfoodapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor {
    Optional<Role> findByName(String user);
}
