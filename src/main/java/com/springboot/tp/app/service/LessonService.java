package com.springboot.tp.app.service;

import java.util.List;

import com.springboot.tp.domain.entity.Lesson;
import com.springboot.tp.domain.entity.Theme;
import com.springboot.tp.infra.repository.LessonRepository;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    private final LessonRepository repository;
     private final ThemeService themeService;

    public LessonService(LessonRepository repository,ThemeService themeService) {
        this.repository = repository;
        this.themeService = themeService;
    }

    public Lesson create(String title, Long themeId) {
        Theme theme= themeService.findById(themeId);
        Lesson lesson = new Lesson(title,theme);
        return repository.save(lesson);
    }

    public List<Lesson> findAllWithTheme() {
        return repository.findAllWithTheme();
    }
}
