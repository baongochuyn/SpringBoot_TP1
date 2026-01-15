package com.springboot.tp.api.mapper;

import com.springboot.tp.api.dto.lesson.LessonCreateDto;
import com.springboot.tp.domain.entity.Lesson;

public class LessonApiMapper {
    public static LessonCreateDto toCreateDto(Lesson lesson) {
        return new LessonCreateDto(lesson.getId(), lesson.getTitle(), lesson.getTheme().getName());
    }
    private LessonApiMapper() {}
}
