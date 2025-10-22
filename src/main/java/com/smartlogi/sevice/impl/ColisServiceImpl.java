package com.smartlogi.sevice.impl;
// No Spring annotations needed here anymore
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

import com.smartlogi.entity.Colis;
import com.smartlogi.enums.ColisStatus;
import com.smartlogi.repository.ColisRepository;
import com.smartlogi.sevice.ColisService;

import java.util.List;
import java.util.Optional;

public class ColisServiceImpl implements ColisService {

    private ColisRepository colisRepository;

    public void setColisRepository(ColisRepository colisRepository) {
        this.colisRepository = colisRepository;
    }

    @Override
    public Colis saveColis(Colis colis) {
        if (colis.getDestinataire() == null || colis.getDestinataire().isEmpty()) {
            throw new IllegalArgumentException("Destinataire is required");
        }
        return colisRepository.save(colis);
    }

    @Override
    public Optional<Colis> getColisById(Long id) {
        return colisRepository.findById(id);
    }

    @Override
    public List<Colis> getAllColis() {
        return colisRepository.findAll();
    }

    @Override
    public List<Colis> getColisByLivreurId(Long livreurId) {
        return colisRepository.findByLivreurId(livreurId);
    }

    @Override
    public void deleteColis(Long id) {
        colisRepository.deleteById(id);
    }

    @Override
    public void updateColisStatut(Long id, ColisStatus statut) {
        Colis colis = colisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Colis not found"));
        colis.setStatut(statut);
        colisRepository.save(colis);
    }
}