package com.smartlogi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Colis {
    @Id
    private Long id;

    private String destinataire;

    private String adresse;

    private double poids;

    private String statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livreur_id")
    private Livreur livreur;
}