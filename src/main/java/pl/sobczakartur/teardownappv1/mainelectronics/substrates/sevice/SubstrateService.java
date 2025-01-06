package pl.sobczakartur.teardownappv1.mainelectronics.substrates.sevice;

import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;

import java.util.List;

public interface SubstrateService {

    Substrate saveSubstrate(Substrate substrate);

    List<Substrate> fetchAllSubstrate();

    Substrate updateSubstrate(Substrate substrate, Long substrateId);

    void deleteSubstrateById(Long substrateId);
}
