package com.quiosque.mesafacil.table.controller;

import com.quiosque.mesafacil.table.service.TableService;
import com.quiosque.mesafacil.table.dtos.CreateTableDTO;
import com.quiosque.mesafacil.table.dtos.ResponseTableDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/mesa")
public class TableController {

    private final TableService mesaService;

    @GetMapping
    public List<ResponseTableDTO> getTable() {
        return mesaService.getAllTable();
    }

    @PostMapping
    public ResponseEntity<ResponseTableDTO> postTable(@RequestBody CreateTableDTO mesas, @RequestHeader("Authorization") String token) {
        return mesaService.createTable(mesas, token);
    }
}
