package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.dto.UserDto;
import com.example.freshfoodapi.dto.VoucherDto;
import com.example.freshfoodapi.email.EmailService;
import com.example.freshfoodapi.entity.User;
import com.example.freshfoodapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController extends BaseController {
    @Autowired
    UserService service;
    @Autowired
    EmailService emailService;


    @GetMapping(value = "/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = service.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping(value = "/")
    public ResponseEntity<List<UserDto>> gets(@RequestBody UserDto criteria, HttpServletRequest request) {
        if (criteria.getPageSize() == null || criteria.getPageSize() <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        }
        if (criteria.getPageNumber() == null || criteria.getPageNumber() < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        }
        List<UserDto> list = service.gets(criteria);

        return ResponseEntity.ok(list);
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        User user = service.findUserById(id);
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
       UserDto userDto = service.getDetail(id);
       return  ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!id.equals(user.getId())) {
            return ResponseEntity.badRequest().build();
        }

        User updatedUser = service.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("send-voucher")
    public ResponseEntity<?> sendVoucher(@RequestParam("voucherId") long voucherId) {
        service.sendVouchersToUsersWithHighPoints(voucherId);
        return ResponseEntity.ok("successfully");
    }

}
