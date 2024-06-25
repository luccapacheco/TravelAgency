package com.example.travelagency.controller;

import com.example.travelagency.model.Destino;
import com.example.travelagency.service.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {
    @Autowired
    private DestinoService destinoService;

    @PostMapping
    public ResponseEntity<Destino> cadastrarDestino(@RequestBody Destino destino) {
        Destino novoDestino = destinoService.save(destino);
        return ResponseEntity.ok(novoDestino);
    }

    @GetMapping
    public ResponseEntity<List<Destino>> listarDestinos() {
        List<Destino> destinos = destinoService.findAll();
        return ResponseEntity.ok(destinos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Destino>> pesquisarDestinos(@RequestParam String termo) {
        List<Destino> destinos = destinoService.search(termo);
        return ResponseEntity.ok(destinos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> visualizarDestino(@PathVariable Long id) {
        Destino destino = destinoService.findById(id).orElseThrow(() -> new RuntimeException("Destino não encontrado"));
        return ResponseEntity.ok(destino);
    }

    @PostMapping("/{id}/avaliar")
    public ResponseEntity<Destino> avaliarDestino(@PathVariable Long id, @RequestParam int nota) {
        Destino destinoAvaliado = destinoService.avaliarDestino(id, nota);
        return ResponseEntity.ok(destinoAvaliado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDestino(@PathVariable Long id) {
        destinoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
