package com.quiosque.mesafacil.table.controller;

import com.quiosque.mesafacil.table.service.MesaService;
import com.quiosque.mesafacil.table.dtos.CreateMesaDTO;
import com.quiosque.mesafacil.table.dtos.ResponseMesaDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/mesa")
public class MesaController {

    private final MesaService mesaService;

    @GetMapping
    public List<ResponseMesaDTO> getMesa() {
        return mesaService.getAllMesa();
    }

    @PostMapping
    public ResponseEntity<ResponseMesaDTO> postMesa(@RequestBody CreateMesaDTO mesas, @RequestHeader("Authorization") String token) {
        return mesaService.createMesa(mesas, token);
    }
}
