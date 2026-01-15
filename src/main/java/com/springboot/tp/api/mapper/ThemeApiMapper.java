package com.springboot.tp.api.mapper;

import com.springboot.tp.api.dto.theme.ThemeCreateDto;
import com.springboot.tp.domain.entity.Theme;

public class ThemeApiMapper {
    public static ThemeCreateDto toCreateDto(Theme theme) {
        return new ThemeCreateDto(theme.getId(), theme.getName());
    }
    private ThemeApiMapper() {}
}
