package com.springboot.catalog.api.dto.lesson;

public class LessonRequestDto {
    private String title;

    public LessonRequestDto() {
    }

    public LessonRequestDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
