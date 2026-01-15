package com.springboot.tp.api.dto.lesson;

public class LessonWithThemeRequestDTO {

    private Long id;
    private String title;
    private ThemeResponseDTO theme;

    public LessonWithThemeRequestDTO() {
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ThemeResponseDTO getTheme() {
        return theme;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTheme(ThemeResponseDTO theme) {
        this.theme = theme;
    }
}
