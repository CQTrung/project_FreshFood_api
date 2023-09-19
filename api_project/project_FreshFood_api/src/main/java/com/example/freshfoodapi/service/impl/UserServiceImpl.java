package com.example.freshfoodapi.service.impl;


import com.example.freshfoodapi.config.ServiceProperties;
import com.example.freshfoodapi.dto.LoginResponseDto;
import com.example.freshfoodapi.entity.Role;
import com.example.freshfoodapi.entity.User;
import com.example.freshfoodapi.repository.RoleRepository;
import com.example.freshfoodapi.repository.UserRepository;
import com.example.freshfoodapi.security.config.UserDetailsImpl;
import com.example.freshfoodapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl extends A_Service implements UserService {

    @Autowired
    ServiceProperties serviceProperties;
    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    List<User> users;
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private UserService userService;

    private void test1() {
        String url = serviceProperties.getBaseUrl() + serviceProperties.getClassroomListUrl();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json"); // set header
        httpHeaders.add("Authorization", "Bearer + token"); // set token
        User user = new User();

        user.setUsername("demo");
        user.setPassword("demo");

        HttpEntity entity = new HttpEntity<>(user, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
    }


    //    static {
//        User user1 = new User(1, "admin1", "admin@123", "admin1@gmail.com", "234234234");
//        user1.setRoles(new String[]{"ROLE_ADMIN"});
//
//        User user2 = new User(2, "admin2", "admin@123", "admin2@gmail.com", "222222222");
//        user2.setRoles(new String[]{"ROLE_USER"});
//
//        User user3 = new User(3, "admin3", "admin@123", "admin3@gmail.com", "233333333");
//        user3.setRoles(new String[]{"ROLE_ADMIN", "ROLE_USER"});
//
//        users.add(user1);
//        users.add(user2);
//        users.add(user3);
//    }
    @Async
    public void sendMessage(String message) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(Long userId) {
        return repository.getById(userId);
    }

    @Override
    public User getUserCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = getByUsername(userDetails.getUsername());
        return user;
    }

    @Override
    public List<User> gets() {
        return repository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> userOptional = repository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get(); // Extract the User object from Optional
        } else {
            return null; // Return null if user is not found
        }
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }


    @Override
    public List<User> findAll() {
        users = repository.findAll();
        return users;
    }

    @Transactional
    public User registerUser(String username, String email, String hashedPassword,String tel) {
        // Create a new user
        User user = new User(username, email, hashedPassword,tel);

        // Set the default role (assuming "customer" role exists in the database)
        Optional<Role> customerRoleOptional = roleRepository.findByName("CUSTOMER");

        if (customerRoleOptional.isPresent()) {
            Role customerRole = customerRoleOptional.get();
            user.setRoles(Collections.singleton(customerRole));
            return repository.save(user);
        }

        return user;
    }

    @Override
    public User findById(long id) {
//        return users.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
//        for (User user : users) {
//            if (user.getId() == id) {
//                return user;
//            }
//        }
        if (id > 0) {
            return repository.getById(id);
        }

        return null;
    }

    @Override
    public boolean add(User user) {
        if (!Objects.isNull(user) && user.getId() == 0) {
            validateText(user.getUsername(), user.getPassword(), user.getEmail(), user.getEmail());
            Optional<User> existedUser = users.stream().filter(u -> u.getUsername().equals(user.getUsername())).findFirst();
            if (existedUser.isPresent()) return false;
            repository.save(user);
//            users.add(user);
            return true;
        }
        return false;
    }

    @Override
    public User getByName(String username) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        User user = repository.findByUsername(username);
        return user;
    }

    @Override
    public boolean checkLogin(User user) {
        for (User existedUser : findAll()) {
            if (user.getUsername().equals(existedUser.getUsername()) && user.getPassword().equals(existedUser.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public LoginResponseDto returnLogin(User user) {
        return null;
    }


//    public static void main(String[] args) {
//        System.err.println(JWTUtils.genToken(users.get(0)));
//
//        JWTUtils jwtUtils = new JWTUtils();
//        String token = JWTUtils.genToken(users.get(0));
//        System.err.println(jwtUtils.getExpireDateFormToken(token));
//    }

}
