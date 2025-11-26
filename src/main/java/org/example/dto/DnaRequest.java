package org.example.dto;

import lombok.Data;
import org.example.validation.ValidDnaSequence;
import java.io.Serializable;

@Data
public class DnaRequest implements Serializable {

    @ValidDnaSequence // ¡Esta es la validación personalizada que da puntos extra!
    private String[] dna;
}