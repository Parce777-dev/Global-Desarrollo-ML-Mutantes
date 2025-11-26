package org.example.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class validationTest {

    @Mock
    private ConstraintValidatorContext context;

    private final ValidDnaSequenceValidator validator = new ValidDnaSequenceValidator();

    @Test
    void testValidDna() {
        String[] dna = {"ATCG", "ATCG", "ATCG", "ATCG"};
        assertTrue(validator.isValid(dna, context));
    }

    @Test
    void testNullDna() {
        assertFalse(validator.isValid(null, context));
    }

    @Test
    void testEmptyDna() {
        assertFalse(validator.isValid(new String[]{}, context));
    }

    @Test
    void testDnaWithNullRow() {
        // Este caso específico faltaba para el 100% en validación
        String[] dna = {"ATCG", null, "ATCG", "ATCG"};
        assertFalse(validator.isValid(dna, context));
    }

    @Test
    void testInvalidLength() {
        String[] dna = {"ATC", "ATCG"};
        assertFalse(validator.isValid(dna, context));
    }

    @Test
    void testInvalidChars() {
        String[] dna = {"ATCX", "ATCG", "ATCG", "ATCG"};
        assertFalse(validator.isValid(dna, context));
    }
}