package com.springboot.tp.api.controller;
import com.springboot.tp.api.dto.theme.ThemeCreateDto;
import com.springboot.tp.app.service.ThemeService;
import com.springboot.tp.domain.entity.Theme;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThemeController {
    private final ThemeService service;

    ThemeController(ThemeService service) {
        this.service = service;
    }

    @PostMapping("/themes")
    ThemeCreateDto newTheme(@RequestBody String name) {
        Theme theme = service.create(name);
        return new ThemeCreateDto(theme.getId(), theme.getName());
    }

}
