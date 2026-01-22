package com.springboot.tp.api.controller;


import com.springboot.tp.api.dto.lesson.LessonDto;
import com.springboot.tp.api.dto.lesson.LessonRequestDto;
import com.springboot.tp.api.exception.NotFoundException;
import com.springboot.tp.api.mapper.LessonApiMapper;
import com.springboot.tp.app.service.LessonService;
import com.springboot.tp.domain.entity.Lesson;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LessonController {

    // Appel au service de gestion des leçons
    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }

    @GetMapping("/lessons")
    public List<LessonDto> getAllLessons() {
        List<Lesson> lessonList = service.findAllWithTheme();
        if (lessonList == null || lessonList.isEmpty()) {
            throw new NotFoundException("No lessons found");
        }
        return LessonApiMapper.toDto(lessonList);
    }

    @PostMapping("/themes/{themeId}/lessons")
    LessonDto newLessonWithTheme(@Valid @RequestBody LessonRequestDto dto, @PathVariable("themeId") Long themeId) {
        Lesson lesson = service.create(dto.getTitle(), themeId);
        if (lesson == null) {
            throw new NotFoundException("Theme with id " + themeId + " not found");
        }
        return LessonApiMapper.toDto(lesson);
    }
}
