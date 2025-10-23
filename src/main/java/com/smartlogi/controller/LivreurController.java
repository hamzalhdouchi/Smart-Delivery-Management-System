package com.smartlogi.controller;

import com.smartlogi.entity.Livreur;
import com.smartlogi.sevice.LivreurService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livreurs")
public class LivreurController {

    private final LivreurService livreurService;

    public LivreurController(LivreurService livreurService) {
        this.livreurService = livreurService;
    }


    @PostMapping
    public ResponseEntity<Livreur> createLivreur(@RequestBody Livreur livreur) {
        try {
            Livreur savedLivreur = livreurService.saveLivreur(livreur);
            return new ResponseEntity<>(savedLivreur ,HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Livreur> getLivreurById(@PathVariable("id") Long id) {
        Optional<Livreur> livreurOptional = livreurService.getLivreurById(id);

        return livreurOptional
                .map(livreur -> ResponseEntity.ok(livreur))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<Livreur>> getAllLivreurs() {
        List<Livreur> livreurs = livreurService.getAllLivreurs();
        return ResponseEntity.ok(livreurs);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Livreur> updateLivreur(@PathVariable("id") Long id, @RequestBody Livreur livreurDetails) {
        try {
            Livreur updatedLivreur = livreurService.updateLivreur(id, livreurDetails);
            return ResponseEntity.ok(updatedLivreur);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLivreur(@PathVariable("id") Long id) {
        try {
            if (!livreurService.getLivreurById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            livreurService.deleteLivreur(id);
            return ResponseEntity.ok("the s");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}