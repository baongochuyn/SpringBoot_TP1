package com.springboot.tp.api.controller;
import com.springboot.tp.domain.entity.Lesson;
import com.springboot.tp.domain.entity.Theme;
import com.springboot.tp.infra.repository.LessonRepository;
import com.springboot.tp.infra.repository.ThemeRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThemeController {
    private final ThemeRepository themeRepository;
    private final LessonRepository lessonRepository;

    ThemeController(ThemeRepository repository, LessonRepository lessonRepository) {
        this.themeRepository = repository;
        this.lessonRepository = lessonRepository;
    }

    @PostMapping("/themes")
    Theme newTheme(@RequestBody Theme newTheme) {
        return themeRepository.save(newTheme);
    }

    @PostMapping("/themes/{themeId}/lessons")
    Lesson newLessonWithTheme(@RequestBody String title, @PathVariable Long themeId) {
        Theme theme = themeRepository.getReferenceById(themeId);
        Lesson newLesson = new Lesson(title);
        newLesson.setTheme(theme);
        return lessonRepository.save(newLesson);
    }
}
