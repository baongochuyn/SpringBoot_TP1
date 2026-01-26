package com.springboot.tp.api.dto.theme;

import jakarta.validation.constraints.NotBlank;

public class ThemeRequestDto {
    @NotBlank(message = "The name is not mandatory")
    private String name;

    public ThemeRequestDto() {
    }

    public ThemeRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
