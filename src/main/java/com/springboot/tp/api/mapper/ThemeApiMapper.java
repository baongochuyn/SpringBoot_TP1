package com.springboot.tp.api.mapper;

import com.springboot.tp.api.dto.theme.ThemeDto;
import com.springboot.tp.domain.entity.Theme;

public class ThemeApiMapper {
    public static ThemeDto toDto(Theme theme) {
        return new ThemeDto(theme.getId(),theme.getName());
    }
    private ThemeApiMapper() {}
}
