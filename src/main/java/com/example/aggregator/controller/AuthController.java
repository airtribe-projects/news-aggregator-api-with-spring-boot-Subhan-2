package com.example.aggregator.controller;

import com.example.aggregator.dto.*;
import com.example.aggregator.security.JwtUtil;
import com.example.aggregator.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody @Valid UserDTO userDTO) {
        userService.register(userDTO);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody  @Valid LoginRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponse(token);
    }
}

