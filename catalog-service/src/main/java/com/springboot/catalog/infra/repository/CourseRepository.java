package com.springboot.catalog.infra.repository;

import com.springboot.catalog.domain.entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long>{
    // Vérification de l'existence d'un cours avec le nom donné
    boolean existsByTitle(String name);

    // Permet de récupérer un cours par son nom si besoin
    Optional<Course> findByTitle(String name);

    // Permet de récupérer un cours par son id
    Optional<Course> findById(Long id);
}
