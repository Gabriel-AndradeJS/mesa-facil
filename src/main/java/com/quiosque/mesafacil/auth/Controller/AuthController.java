package com.quiosque.mesafacil.auth.Controller;

import com.quiosque.mesafacil.auth.DTO.LoginDTO;
import com.quiosque.mesafacil.auth.DTO.ResponseLogin;
import com.quiosque.mesafacil.auth.Service.LoginService;
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
