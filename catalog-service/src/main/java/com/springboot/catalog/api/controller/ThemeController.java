package com.springboot.catalog.api.controller;
import com.springboot.catalog.api.dto.theme.ThemeDto;
import com.springboot.catalog.api.dto.theme.ThemeRequestDto;
import com.springboot.catalog.api.mapper.ThemeApiMapper;
import com.springboot.catalog.app.service.ThemeService;
import com.springboot.catalog.domain.entity.Theme;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {
    private final ThemeService service;

    ThemeController(ThemeService service) {
        this.service = service;
    }

    @PostMapping
    ThemeDto newTheme(@RequestBody ThemeRequestDto dto) {
        Theme theme = service.create(dto.getName());
        return ThemeApiMapper.toDto(theme);
    }

}
