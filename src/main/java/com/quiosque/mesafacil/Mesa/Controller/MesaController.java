package com.quiosque.mesafacil.Mesa.Controller;

import com.quiosque.mesafacil.Mesa.Entity.MesaEntity;
import com.quiosque.mesafacil.Mesa.Service.MesaService;
import com.quiosque.mesafacil.Mesa.dtos.CreateMesaDTO;
import com.quiosque.mesafacil.Mesa.dtos.ResponseMesaDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/mesa")
public class MesaController {

    private final MesaService mesaService;

    @GetMapping
    public String getMesa() {
        return "Mesa controller";
    }

    @PostMapping
    public ResponseEntity<ResponseMesaDTO> postMesa(@RequestBody CreateMesaDTO mesas, @RequestHeader("Authorization") String token) {
        return mesaService.createMesa(mesas, token);
    }
}
