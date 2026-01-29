package com.springboot.catalog.api.dto.lesson;

public class LessonDto {
    private Long id;
    private String title;
    private String themeName;

    public LessonDto() {
    }

    public LessonDto(Long id, String title,String themeName) {
        this.id = id;
        this.title = title;
        this.themeName = themeName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThemeName() {
        return themeName;
    }
}


