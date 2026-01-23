package com.springboot.tp.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.springboot.tp.app.service.InMemoryUserDetailsService;
import com.springboot.tp.infra.repository.FakeUserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    // Ajoute /error ici pour éviter le 403 en cas de bug interne
                    .requestMatchers("/auth/**", "/error", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/lessons/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/themes").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth -> oauth
                    .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
            )
            .build();
}

    // ----------------------------
    // JWT Converter
    // ----------------------------
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtConverter;
    }

    // ----------------------------
    // Password encoder
    // ----------------------------
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ----------------------------
    // UserDetailsService unique
    // ----------------------------
    @Bean
    public UserDetailsService userDetailsService(FakeUserRepository repo) {
        return new InMemoryUserDetailsService(repo);
    }

    // ----------------------------
    // AuthenticationManager unique
    // ----------------------------
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return new org.springframework.security.authentication.ProviderManager(provider);
    }
}
