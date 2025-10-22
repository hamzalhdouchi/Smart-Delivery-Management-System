package com.smartlogi.sevice;

import com.smartlogi.entity.Colis;
import com.smartlogi.enums.ColisStatus;

import java.util.List;
import java.util.Optional;

public interface ColisService {
    Colis saveColis(Colis colis);
    Optional<Colis> getColisById(Long id);
    List<Colis> getAllColis();
    List<Colis> getColisByLivreurId(Long livreurId);
    void deleteColis(Long id);
    void updateColisStatut(Long id, ColisStatus statut);
}