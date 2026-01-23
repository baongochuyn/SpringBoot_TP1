package com.springboot.tp.app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.tp.domain.entity.User;
import com.springboot.tp.infra.repository.FakeUserRepository;

public class InMemoryUserDetailsService implements UserDetailsService{
    private final FakeUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InMemoryUserDetailsService(FakeUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword())) // On encode à la volée pour le test
                .roles(user.getRole().name())
                .build();
    }
}
