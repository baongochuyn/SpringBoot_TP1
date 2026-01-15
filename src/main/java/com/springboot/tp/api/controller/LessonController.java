package com.springboot.tp.api.controller;


import com.springboot.tp.api.dto.lesson.LessonDto;
import com.springboot.tp.api.mapper.LessonApiMapper;
import com.springboot.tp.app.service.LessonService;
import com.springboot.tp.domain.entity.Lesson;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    // Appel au service de gestion des leçons
    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }

    @GetMapping
    public List<LessonDto> getAllLessons() {
        List<Lesson> lessonList = service.findAllWithTheme();
        return lessonList.stream().map(LessonApiMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping("/themes/{themeId}/lessons")
    LessonDto newLessonWithTheme(@RequestBody String title, @PathVariable Long themeId) {
        Lesson lesson = service.create(title, themeId);
        return LessonApiMapper.toDto(lesson);
    }
}
