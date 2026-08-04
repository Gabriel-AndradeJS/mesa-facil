package com.quiosque.mesafacil.User.Controller;

import com.quiosque.mesafacil.User.DTOs.CreateUserDTO;
import com.quiosque.mesafacil.User.DTOs.ResponseUserDTO;
import com.quiosque.mesafacil.User.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;

    @GetMapping
    public String ObterUser(){
        return "Teste ok!";
    }


    @PostMapping
    public ResponseUserDTO createUser(@RequestBody CreateUserDTO dto){
        return this.userService.createUser(dto);
    }

}
