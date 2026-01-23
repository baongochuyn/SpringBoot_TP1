package com.springboot.tp.security.jwt;



import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    public String generateToken(String username) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(username)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(jwtProperties.getExpiration()))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean validateToken(String token) {
    try {
        // La méthode decode() vérifie déjà la signature et le format
        Jwt jwt = jwtDecoder.decode(token);
        
        // On vérifie simplement si la date d'expiration est passée
        return jwt.getExpiresAt() != null && jwt.getExpiresAt().isAfter(Instant.now());
    } catch (JwtException e) {
        // Si le token est malformé, expiré ou si la signature est invalide
        return false;
    }
}

    public String getUsernameFromToken(String token) {
        try {
            return jwtDecoder.decode(token).getSubject();
        } catch (JwtException e) {
            return null;
        }
    }


    public Authentication getAuthentication(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        
        // On récupère les rôles du token pour reconstruire les autorités Spring
        String rolesClaim = jwt.getClaim("roles");
        Collection<? extends GrantedAuthority> authorities = rolesClaim == null ? 
            Arrays.asList() :
            Arrays.stream(rolesClaim.split(" "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(jwt.getSubject(), token, authorities);
    }
}
