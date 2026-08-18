package com.quiosque.mesafacil.User.Service;

import com.quiosque.mesafacil.Config.JwtService;
import com.quiosque.mesafacil.User.DTOs.CreateWaiterDTO;
import com.quiosque.mesafacil.User.DTOs.WaiterDTO;
import com.quiosque.mesafacil.User.Entity.UserEntity;
import com.quiosque.mesafacil.User.Entity.WaiterEntity;
import com.quiosque.mesafacil.User.Mapper.UserMapper;
import com.quiosque.mesafacil.User.Repository.UserRepository;
import com.quiosque.mesafacil.User.Repository.WaiterRepository;
import com.quiosque.mesafacil.User.enums.UserRole;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseEntity<WaiterDTO> createWaiter(CreateWaiterDTO dto, String admin) {
        String token = admin.replace("Bearer ", "");
        Integer id = jwtService.extractClaimId(token, "id");

        UserEntity adminUser = userRepository.findById(id.longValue())
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
}
