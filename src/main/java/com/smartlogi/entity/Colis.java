package com.smartlogi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "Colis")
public class Colis{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String vehicle;

    @Column(name = "phone_number",nullable = false,unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "courier",cascade = CascadeType.ALL)
    private List<> deliveries;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Colis(String lastName, String firstName, String vehicle, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.vehicle = vehicle;
        this.phoneNumber = phoneNumber;
    }
}
