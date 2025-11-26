package org.example.service;

import org.example.entity.DnaRecord;
import org.example.repository.DnaRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MutantServiceTest {

    @Mock
    private MutantDetector mutantDetector;

    @Mock
    private DnaRecordRepository dnaRecordRepository;

    @InjectMocks
    private MutantService mutantService;

    @Test
    void testAnalyzeDna_NewAnalysis() {
        // Caso: No existe en BD
        when(dnaRecordRepository.findByDna(anyString())).thenReturn(Optional.empty());
        when(mutantDetector.isMutant(any())).thenReturn(true);

        boolean result = mutantService.analyzeDna(new String[]{"AAAA"});

        assertTrue(result);
        verify(dnaRecordRepository, times(1)).save(any()); // Verifica que guardó
    }

    @Test
    void testAnalyzeDna_CacheHit() {
        // Caso: YA existe en BD (Cache)
        DnaRecord existingRecord = new DnaRecord();
        existingRecord.setMutant(true);

        when(dnaRecordRepository.findByDna(anyString())).thenReturn(Optional.of(existingRecord));

        boolean result = mutantService.analyzeDna(new String[]{"AAAA"});

        assertTrue(result);
        // IMPORTANTE: Verifica que NO llamó al detector ni guardó de nuevo
        verify(mutantDetector, never()).isMutant(any());
        verify(dnaRecordRepository, never()).save(any());
    }
}