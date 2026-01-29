package com.springboot.catalog.domain.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CourseEventProducer {

    private final KafkaTemplate<String, CourseEvent> kafkaTemplate;

    public CourseEventProducer(KafkaTemplate<String, CourseEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCourseCreatedEvent(CourseEvent event) {
        kafkaTemplate.send("course-events", event);
    }
}
