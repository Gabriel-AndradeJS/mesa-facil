package com.quiosque.mesafacil.user.service;

import com.quiosque.mesafacil.configs.JwtService;
import com.quiosque.mesafacil.user.dto.CreateWaiterDTO;
import com.quiosque.mesafacil.user.dto.WaiterDTO;
import com.quiosque.mesafacil.user.entity.UserEntity;
import com.quiosque.mesafacil.user.entity.WaiterEntity;
import com.quiosque.mesafacil.user.mapper.UserMapper;
import com.quiosque.mesafacil.user.repository.UserRepository;
import com.quiosque.mesafacil.user.repository.WaiterRepository;
import com.quiosque.mesafacil.user.enums.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
public class WaiterService {

    private final UserRepository userRepository;
    private final WaiterRepository waiterRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public WaiterService(
            UserRepository userRepository,
            WaiterRepository waiterRepository,
            PasswordEncoder passwordEncoder,
            UserMapper userMapper,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.waiterRepository = waiterRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    public ResponseEntity<WaiterDTO> createWaiter(CreateWaiterDTO dto, Long id) {
        UserEntity adminUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin user not found"));

        UserEntity waiterUser = new UserEntity();

        waiterUser.setName(dto.getName());
        waiterUser.setEmail(dto.getEmail());
        waiterUser.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );
        waiterUser.setRole(UserRole.WAITER);

        userRepository.save(waiterUser);

        WaiterEntity waiter = new WaiterEntity();

        waiter.setName(dto.getName());
        waiter.setUser(waiterUser);
        waiter.setAdmin(adminUser);

        WaiterEntity savedWaiter = waiterRepository.save(waiter);

        WaiterDTO waiterDTO = userMapper.WaiterEntityToWaiter(savedWaiter);
        return ResponseEntity.status(HttpStatus.CREATED).body(waiterDTO);
    }

    public WaiterDTO getWaiterById(Long id){
        WaiterEntity waiter = waiterRepository.findById(id).orElseThrow( () -> new RuntimeException("Waiter not found"));
        return userMapper.WaiterEntityToWaiter(waiter);
    }

    public WaiterDTO getWaiterUserById(Long id){
        return userMapper.WaiterEntityToWaiter(waiterRepository.findByUserId(id));

    }

    public WaiterDTO getWaitersUserId(Long userId){
        return userMapper.WaiterEntityToWaiter(waiterRepository.findByUserId(userId));
    }

    public UserEntity getAdminForUser(UserEntity user) {
        if (user.getRole() == UserRole.ADMIN) {
            return user;
        }

        if (user.getRole() == UserRole.WAITER) {
            return waiterRepository.findOptionalByUserId(user.getId())
                    .map(WaiterEntity::getAdmin)
                    .orElseThrow(() -> new ResponseStatusException(
                            FORBIDDEN, "Waiter não está vinculado a um administrador"));
        }

        throw new ResponseStatusException(FORBIDDEN, "Usuário sem acesso a este recurso");
    }
}
