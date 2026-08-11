package com.quiosque.mesafacil.Auth.Service;

import com.quiosque.mesafacil.Auth.DTO.LoginDTO;
import com.quiosque.mesafacil.Auth.DTO.ResponseLogin;
import com.quiosque.mesafacil.Config.JwtService;
import com.quiosque.mesafacil.User.Entity.UserEntity;
import com.quiosque.mesafacil.User.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;


    public ResponseLogin login(LoginDTO loginDTO) {
        UserEntity user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
           authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(),
                            loginDTO.getPassword()
                    )
            );

        } catch (AuthenticationException ex) {
            logger.warn("Authentication failed for {}: {}", loginDTO.getEmail(), ex.getMessage());
            throw ex;
        }

        String token = jwtService.createToken(user);
        return new ResponseLogin(token);
    }
}
