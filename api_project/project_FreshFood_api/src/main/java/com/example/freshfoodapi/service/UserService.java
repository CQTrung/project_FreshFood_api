package com.example.freshfoodapi.service;


import com.example.freshfoodapi.dto.LoginResponseDto;
import com.example.freshfoodapi.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(long id);

    boolean add(User user);

    User getByName(String username);

    User getByUsername(String username);

    boolean checkLogin(User user);

    LoginResponseDto returnLogin(User user);

    void sendMessage(String message);

    User getUserById(Long userId);

    User getUserCurrent();

    List<User> gets();

    User findUserById(Long id);

    User save(User user);
}
