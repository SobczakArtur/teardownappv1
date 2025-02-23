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
                substrDB.setTechnologyName(technologyEnum.getName());
                substrDB.setTechnologyDescription(technologyEnum.getDescription());
                substrDB.setTechnologyCoreMaterial(technologyEnum.getCoreMaterial());
                substrDB.setArea(substrate.getArea());
                substrDB.setTechnologyMetalLayers(technologyEnum.getMetalLayers());
                substrDB.setComplexity(complexityEnum.getCompl());
                substrDB.setThickness(substrate.getThickness());
                substrDB.setWeight(substrate.getWeight());
                substrDB.setTestCost(substrateCost.testCost());
                substrDB.setSubstrateCost(substrateCost.substrateCost());

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


