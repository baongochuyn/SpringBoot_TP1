package com.springboot.tp.api.dto.theme;

public class ThemeDto {
    private Long id;
    private String name;

    public ThemeDto() {
    }

    public ThemeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

