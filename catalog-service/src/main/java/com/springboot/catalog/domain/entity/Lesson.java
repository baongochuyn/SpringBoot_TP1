package com.springboot.catalog.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

    // Constructors
    public Lesson() {}
    public Lesson(String title) { this.title = title; }

    public Lesson(String title, Theme theme) {
        this.title = title;
        setTheme(theme);
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Theme getTheme() { return theme; }
    public void setTheme(Theme theme) {
        //Supprime cette leçon de l'ancien thème si elle existe
        if (this.theme != null) {
            this.theme.getLessons().remove(this);
        }
        this.theme = theme;
        // Ajouter au nouveau thème
        if (theme != null && !theme.getLessons().contains(this)) {
            theme.getLessons().add(this);
        }
    }
}