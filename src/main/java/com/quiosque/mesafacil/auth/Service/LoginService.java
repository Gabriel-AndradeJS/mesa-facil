package com.quiosque.mesafacil.auth.Service;

import com.quiosque.mesafacil.auth.DTO.LoginDTO;
import com.quiosque.mesafacil.auth.DTO.ResponseLogin;
import com.quiosque.mesafacil.configs.JwtService;
import com.quiosque.mesafacil.user.entity.UserEntity;
import com.quiosque.mesafacil.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

        UserEntity user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new IllegalStateException("Usuário autenticado não encontrado"));

        String token = jwtService.createToken(user);
        return new ResponseLogin(token);
    }
}
