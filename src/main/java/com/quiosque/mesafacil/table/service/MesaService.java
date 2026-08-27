package com.quiosque.mesafacil.table.service;

import com.quiosque.mesafacil.configs.JwtService;
import com.quiosque.mesafacil.table.entity.MesaEntity;
import com.quiosque.mesafacil.table.enums.Status;
import com.quiosque.mesafacil.table.repository.MesaRepository;
import com.quiosque.mesafacil.table.dtos.CreateMesaDTO;
import com.quiosque.mesafacil.table.dtos.ResponseMesaDTO;
import com.quiosque.mesafacil.table.mapper.MesaMapper;
import com.quiosque.mesafacil.user.dto.WaiterDTO;
import com.quiosque.mesafacil.user.entity.UserEntity;
import com.quiosque.mesafacil.user.repository.UserRepository;
import com.quiosque.mesafacil.user.service.WaiterService;
import com.quiosque.mesafacil.user.enums.UserRole;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

        MesaEntity mesaExists = mesaRepository.findByNumber(createMesaDTO.getNumber());


        if (mesaExists != null && mesaExists.getStatus().equals(Status.ABERTO)) {
            throw new RuntimeException(
                    "Já existe uma mesa " + createMesaDTO.getNumber() + " aberta"
            );
        }


        UserEntity user = userRepository.findById(userId.longValue())
                .orElseThrow(() -> new RuntimeException("User not found"));

        mesa.setCreatedBy(user);

        if (user.getRole() == UserRole.WAITER) {
            WaiterDTO waiterDTO = waiterService.getWaiterUserById(user.getId());

            if (waiterDTO == null) {
                return ResponseEntity.badRequest().build();
            }

            UserEntity admin = userRepository.findById(waiterDTO.getAdminId())
                    .orElseThrow(() -> new RuntimeException("Admin not found"));

            mesa.setAdmin(admin);
        } else if (user.getRole() == UserRole.ADMIN) {
            mesa.setAdmin(user);
        } else {
            return ResponseEntity.badRequest().build();
        }

        mesa.setNumber(createMesaDTO.getNumber());
        mesa.setStatus(createMesaDTO.getStatus());
        mesa.setTitular(createMesaDTO.getTitular());

        mesa = mesaRepository.save(mesa);

        return ResponseEntity.ok(mesaMapper.entityToResponse(mesa));
    }

    public List<ResponseMesaDTO> getAllMesa(){
        return mesaRepository.findAll().stream().map(mesaMapper::entityToResponse).toList();
    }
}
