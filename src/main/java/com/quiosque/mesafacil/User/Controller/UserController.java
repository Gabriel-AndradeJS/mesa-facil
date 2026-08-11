package com.quiosque.mesafacil.User.Controller;

import com.quiosque.mesafacil.User.DTOs.CreateUserDTO;
import com.quiosque.mesafacil.User.DTOs.ResponseUserDTO;
import com.quiosque.mesafacil.User.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping
    public ResponseEntity<ResponseUserDTO> createUser(@Valid @RequestBody CreateUserDTO dto){
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return this.userService.createUser(dto);
    }

    @GetMapping
    public List<ResponseUserDTO> getUsers(){
        return this.userService.getUsers();
    }

}
