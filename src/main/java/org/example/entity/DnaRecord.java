package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "dna_records")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DnaRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String dna; // Guardaremos el hash del ADN aquí para buscar rápido

    private boolean isMutant;
}