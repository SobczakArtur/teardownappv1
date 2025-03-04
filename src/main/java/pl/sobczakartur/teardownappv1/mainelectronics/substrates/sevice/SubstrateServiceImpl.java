package pl.sobczakartur.teardownappv1.mainelectronics.substrates.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.SubstrateCost;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.TechnologyEnum;
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
        public Optional<Substrate> updateSubstrate(SubstrateCost substrateCost, ComplexityEnum complexityEnum, TechnologyEnum technologyEnum,
                                                   Substrate substrate, Long substrateId) {

            boolean substrateIsPresent = substrateRepository.findById(substrateId).isPresent();
            if (substrateIsPresent) {
                Substrate substrDB = substrateRepository.findById(substrateId).get();

                substrDB.setAssemblyName(substrate.getAssemblyName());
                substrDB.setSubstrateMarking(substrate.getSubstrateMarking());
                substrDB.setManufacturer(substrate.getManufacturer());
                substrDB.setTechnology(technologyEnum);
//                substrDB.setTechnologyDescription(technologyEnum.getDescription());
//                substrDB.setTechnologyCoreMaterial(technologyEnum.getCoreMaterial());
                substrDB.setAreaValue(substrate.getAreaValue());
//                substrDB.setTechnologyMetalLayers(technologyEnum.getMetalLayers());
                substrDB.setComplexity(complexityEnum);
                substrDB.setThickness(substrate.getThickness());
                substrDB.setWeight(substrate.getWeight());
//                substrDB.setTestCost(substrateCost.testCost());
//                substrDB.setSubstrateCost(substrateCost.substrateCost());

                return Optional.of(substrateRepository.save(substrDB));
            }
            return Optional.empty();
        }

    @Override
    public Optional<Substrate> partiallyUpdateSubstrate(SubstrateCost updatedSubstrateCost, ComplexityEnum updatedComplexity, TechnologyEnum updatedTechnology,
                                                        Substrate updatedSubstrate, Long substrateId) {

        boolean substrateIsPresent = substrateRepository.findById(substrateId).isPresent();
        if (substrateIsPresent) {
            Substrate substrDB = substrateRepository.findById(substrateId).get();

            if (updatedSubstrate.getAssemblyName() != null) substrDB.setAssemblyName(updatedSubstrate.getAssemblyName());
            if (updatedSubstrate.getSubstrateMarking() != null) substrDB.setSubstrateMarking(updatedSubstrate.getSubstrateMarking());
            if (updatedSubstrate.getManufacturer() != null) substrDB.setManufacturer(updatedSubstrate.getManufacturer());
            if (updatedTechnology != null) substrDB.setTechnology(updatedTechnology);
//            if (updatedTechnology.getDescription() != null) substrDB.setTechnologyDescription(updatedTechnology.getDescription());
//            if (updatedTechnology.getCoreMaterial() != null) substrDB.setTechnologyCoreMaterial(updatedTechnology.getCoreMaterial());
            if (updatedSubstrate.getAreaValue().getAreaValue() > 0) substrDB.setAreaValue(updatedSubstrate.getAreaValue());
//            if (updatedTechnology.getMetalLayers() > 0) substrDB.setTechnologyMetalLayers(updatedTechnology.getMetalLayers());
            if (updatedComplexity != null) substrDB.setComplexity(updatedComplexity);
            if (updatedSubstrate.getThickness() > 0) substrDB.setThickness(updatedSubstrate.getThickness());
            if (updatedSubstrate.getWeight() > 0) substrDB.setWeight(updatedSubstrate.getWeight());
//            if (updatedSubstrateCost.testCost() > 0) substrDB.setTestCost(updatedSubstrateCost.testCost());
//            if (updatedSubstrateCost.substrateCost() > 0) substrDB.setSubstrateCost(updatedSubstrateCost.substrateCost());

            return Optional.of(substrateRepository.save(substrDB));
        }
        return Optional.empty();
    }

    @Override
        public Optional<Substrate> removeSubstrateById(Long substrateId) {
            Optional<Substrate> substrateToRemove = getSubstrateById(substrateId);
            substrateRepository.deleteById(substrateId);
            return substrateToRemove;
        }
}






