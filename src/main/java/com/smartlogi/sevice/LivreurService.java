package com.smartlogi.sevice;

import com.smartlogi.entity.Livreur;

import java.util.List;
import java.util.Optional;

public interface LivreurService {
    Livreur saveLivreur(Livreur livreur);

    Optional<Livreur> getLivreurById(Long id);

    List<Livreur> getAllLivreurs();

    void deleteLivreur(Long id);

    Livreur updateLivreur(Long id, Livreur livreur);
}