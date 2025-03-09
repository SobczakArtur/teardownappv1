package pl.sobczakartur.teardownappv1.mainelectronics.substrates.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.repository.SubstrateRepository;

import java.util.List;
import java.util.Optional;


@Service
public class SubstrateServiceImpl implements SubstrateService {

        @Autowired
        private SubstrateRepository substrateRepository;



        @Override
        public List<Substrate> getAllSubstrate() {
            return substrateRepository.findAll();
        }

        @Override
        public Optional<Substrate> getSubstrateById(Long substrateId){
            return substrateRepository.findById(substrateId);
        }

        @Override
        public Optional<Substrate> addSubstrate(Substrate substrate) {
            return Optional.of(substrateRepository.save(substrate));
        }


        @Override
        public Optional<Substrate> updatedSubstrate(Substrate substrateToUpdate, Long substrateId) {

                Optional<Substrate> substrate = getSubstrateById(substrateId);
                if (substrate.isPresent()){
                    Substrate updated = substrateRepository.save(substrateToUpdate);
                    return Optional.of(updated);
            }
            return Optional.empty();
        }

    @Override
    public Optional<Substrate> partiallyUpdatedSubstrate(Substrate substrateToUpdate, Long substrateId) {

//        boolean substrateIsPresent = substrateRepository.findById(substrateId).isPresent();
//        Substrate substrDB = substrateRepository.findById(substrateId)
//                .orElseThrow(() -> new EntityNotFoundException("Substrate not found"));

        return substrateRepository.findById(substrateId).map(existingSubstrate -> {

            if (substrateToUpdate.getAssemblyName() != null) {
                existingSubstrate.setAssemblyName(substrateToUpdate.getAssemblyName());
            }

            if (substrateToUpdate.getSubstrateMarking() != null) {
                existingSubstrate.setSubstrateMarking(substrateToUpdate.getSubstrateMarking());
            }

            if (substrateToUpdate.getManufacturer() != null) {
                existingSubstrate.setManufacturer(substrateToUpdate.getManufacturer());
            }

            if (substrateToUpdate.getTechnology() != null) {
                existingSubstrate.setTechnology(substrateToUpdate.getTechnology());
                existingSubstrate.recalculateFields();
            }

            if (substrateToUpdate.getAreaValue() != null) {
                existingSubstrate.setAreaValue(substrateToUpdate.getAreaValue());
                existingSubstrate.recalculateFields();
            }

            if (substrateToUpdate.getComplexity() != null) {
                existingSubstrate.setComplexity(substrateToUpdate.getComplexity());
                existingSubstrate.recalculateFields();
            }

            if (substrateToUpdate.getThickness() != null) {
                existingSubstrate.setThickness(substrateToUpdate.getThickness());
            }

            if (substrateToUpdate.getWeight() != null) {
                existingSubstrate.setWeight(substrateToUpdate.getWeight());
            }

            return substrateRepository.save(existingSubstrate);
        });
//        return Optional.empty();
    }

    @Override
        public Optional<Substrate> removeSubstrateById(Long substrateId) {
            Optional<Substrate> substrateToRemove = getSubstrateById(substrateId);
            substrateRepository.deleteById(substrateId);
            return substrateToRemove;
        }
}





//    @Override
//    public Optional<Substrate> updateSubstrate(Substrate substrateToUpdate, Long substrateId) {
//
//        boolean substrateIsPresent = substrateRepository.findById(substrateId).isPresent();
//        if (substrateIsPresent) {
//            Substrate substrDB = substrateRepository.findById(substrateId).get();
//
//            substrDB.setAssemblyName(substrate.getAssemblyName());
//            substrDB.setSubstrateMarking(substrate.getSubstrateMarking());
//            substrDB.setManufacturer(substrate.getManufacturer());
//            substrDB.setTechnology(technologyEnum);
////                substrDB.setTechnologyDescription(technologyEnum.getDescription());
////                substrDB.setTechnologyCoreMaterial(technologyEnum.getCoreMaterial());
//            substrDB.setAreaValue(substrate.getAreaValue());
////                substrDB.setTechnologyMetalLayers(technologyEnum.getMetalLayers());
//            substrDB.setComplexity(complexityEnum);
//            substrDB.setThickness(substrate.getThickness());
//            substrDB.setWeight(substrate.getWeight());
////                substrDB.setTestCost(substrateCost.testCost());
////                substrDB.setSubstrateCost(substrateCost.substrateCost());
//
//            return Optional.of(substrateRepository.save(substrDB));
//        }
//        return Optional.empty();
//    }

