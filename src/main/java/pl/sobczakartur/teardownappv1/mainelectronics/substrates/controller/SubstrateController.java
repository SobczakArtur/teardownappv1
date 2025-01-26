package pl.sobczakartur.teardownappv1.mainelectronics.substrates.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.SubstrateTestCost;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.TechnologyEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.sevice.SubstrateService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SubstrateController {

        @Autowired
        private SubstrateService substrateService;

        @PostMapping("/substrates")
        public Substrate saveSubstrate(@Valid @RequestBody Substrate substrate){
            return substrateService.saveSubstrate(substrate);
        };


        @GetMapping("/substrates")
        public List<Substrate> fetchAllSubstrate() {
            return substrateService.fetchAllSubstrate();
        }


        @PostMapping("substrates/{id}")
        public Substrate updateSubstrate(@RequestBody SubstrateTestCost substrateTestCost, @RequestBody ComplexityEnum complexityEnum,
                                         @RequestBody TechnologyEnum technologyEnum, @RequestBody Substrate substrate, @PathVariable("id") Long substrateId) {
            return substrateService.updateSubstrate(substrateTestCost, complexityEnum, technologyEnum, substrate, substrateId);
        }

        @DeleteMapping("substrates/{id}")
        public String deleteSubstrateById(@PathVariable("id") Long substrateId) {
            substrateService.deleteSubstrateById(substrateId);
            return "Deleted Successfully";
        }


}
