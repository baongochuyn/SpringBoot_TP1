package com.springboot.tp.api.controller;

import com.springboot.tp.api.dto.Login.LoginRequestDto;
import com.springboot.tp.api.dto.Login.LoginResponseDto;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = authService.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        return new LoginResponseDto(token);
    }
}
