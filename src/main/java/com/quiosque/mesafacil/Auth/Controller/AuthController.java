package com.quiosque.mesafacil.Auth.Controller;

import com.quiosque.mesafacil.Auth.DTO.LoginDTO;
import com.quiosque.mesafacil.Auth.DTO.ResponseLogin;
import com.quiosque.mesafacil.Auth.Service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final LoginService loginService;

    @PostMapping
    public ResponseLogin login(@RequestBody LoginDTO loginDTO) {
        return loginService.login(loginDTO);
    }
}
