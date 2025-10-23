package com.smartlogi.dto;

import com.smartlogi.enums.ColisStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreateColisRequest {


    private String destinataire;

    private String adresse;

    private double poids;

    private ColisStatus statut;

    private Long idLivreur;
}

