package com.smartlogi.controller;

import com.smartlogi.entity.Colis;
import com.smartlogi.enums.ColisStatus;
import com.smartlogi.sevice.ColisService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/colis")
public class ColisController {

    private final ColisService colisService;

    // Use constructor injection for dependencies
    public ColisController(ColisService colisService) {
        this.colisService = colisService;
    }

    @PostMapping
    public ResponseEntity<Colis> createColis(@RequestBody Colis colis) {
        try {
            Colis savedColis = colisService.saveColis(colis);
            return new ResponseEntity<>(savedColis, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colis> getColisById(@PathVariable Long id) {
        Optional<Colis> colisOptional = colisService.getColisById(id);

        // Return 200 OK if found, 404 Not Found otherwise
        return colisOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Colis>> getAllColis() {
        List<Colis> colisList = colisService.getAllColis();
        return ResponseEntity.ok(colisList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColis(@PathVariable Long id) {
        if (!colisService.getColisById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        colisService.deleteColis(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/livreur/{livreurId}")
    public ResponseEntity<List<Colis>> getColisByLivreur(@PathVariable Long livreurId) {
        List<Colis> colisList = colisService.getColisByLivreurId(livreurId);
        return ResponseEntity.ok(colisList);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateColisStatus(@PathVariable Long id, @RequestParam ColisStatus statut) {
        try {
            colisService.updateColisStatut(id, statut);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Colis not found")) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}