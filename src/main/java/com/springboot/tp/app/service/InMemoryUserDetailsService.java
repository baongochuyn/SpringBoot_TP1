package com.springboot.tp.app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springboot.tp.domain.entity.User;
import com.springboot.tp.infra.repository.FakeUserRepository;


public class InMemoryUserDetailsService implements UserDetailsService{
    private final FakeUserRepository userRepository;

    public InMemoryUserDetailsService(FakeUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Loading user: " + username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        System.out.println("receive user: " + user.getUsername() +"pass"+ user.getPassword());
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // On encode à la volée pour le test
                .roles(user.getRole().name())
                .build();
    }
}
