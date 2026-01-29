package com.springboot.catalog.app.service;

import org.springframework.stereotype.Service;

import com.springboot.catalog.domain.entity.Course;
import com.springboot.catalog.domain.event.CourseEvent;
import com.springboot.catalog.domain.event.CourseEventProducer;
import com.springboot.catalog.infra.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository repository;
    private final CourseEventProducer producer;

    public CourseService(CourseRepository repository,
                         CourseEventProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

     public Course create(String title, String description) {
        Course course = repository.save(new Course(title,description));
        CourseEvent event = new CourseEvent(
            course.getId(),
            course.getTitle(),
            "CREATED"
        );

        producer.sendCourseCreatedEvent(event);
        return course;
    }
}
