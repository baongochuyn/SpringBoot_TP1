package com.springboot.tp.infra.repository;

import com.springboot.tp.domain.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    
    // Utilisation d'une seule requête pour charger la leçon et son thème
    @Query("Select l FROM Lesson l JOIN FETCH l.theme")
    List<Lesson> findAllWithTheme();    

    // Permet de récupèrer les leçons selon un thème
    List<Lesson> findByThemeId(Long themeId);

    // Permet de retrouver une leçon par son titre si besoin
    Optional<Lesson> findByTitle(String title);
}
