package org.example.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MutantDetectorTest {

    private final MutantDetector detector = new MutantDetector();

    @Test
    void testMutantHorizontal() {
        String[] dna = {"AAAA", "CCCC", "TCAG", "GGTC"};
        assertTrue(detector.isMutant(dna));
    }

    @Test
    void testMutantVertical() {
        String[] dna = {"ATCG", "ATCG", "ATCG", "ATCG"};
        assertTrue(detector.isMutant(dna));
    }

    @Test
    void testMutantDiagonal() {
        String[] dna = {
                "ATCG",
                "GAGG",
                "TGAG",
                "GGGA"
        };
        assertTrue(detector.isMutant(dna));
    }

    @Test
    void testHumanOnlyOneSequence() {
        String[] dna = {"AAAA", "GTCA", "TGAC", "GTCG"};
        assertFalse(detector.isMutant(dna));
    }

    @Test
    void testHumanNoSequence() {
        String[] dna = {"ATCG", "GTCA", "TGAC", "GTCG"};
        assertFalse(detector.isMutant(dna));
    }

    @Test
    void testInvalidNull() {
        assertThrows(IllegalArgumentException.class, () -> detector.isMutant(null));
    }

    @Test
    void testInvalidEmpty() {
        assertThrows(IllegalArgumentException.class, () -> detector.isMutant(new String[]{}));
    }

    @Test
    void testInvalidNxM() {
        String[] dna = {"ATC", "GTC"};
        assertThrows(IllegalArgumentException.class, () -> detector.isMutant(dna));
    }

    @Test
    void testInvalidCharacters() {
        String[] dna = {"ATCG", "ATCX", "ATCG", "ATCG"};
        assertThrows(IllegalArgumentException.class, () -> detector.isMutant(dna));
    }

    @Test
    void testNullRow() {
        // Este es el test nuevo para el 100%
        String[] dna = {"ATCG", null, "ATCG", "ATCG"};
        assertThrows(IllegalArgumentException.class, () -> detector.isMutant(dna));
    }
}