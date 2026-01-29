package com.springboot.catalog.api.mapper;

import com.springboot.catalog.api.dto.theme.ThemeDto;
import com.springboot.catalog.domain.entity.Theme;

public class ThemeApiMapper {
    public static ThemeDto toDto(Theme theme) {
        return new ThemeDto(theme.getId(),theme.getName());
    }
    private ThemeApiMapper() {}
}
