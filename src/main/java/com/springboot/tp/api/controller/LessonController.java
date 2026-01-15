package com.springboot.tp.api.controller;


import com.springboot.tp.app.service.LessonService;
import com.springboot.tp.domain.entity.Lesson;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    // Appel au service de gestion des leçons
    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }

    @GetMapping
    public List<Lesson> getAllLessons() {
        return service.findAll();
    }

    @PostMapping("/themes/{themeId}/lessons")
    Lesson newLessonWithTheme(@RequestBody String title, @PathVariable Long themeId) {
        return service.create(title, themeId);
    }
}
