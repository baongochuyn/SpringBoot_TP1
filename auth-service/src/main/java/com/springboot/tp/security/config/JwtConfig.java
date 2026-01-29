package com.springboot.tp.security.config;

import com.springboot.tp.api.controller.JwkController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.JWKSet;

@Configuration
public class JwtConfig {

    private final JwkController jwkController;

    public JwtConfig(JwkController jwkController) {
        this.jwkController = jwkController;
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        // Crée une clé RSA avec la clé publique et privée exposée par JwkController
        RSAKey rsaKey = new RSAKey.Builder(jwkController.getPublicKey())
                .privateKey(jwkController.getPrivateKey())
                .keyID("auth-service-key-2026")
                .build();

        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(rsaKey)));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Utilise uniquement la clé publique pour vérifier les JWT
        return NimbusJwtDecoder.withPublicKey(jwkController.getPublicKey()).build();
    }
}
