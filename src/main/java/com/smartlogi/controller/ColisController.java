package com.smartlogi.controller;

import com.smartlogi.dto.CreateColisRequest;
import com.smartlogi.entity.Colis;
import com.smartlogi.enums.ColisStatus;
import com.smartlogi.sevice.ColisService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/colis")
public class ColisController {

    private final ColisService colisService;

    public ColisController(ColisService colisService) {
        this.colisService = colisService;
    }

    @PostMapping
    public ResponseEntity<Colis> createColis(@RequestBody CreateColisRequest colisDTO) {
        try {
            Colis savedColis = colisService.saveColis(colisDTO);
            // Return 201 Created status
            return new ResponseEntity<>(savedColis, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handle validation error from service (Destinataire required)
            return new ResponseEntity<>((HttpHeaders)null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colis> getColisById(@PathVariable("id") Long id) {
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
    public ResponseEntity<Void> deleteColis(@PathVariable("id") Long id) {
        if (!colisService.getColisById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        colisService.deleteColis(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/livreur/{livreurId}")
    public ResponseEntity<List<Colis>> getColisByLivreur(@PathVariable("livreurId") Long livreurId) {
        List<Colis> colisList = colisService.getColisByLivreurId(livreurId);
        return ResponseEntity.ok(colisList);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateColisStatus(@PathVariable("id") Long id, @RequestBody String status) {
        try {
            ColisStatus statut = ColisStatus.valueOf(status);
            colisService.updateColisStatut(id,statut);
            return ResponseEntity.ok("the status is change successfully");
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Colis not found")) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>("error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}