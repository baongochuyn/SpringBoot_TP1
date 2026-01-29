package com.springboot.tp.progress_service.infra.repository;

import com.springboot.tp.progress_service.domain.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByUsername(String username);
    Optional<Progress> findByUsernameAndLessonId(String username, Long lessonId);
    boolean existsByUsernameAndLessonId(String username, Long lessonId);
}
