package com.quiosque.mesafacil.table.service;

import com.quiosque.mesafacil.configs.JwtService;
import com.quiosque.mesafacil.table.entity.TableEntity;
import com.quiosque.mesafacil.table.enums.Status;
import com.quiosque.mesafacil.table.repository.TableRepository;
import com.quiosque.mesafacil.table.dtos.CreateTableDTO;
import com.quiosque.mesafacil.table.dtos.ResponseTableDTO;
import com.quiosque.mesafacil.table.mapper.TableMapper;
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
public class TableService {

    private final TableRepository mesaRepository;
    private final TableMapper mesaMapper;
    private final JwtService jwtService;
    private final WaiterService waiterService;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<ResponseTableDTO> createTable(CreateTableDTO createTableDTO, Long userId) {
        TableEntity mesa = new TableEntity();

        TableEntity mesaExists = mesaRepository.findByNumber(createTableDTO.getNumber());


        if (mesaExists != null && mesaExists.getStatus().equals(Status.ABERTO)) {
            throw new RuntimeException(
                    "Já existe uma mesa " + createTableDTO.getNumber() + " aberta"
            );
        }


        UserEntity user = userRepository.findById(userId)
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

        mesa.setNumber(createTableDTO.getNumber());
        mesa.setStatus(createTableDTO.getStatus());
        mesa.setTitular(createTableDTO.getTitular());

        mesa = mesaRepository.save(mesa);

        return ResponseEntity.ok(mesaMapper.entityToResponse(mesa));
    }

    public List<ResponseTableDTO> getAllTable(){
        return mesaRepository.findAll().stream().map(mesaMapper::entityToResponse).toList();
    }
}
