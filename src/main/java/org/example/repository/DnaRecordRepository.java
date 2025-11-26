package org.example.repository;

import org.example.entity.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {

    // Busca por la secuencia (que será nuestro hash)
    Optional<DnaRecord> findByDna(String dna);

    // Cuenta mutantes o humanos para las estadísticas
    long countByIsMutant(boolean isMutant);
}