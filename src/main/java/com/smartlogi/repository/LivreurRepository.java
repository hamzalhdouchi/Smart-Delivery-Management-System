package com.smartlogi.repository;

import com.smartlogi.entity.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreurRepository extends JpaRepository<Livreur, Long> {
    Long id(Long id);
}