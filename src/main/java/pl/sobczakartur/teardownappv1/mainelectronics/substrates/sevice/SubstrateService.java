package pl.sobczakartur.teardownappv1.mainelectronics.substrates.sevice;

import pl.sobczakartur.teardownappv1.mainelectronics.substrates.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.SubstrateTestCost;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.TechnologyEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;

import java.util.List;

public interface SubstrateService {

    Substrate saveSubstrate(Substrate substrate);

    List<Substrate> fetchAllSubstrate();

    Substrate updateSubstrate(SubstrateTestCost substrateTestCost, ComplexityEnum complexityEnum,
                              TechnologyEnum technologyEnum, Substrate substrate, Long substrateId);

    void deleteSubstrateById(Long substrateId);
}
