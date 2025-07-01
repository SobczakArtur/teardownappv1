package pl.sobczakartur.teardownappv1.mainelectronics.substrates.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.AssemblyBlocks;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.exception.ResourceNotFoundException;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.repository.SubstrateRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubstrateServiceImplTest {

    @Mock
    private SubstrateRepository substrateRepository;

    @InjectMocks
    private SubstrateServiceImpl substrateServiceImpl;

    @Test
    void shouldReturnAllSubstrates() {
        List<Substrate> substrates = List.of(Substrate.builder()
                                                    .substrateId(1L)
                                                    .assemblyName("Substrate A")
                                                    .substrateMarking("Description A")
                                                    .build(),
                                            Substrate.builder()
                                                    .substrateId(2L)
                                                    .assemblyName("Substrate B")
                                                    .substrateMarking("Description B")
                                                    .build());

        when(substrateRepository.findAll()).thenReturn(substrates);

        List<Substrate> result = substrateServiceImpl.getAllSubstrate();

        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnSubstrateById() {
        Long substrateId = 1L;
        Substrate substrate = new Substrate();
        substrate.setSubstrateId(substrateId);

        when(substrateRepository.findById(substrateId)).thenReturn(Optional.of(substrate));

        Optional<Substrate> result = substrateServiceImpl.getSubstrateById(substrateId);

        assertTrue(result.isPresent());
        assertEquals(substrateId, result.get().getSubstrateId());
    }

    @Test
    void shouldThrowExceptionWhenSubstrateNotFound() {
        Long substrateId = 999L;
        when(substrateRepository.findById(substrateId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> substrateServiceImpl.getSubstrateById(substrateId));
    }

    @Test
    void shouldAddSubstrate() {

        Substrate substrate = Substrate.builder()
                                        .substrateId(1L)
                                        .assemblyName("New Substrate")
                                        .build();

        when(substrateRepository.save(any())).thenReturn(substrate);

        Optional<Substrate> result = substrateServiceImpl.addSubstrate(substrate);

        assertTrue(result.isPresent());
        assertEquals("New Substrate", result.get().getAssemblyName());
    }

    @Test
    void shouldUpdateSubstrate() {

        Substrate existingSubstrate = Substrate.builder()
                                            .substrateId(1L)
                                            .assemblyName("Old Substrate")
                                            .build();

        Substrate updatedSubstrate = Substrate.builder()
                                            .substrateId(1L)
                                            .assemblyName("Updated Substrate")
                                            .assemblyBlocks(List.of(
                                                    AssemblyBlocks.builder().build(),
                                                    AssemblyBlocks.builder().build()
                                            ))
                                            .build();

        when(substrateRepository.findById(1L)).thenReturn(Optional.of(existingSubstrate));
        when(substrateRepository.save(updatedSubstrate)).thenReturn(updatedSubstrate);

        Optional<Substrate> result = substrateServiceImpl.updatedSubstrate(updatedSubstrate, 1L);

        assertTrue(result.isPresent());
        assertEquals("Updated Substrate", result.get().getAssemblyName());
        assertNotNull(result.get().getAssemblyBlocks());
        result.get().getAssemblyBlocks()
                .forEach(block -> assertEquals(updatedSubstrate, block.getSubstrate())
        );
    }

    @Test
    void shouldPartiallyUpdateSubstrate() {

        Substrate existingSubstrate = Substrate.builder()
                                            .substrateId(1L)
                                            .assemblyName("Old Substrate")
                                            .substrateMarking("Old Marking")
                                            .build();

        Substrate updatedSubstrate = Substrate.builder()
                                            .substrateId(1L)
                                            .assemblyName("Partially Updated Substrate")
                                            .build();

        when(substrateRepository.findById(1L)).thenReturn(Optional.of(existingSubstrate));
        when(substrateRepository.save(existingSubstrate)).thenReturn(existingSubstrate);

        Optional<Substrate> result = substrateServiceImpl.partiallyUpdatedSubstrate(updatedSubstrate, 1L);

        assertTrue(result.isPresent());
        assertEquals("Partially Updated Substrate", result.get().getAssemblyName());
        assertEquals("Old Marking", result.get().getSubstrateMarking());
    }

    @Test
    void shouldDeleteSubstrate() {

        Substrate substrate = Substrate.builder()
                                    .substrateId(1L)
                                    .assemblyName("Deleted Substrate")
                                    .build();

        when(substrateRepository.findById(1L)).thenReturn(Optional.of(substrate));

        Optional<Substrate> result = substrateServiceImpl.removeSubstrateById(1L);

        assertTrue(result.isPresent());
        assertEquals("Deleted Substrate", result.get().getAssemblyName());
        verify(substrateRepository, times(1)).deleteById(1L);
    }
}
