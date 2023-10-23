package com.example.freshfoodapi.controller;

import com.example.freshfoodapi.entity.Role;
import com.example.freshfoodapi.entity.User;
import com.example.freshfoodapi.exception.BusinessException;
import com.example.freshfoodapi.repository.RoleRepository;
import com.example.freshfoodapi.repository.UserRepository;
import com.example.freshfoodapi.security.config.UserDetailsImpl;
import com.example.freshfoodapi.security.payload.request.LoginRequest;
import com.example.freshfoodapi.security.payload.request.SignupRequest;
import com.example.freshfoodapi.security.payload.response.JwtResponse;
import com.example.freshfoodapi.security.payload.response.MessageResponse;
import com.example.freshfoodapi.security.utils.JwtUtils;
import com.example.freshfoodapi.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1")
public class LoginController extends BaseController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserServiceImpl userService;



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        if (!userRepository.existsByUsername(loginRequest.getUsername())) {
            throw  new BusinessException("404" , "account does not exist");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        // Step 1: Hash the password
        String hashedPassword = encoder.encode(signUpRequest.getPassword());

    // Step 2: Create the User object with username, email, and hashed password
        User user = userService.registerUser(signUpRequest.getUsername(), signUpRequest.getEmail(), hashedPassword,signUpRequest.getTel());
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        String token = extractAuthToken(request);
        if (token != null) {
            jwtUtils.expireJwtToken(token);
        }
        return ResponseEntity.ok(new MessageResponse("User logged out successfully!"));
    }

    private String extractAuthToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Lấy token từ header
        }

        return null;
    }
}
