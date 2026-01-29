package com.springboot.tp.security.jwt;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final JwtProperties jwtProperties;

    public JwtTokenProvider(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, JwtProperties jwtProperties) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.jwtProperties = jwtProperties;
    }

    /**
     * Génère un JWT signé avec RS256
     */
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        // Récupérer les rôles de l'utilisateur authentifié
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        // JWS Header avec RS256 (asymétrique)
        JwsHeader jwsHeader = JwsHeader.with(SignatureAlgorithm.RS256)
                .keyId("auth-service-key-2026") // correspond au kid du JWK
                .build();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(jwtProperties.getExpiration()))
                .claim("roles", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    /**
     * Récupère le username à partir du JWT
     */
    public String getUsernameFromToken(String token) {
        try {
            return jwtDecoder.decode(token).getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
}
