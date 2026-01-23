package com.springboot.tp.app.service;

//import com.springboot.tp.domain.entity.User;
//import com.springboot.tp.infra.repository.UserRepository;
import com.springboot.tp.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    //private final UserRepository userRepository;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider
            //UserRepository userRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        //this.userRepository = userRepository;
    }

    public String login(String username, String password) {
        System.out.println("Attempting login with username: " + username + " and password: " + password);
        System.out.println("Match ? " + new BCryptPasswordEncoder().matches("admin123", "$2a$10$4QGYbT6a2Z3HvVX.8Cb/yO.4N4SGCbQwBQhayd.cyr7JApOYVUe8O"));
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        System.out.println("Authentication success: " + authentication.isAuthenticated());

        /*User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return jwtTokenProvider.generateToken(user);*/
        return jwtTokenProvider.generateToken(authentication);
    }
}
