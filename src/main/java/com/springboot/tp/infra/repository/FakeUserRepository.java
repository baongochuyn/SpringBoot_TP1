package com.springboot.tp.infra.repository;


import org.springframework.stereotype.Repository;

import com.springboot.tp.domain.entity.Role;
import com.springboot.tp.domain.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeUserRepository {
    private final List<User> users = new ArrayList<>();

    public FakeUserRepository() {
        // On initialise nos données de test ici
        users.add(new User("admin", "admin123", Role.ROLE_ADMIN));
        users.add(new User("user", "user123", Role.ROLE_USER));
    }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }
}
