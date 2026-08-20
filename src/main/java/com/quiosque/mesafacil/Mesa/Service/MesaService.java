package com.quiosque.mesafacil.Mesa.Service;

import com.quiosque.mesafacil.Config.JwtService;
import com.quiosque.mesafacil.Mesa.Entity.MesaEntity;
import com.quiosque.mesafacil.Mesa.Repository.MesaRepository;
import com.quiosque.mesafacil.Mesa.dtos.CreateMesaDTO;
import com.quiosque.mesafacil.Mesa.dtos.ResponseMesaDTO;
import com.quiosque.mesafacil.Mesa.mapper.MesaMapper;
import com.quiosque.mesafacil.User.DTOs.WaiterDTO;
import com.quiosque.mesafacil.User.Entity.UserEntity;
import com.quiosque.mesafacil.User.Repository.UserRepository;
import com.quiosque.mesafacil.User.Repository.WaiterRepository;
import com.quiosque.mesafacil.User.Service.WaiterService;
import com.quiosque.mesafacil.User.enums.UserRole;
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
