package com.quiosque.mesafacil.mesa.service;

import com.quiosque.mesafacil.configs.JwtService;
import com.quiosque.mesafacil.mesa.entity.MesaEntity;
import com.quiosque.mesafacil.mesa.repository.MesaRepository;
import com.quiosque.mesafacil.mesa.dtos.CreateMesaDTO;
import com.quiosque.mesafacil.mesa.dtos.ResponseMesaDTO;
import com.quiosque.mesafacil.mesa.mapper.MesaMapper;
import com.quiosque.mesafacil.user.dto.WaiterDTO;
import com.quiosque.mesafacil.user.entity.UserEntity;
import com.quiosque.mesafacil.user.repository.UserRepository;
import com.quiosque.mesafacil.user.service.WaiterService;
import com.quiosque.mesafacil.user.enums.UserRole;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MesaService {

    private final MesaRepository mesaRepository;
    private final MesaMapper mesaMapper;
    private final JwtService jwtService;
    private final WaiterService waiterService;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<ResponseMesaDTO> createMesa(CreateMesaDTO createMesaDTO, String token) {
        String tokenUser = token.substring(7);
        Integer userId = jwtService.extractClaimId(tokenUser, "id");
        MesaEntity mesa = new MesaEntity();

        WaiterDTO waiterDTO =
                waiterService.getWaiterUserById(userId.longValue());


        if (waiterDTO != null) {
            UserEntity user = userRepository.findById(userId.longValue())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (user.getRole() == UserRole.WAITER) {
                UserEntity adminId = userRepository.findById(waiterDTO.getAdminId())
                        .orElseThrow(() -> new RuntimeException("Admin not found"));
                mesa.setAdmin(adminId);
                mesa.setCreatedBy(user);
            } else {
                mesa.setCreatedBy(user);
                mesa.setAdmin(user);
            }
        }

        mesa.setNumber(createMesaDTO.getNumber());
        mesa.setStatus(createMesaDTO.getStatus());
        mesa.setTitular(createMesaDTO.getTitular());

        mesa = mesaRepository.save(mesa);

        return ResponseEntity.ok(mesaMapper.entityToResponse(mesa));
    }
}
