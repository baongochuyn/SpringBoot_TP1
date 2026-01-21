package com.springboot.tp.api.dto.theme;

public class ThemeRequestDto {
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
