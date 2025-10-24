package com.smartlogi.sevice.impl;
// No Spring annotations needed here anymore
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

import com.smartlogi.dto.CreateColisRequest;
import com.smartlogi.entity.Colis;
import com.smartlogi.entity.Livreur;
import com.smartlogi.enums.ColisStatus;
import com.smartlogi.repository.ColisRepository;
import com.smartlogi.repository.LivreurRepository;
import com.smartlogi.sevice.ColisService;

import java.util.List;
import java.util.Optional;

public class ColisServiceImpl implements ColisService {

    private ColisRepository colisRepository;
    private LivreurRepository livreurRepository;

    public void setColisRepository(ColisRepository colisRepository) {
        this.colisRepository = colisRepository;
    }

    public void setLivreurRepository(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    @Override
    public Colis saveColis(CreateColisRequest colisDTO) {
        if (colisDTO.getIdLivreur() == null) {
            throw new IllegalArgumentException("Livreur id is required");
        }

        Livreur livreur = livreurRepository.findById(colisDTO.getIdLivreur())
                .orElseThrow(() -> new IllegalArgumentException("Livreur id not found"));
        Colis colis = new Colis();
        colis.setLivreur(livreur);
        colis.setStatut(colisDTO.getStatut());
        colis.setPoids(colisDTO.getPoids());
        colis.setDestinataire(colisDTO.getDestinataire());
        colis.setAdresse(colisDTO.getAdresse());
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