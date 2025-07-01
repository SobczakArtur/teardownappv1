package pl.sobczakartur.teardownappv1.mainelectronics.substrates.service;

import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;

import java.util.List;
import java.util.Optional;

public interface SubstrateService {


    List<Substrate> getAllSubstrate();

    Optional<Substrate> getSubstrateById(Long substrateId);

    Optional<Substrate>  addSubstrate(Substrate substrate);

    Optional<Substrate>  updatedSubstrate(Substrate substrateToUpdate, Long substrateId);

    Optional<Substrate>  partiallyUpdatedSubstrate(Substrate updatedSubstrate, Long substrateId);

    Optional<Substrate>  removeSubstrateById(Long substrateId);
}
