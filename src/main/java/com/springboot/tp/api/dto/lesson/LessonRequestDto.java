package com.springboot.tp.api.dto.lesson;

import jakarta.validation.constraints.NotBlank;

public class LessonRequestDto {
    @NotBlank(message = "Title is required")
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
