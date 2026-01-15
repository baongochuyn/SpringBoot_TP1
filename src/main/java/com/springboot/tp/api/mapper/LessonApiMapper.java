package com.springboot.tp.api.mapper;

import com.springboot.tp.api.dto.lesson.LessonDto;
import com.springboot.tp.domain.entity.Lesson;

public class LessonApiMapper {
    public static LessonDto toDto(Lesson lesson) {
        return new LessonDto(lesson.getId(), lesson.getTitle(), lesson.getTheme().getName());
    }
    private LessonApiMapper() {}
}
