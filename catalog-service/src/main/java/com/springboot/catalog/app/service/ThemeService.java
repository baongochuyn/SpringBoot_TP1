package com.springboot.catalog.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.catalog.domain.entity.Theme;
import com.springboot.catalog.infra.repository.ThemeRepository;

@Service
public class ThemeService {
    private final ThemeRepository repository;

    public ThemeService(ThemeRepository repository) {
        this.repository = repository;
    }
    
    public Theme create(String name) {
        Theme theme = new Theme(name);
        return repository.save(theme);
    }

    public List<Theme> findAll() {
        return repository.findAll();
    }

    public Theme findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theme not found"));
    }

}
