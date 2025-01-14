package pl.sobczakartur.teardownappv1.mainelectronics.substrates.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.SubstrateTestCost;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.TechnologyEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.repository.SubstrateRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubstrateServiceImpl implements SubstrateService {

    private SubstrateRepository substrateRepository;

    @Autowired
    public SubstrateServiceImpl(SubstrateRepository substrateRepository) {
        this.substrateRepository = substrateRepository;
    }

        @Override
        public Substrate saveSubstrate(Substrate substrate) {
            return substrateRepository.save(substrate);
        }

        @Override
        public List<Substrate> fetchAllSubstrate() {
            return substrateRepository.findAll();
        }

        @Override
        public Substrate updateSubstrate(SubstrateTestCost substrateTestCost, ComplexityEnum complexityEnum, TechnologyEnum technologyEnum, Substrate substrate, Long substrateId) {


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
                substrDB.setTestCost(substrateTestCost.testCost());
                substrDB.setSubstrateCost(substrateTestCost.substrateCost());



            return substrateRepository.save(substrDB);
        }

        @Override
        public void deleteSubstrateById(Long substrateId) {
            substrateRepository.deleteById(substrateId);
        }
}


//        if (Optional.ofNullable(substrate.getAssemblyName()).orElse("").equals("")){
//                substrDB.setAssemblyName(substrate.getAssemblyName());
//                }
//
//                if (Optional.ofNullable(substrate.getSubstrateMarking()).orElse("").equals("")){
//                substrDB.setSubstrateMarking(substrate.getSubstrateMarking());
//                }
//
//                if (Optional.ofNullable(substrate.getManufacturer()).orElse("").equals("")) {
//                substrDB.setManufacturer(substrate.getManufacturer());
//                }
//
//                if (Optional.ofNullable(substrate.getArea()).orElse(0.0) == 0.0){
//                substrDB.setArea(substrate.getArea());
//                }
//
//                if (Optional.ofNullable(substrate.getThickness()).orElse(0.0) == 0.0){
//                substrDB.setThickness(substrate.getThickness());
//                }
//
//                if (Optional.ofNullable(substrate.getWeight()).orElse(0.0) == 0.0){
//                substrDB.setWeight(substrate.getWeight());
//                }
//
//                if (Optional.of(technology = TechnologyEnum.valueOf("TWO_LAYER_C").equals("")){
//                substrDB.setWeight(substrate.getWeight());
//                }
