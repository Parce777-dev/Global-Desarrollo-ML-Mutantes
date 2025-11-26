package org.example.service;

import org.example.entity.DnaRecord;
import org.example.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MutantService {

    private final MutantDetector mutantDetector;
    private final DnaRecordRepository dnaRecordRepository;

    public boolean analyzeDna(String[] dna) {
        // 1. Convertimos el array a String para usarlo como "Hash" (identificador único)
        String dnaHash = String.join(",", dna);

        // 2. Verificamos si ya existe en la Base de Datos
        Optional<DnaRecord> existingRecord = dnaRecordRepository.findByDna(dnaHash);
        if (existingRecord.isPresent()) {
            return existingRecord.get().isMutant();
        }

        // 3. Si no existe, calculamos
        boolean isMutant = mutantDetector.isMutant(dna);

        // 4. Guardamos el resultado
        DnaRecord newRecord = DnaRecord.builder()
                .dna(dnaHash)
                .isMutant(isMutant)
                .build();
        dnaRecordRepository.save(newRecord);

        return isMutant;
    }
}