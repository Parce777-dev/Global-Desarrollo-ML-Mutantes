package org.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidDnaSequenceValidator implements ConstraintValidator<ValidDnaSequence, String[]> {

    private static final Pattern VALID_CHARACTERS = Pattern.compile("^[ATCG]+$");

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {
        if (dna == null || dna.length == 0) return false; // No nulo ni vacío

        int n = dna.length; // Tamaño esperado (NxN)

        for (String row : dna) {
            if (row == null) return false; // Fila nula
            if (row.length() != n) return false; // No es cuadrada
            if (!VALID_CHARACTERS.matcher(row).matches()) return false; // Caracteres inválidos
        }
        return true;
    }
}