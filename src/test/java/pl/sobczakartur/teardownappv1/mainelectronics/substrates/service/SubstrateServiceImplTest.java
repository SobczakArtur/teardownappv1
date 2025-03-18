package pl.sobczakartur.teardownappv1.mainelectronics.substrates.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.exception.ResourceNotFoundException;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.repository.SubstrateRepository;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.sevice.SubstrateServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubstrateServiceImplTest {


    @Mock
    private SubstrateRepository substrateRepository;

    @InjectMocks
    private SubstrateServiceImpl substrateServiceImpl;

    @Test
    void shouldReturnSubstrateWhenFoundById() {

        // Arrange
        Long substrateId = 1L;
        Substrate substrate = new Substrate();
        substrate.setSubstrateId(substrateId);

        when(substrateRepository.findById(substrateId)).thenReturn(Optional.of(substrate));

        // Act
        Optional<Substrate> result = substrateServiceImpl.getSubstrateById(substrateId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(substrateId, result.get().getSubstrateId());
    }

    @Test
    void shouldThrowExceptionWhenSubstrateNotFound() {
        // Arrange
        Long substrateId = 1L;
        when(substrateRepository.findById(substrateId)).thenReturn(Optional.empty());

//         Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> substrateServiceImpl.getSubstrateById(substrateId));
    }
}
