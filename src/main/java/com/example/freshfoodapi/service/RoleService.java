package com.example.freshfoodapi.service;

import com.example.freshfoodapi.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> gets();

    Role getRoleById(Long id);

    Role save(Role role);

}
