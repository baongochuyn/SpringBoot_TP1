package com.springboot.tp.api.controller;

import com.springboot.tp.api.dto.LessonDTO;
import com.springboot.tp.app.service.LessonService;
import com.springboot.tp.domain.entity.Lesson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    // Appel au service de gestion des leçons
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public List<LessonDTO> getAllLessons() {
        return lessonService.getAllLessons();
    }
}
