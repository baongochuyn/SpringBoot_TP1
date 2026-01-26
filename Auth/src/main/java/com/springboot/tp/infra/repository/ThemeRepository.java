package com.springboot.tp.infra.repository;

import com.springboot.tp.domain.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long>{
    
    // Vérification de l'existence d'un thème avec le nom donné
    boolean existsByName(String name);

    // Permet de récupérer un thème par son nom si besoin
    Optional<Theme> findByName(String name);

    // Permet de récupérer un thème par son id
    Optional<Theme> findById(Long id);
}
