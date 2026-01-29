package com.springboot.tp.progress_service.api.controller;

import com.springboot.tp.progress_service.domain.entity.Progress;
import com.springboot.tp.progress_service.infra.repository.ProgressRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressRepository repo;

    public ProgressController(ProgressRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Progress> all() {
        return repo.findAll();
    }
}
