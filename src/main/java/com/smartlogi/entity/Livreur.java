package com.smartlogi.entity;

import com.smartlogi.entity.Colis;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "livreur")
public class Livreur {
    @Id
    private Long id;

    private String nom;

    private String prenom;

    private String vehicule;


    private String telephone;


    private String email;

    @OneToMany(mappedBy = "livreur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Colis> colisList = new ArrayList<>();

}