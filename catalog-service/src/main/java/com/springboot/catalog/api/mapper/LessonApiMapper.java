package com.springboot.catalog.api.mapper;

import com.springboot.catalog.api.dto.lesson.LessonDto;
import com.springboot.catalog.domain.entity.Lesson;

import java.util.List;
import java.util.stream.Collectors;

public class LessonApiMapper {
    public static LessonDto toDto(Lesson lesson) {
        return new LessonDto(lesson.getId(), lesson.getTitle(), lesson.getTheme().getName());
    }
    public static List<LessonDto> toDto(List<Lesson> lessonList) {
        return lessonList.stream().
                map(lesson -> new LessonDto(lesson.getId(), lesson.getTitle(), lesson.getTheme().getName()))
                .collect(Collectors.toList());
    }
    private LessonApiMapper() {}
}
