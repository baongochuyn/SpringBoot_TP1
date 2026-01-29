package com.springboot.catalog.api.mapper;


import java.util.List;
import java.util.stream.Collectors;

import com.springboot.catalog.api.dto.course.CourseDto;
import com.springboot.catalog.domain.entity.Course;

public class CourseApiMapper {
    public static CourseDto toDto(Course course) {
        return new CourseDto(course.getId(), course.getTitle(), course.getDescription());
    }
    public static List<CourseDto> toDto(List<Course> lessonList) {
        return lessonList.stream().
                map(course -> new CourseDto(course.getId(), course.getTitle(), course.getDescription()))
                .collect(Collectors.toList());
    }
    private CourseApiMapper() {}
}
