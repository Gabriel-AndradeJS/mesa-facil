package com.quiosque.mesafacil.User.Controller;

import com.quiosque.mesafacil.User.DTOs.CreateUserDTO;
import com.quiosque.mesafacil.User.DTOs.ResponseUserDTO;
import com.quiosque.mesafacil.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String ObterUser(){
        return "Teste ok!";
    }


    @PostMapping
    public ResponseUserDTO createUser(@RequestBody CreateUserDTO dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return this.userService.createUser(dto);
    }

}
