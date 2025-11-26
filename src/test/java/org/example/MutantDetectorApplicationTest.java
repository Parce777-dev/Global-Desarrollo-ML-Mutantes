package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MutantDetectorApplicationTest {

    @Test
    void contextLoads() {
        // Este test verifica que Spring levante bien.
        // Al ejecutar el main, cubre la clase principal.
        MutantDetectorApplication.main(new String[]{});
    }
}