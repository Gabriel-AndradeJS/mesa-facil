package com.quiosque.mesafacil.User.Controller;

import com.quiosque.mesafacil.User.DTOs.CreateUserDTO;
import com.quiosque.mesafacil.User.DTOs.CreateWaiterDTO;
import com.quiosque.mesafacil.User.DTOs.ResponseUserDTO;
import com.quiosque.mesafacil.User.DTOs.WaiterDTO;
import com.quiosque.mesafacil.User.Service.UserService;
import com.quiosque.mesafacil.User.Service.WaiterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final WaiterService waiterService;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, WaiterService waiterService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.waiterService = waiterService;
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

    @GetMapping("waiters")
    public List<WaiterDTO> getWaiters(@RequestHeader("Authorization") String token) {
        return this.userService.getWaiters(token);
    }

    @GetMapping("roles")
    public List<WaiterDTO> getRoles() {
        return this.userService.getRoles();
    }

    @PostMapping("waiters")
    public ResponseEntity<WaiterDTO> createWaiter(@Valid @RequestBody CreateWaiterDTO dto, @RequestHeader("Authorization") String token) {
        return this.waiterService.createWaiter(dto, token);
    }
}
