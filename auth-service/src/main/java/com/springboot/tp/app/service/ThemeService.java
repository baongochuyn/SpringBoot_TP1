package com.springboot.tp.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.tp.api.exception.NotFoundException;
import com.springboot.tp.domain.entity.Theme;
import com.springboot.tp.infra.repository.ThemeRepository;

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
                .orElseThrow(() -> new NotFoundException("Theme with id " + id + " not found"));
    }

}
