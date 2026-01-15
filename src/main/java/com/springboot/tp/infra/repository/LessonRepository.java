package com.springboot.tp.infra.repository;

import com.springboot.tp.domain.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    
}
