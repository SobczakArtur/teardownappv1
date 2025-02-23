package pl.sobczakartur.teardownappv1.mainelectronics.substrates.sevice;

import pl.sobczakartur.teardownappv1.mainelectronics.substrates.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.SubstrateCost;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.TechnologyEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;

import java.util.List;
import java.util.Optional;

public interface SubstrateService {


    List<Substrate> getAllSubstrate();

    Optional<Substrate> getSubstrateById(Long substrateId);

    Optional<Substrate>  addSubstrate(Substrate substrate);

    Optional<Substrate>  updateSubstrate(SubstrateCost substrateTestCost, ComplexityEnum complexityEnum,
                              TechnologyEnum technologyEnum, Substrate substrate, Long substrateId);

    Optional<Substrate>  removeSubstrateById(Long substrateId);
}
