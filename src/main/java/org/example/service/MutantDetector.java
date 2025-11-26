package org.example.service;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class MutantDetector {

    private static final int SEQUENCE_LENGTH = 4;
    private static final Pattern VALID_DNA_PATTERN = Pattern.compile("^[ATCG]+$");

    public boolean isMutant(String[] dna) {
        if (dna == null || dna.length == 0) throw new IllegalArgumentException("DNA cannot be null or empty");

        int n = dna.length;
        if (dna[0] == null) throw new IllegalArgumentException("First row cannot be null"); // Protección extra
        if (dna[0].length() != n) throw new IllegalArgumentException("DNA must be a square matrix (NxN)");

        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            // 👇 ¡AQUÍ ESTABA EL PROBLEMA! Agregamos esta línea:
            if (dna[i] == null) throw new IllegalArgumentException("Row cannot be null");

            if (dna[i].length() != n) throw new IllegalArgumentException("All rows must have same length");
            if (!VALID_DNA_PATTERN.matcher(dna[i]).matches()) throw new IllegalArgumentException("DNA contains invalid characters");
            matrix[i] = dna[i].toCharArray();
        }

        int sequenceCount = 0;

        // Recorremos la matriz...
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // Horizontal
                if (j <= n - SEQUENCE_LENGTH) {
                    if (checkLine(matrix, i, j, 0, 1)) {
                        sequenceCount++;
                        if (sequenceCount > 1) return true;
                    }
                }

                // Vertical
                if (i <= n - SEQUENCE_LENGTH) {
                    if (checkLine(matrix, i, j, 1, 0)) {
                        sequenceCount++;
                        if (sequenceCount > 1) return true;
                    }
                }

                // Diagonal Principal (↘)
                if (i <= n - SEQUENCE_LENGTH && j <= n - SEQUENCE_LENGTH) {
                    if (checkLine(matrix, i, j, 1, 1)) {
                        sequenceCount++;
                        if (sequenceCount > 1) return true;
                    }
                }

                // Diagonal Invertida (↙)
                if (i <= n - SEQUENCE_LENGTH && j >= SEQUENCE_LENGTH - 1) {
                    if (checkLine(matrix, i, j, 1, -1)) {
                        sequenceCount++;
                        if (sequenceCount > 1) return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkLine(char[][] matrix, int row, int col, int dRow, int dCol) {
        char first = matrix[row][col];
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (matrix[row + i * dRow][col + i * dCol] != first) {
                return false;
            }
        }
        return true;
    }
}