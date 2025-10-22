package com.smartlogi.sevice.impl;

import com.smartlogi.entity.Livreur;
import com.smartlogi.repository.LivreurRepository;
import com.smartlogi.sevice.LivreurService;

import java.util.List;
import java.util.Optional;

public class LivreurServiceImpl implements LivreurService {

    private LivreurRepository livreurRepository;

    public LivreurServiceImpl(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    @Override
    public Livreur saveLivreur(Livreur livreur) {
        if (livreur.getTelephone() == null || livreur.getTelephone().isEmpty()) {
            throw new IllegalArgumentException("Telephone is required");
        }
        return livreurRepository.save(livreur);
    }

    @Override
    public Optional<Livreur> getLivreurById(Long id) {
        return livreurRepository.findById(id);
    }

    @Override
    public List<Livreur> getAllLivreurs() {
        return livreurRepository.findAll();
    }

    @Override
    public void deleteLivreur(Long id) {
        livreurRepository.deleteById(id);
    }

    @Override
    public Livreur updateLivreur(Long id, Livreur livreur) {
        Livreur livreur1 = livreurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livreur not found with ID: " + id));

        if (livreur.getNom() != null) {
            livreur1.setNom(livreur.getNom());
        }
        if (livreur.getPrenom() != null) {
            livreur1.setPrenom(livreur.getPrenom());
        }
        if (livreur.getTelephone() != null) {
            livreur1.setTelephone(livreur.getTelephone());
        }
        if (livreur.getEmail() != null) {
            livreur1.setEmail(livreur.getEmail());
        }
        if (livreur.getVehicule() != null) {
            livreur1.setVehicule(livreur.getVehicule());
        }
        return livreurRepository.save(livreur1);
    }
}