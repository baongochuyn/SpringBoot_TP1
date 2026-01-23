package com.springboot.tp.infra.repository;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.springboot.tp.domain.entity.Role;
import com.springboot.tp.domain.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeUserRepository {
    private final List<User> users = new ArrayList<>();

    public FakeUserRepository(PasswordEncoder passwordEncoder,@Value("${app.security.admin-password}") String adminPwd,
        @Value("${app.security.user-password}") String userPwd) {
        users.add(new User("admin", passwordEncoder.encode(adminPwd), Role.ADMIN));
        users.add(new User("user", passwordEncoder.encode(userPwd), Role.USER));
    }
   

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }
}
