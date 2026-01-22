package com.springboot.tp.api.controller;

import com.springboot.tp.api.dto.Login.LoginRequestDto;
import com.springboot.tp.api.dto.Login.LoginResponseDto;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto request) {
        String token = authService.login(
                request.getUsername(),
                request.getPassword());
        return new LoginResponseDto(token);
    }
}
