package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.entity.Role;
import com.example.freshfoodapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/roles")
public class RoleController extends BaseController {
    @Autowired
    RoleService service;


    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> Roles = service.gets();
        return ResponseEntity.ok(Roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Role role = service.getRoleById(id);
        if (role != null) {
            return ResponseEntity.ok(role);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = service.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        if (!id.equals(role.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Role updatedRole = service.save(role);
        return ResponseEntity.ok(updatedRole);
    }
}
