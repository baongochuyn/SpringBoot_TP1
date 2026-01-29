package com.springboot.catalog.api.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.catalog.api.dto.course.CourseDto;
import com.springboot.catalog.api.dto.course.CourseRequestDto;
import com.springboot.catalog.api.mapper.CourseApiMapper;
import com.springboot.catalog.app.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
    public CourseDto createCourse(@RequestBody CourseRequestDto dto) {
        return CourseApiMapper.toDto(service.create(dto.getTitle(), dto.getDescription()));
    }
}

