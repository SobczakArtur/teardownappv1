package pl.sobczakartur.teardownappv1.mainelectronics.substrates.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.SubstrateCost;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.TechnologyEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.sevice.SubstrateService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/substrate")
public class SubstrateController {



        private final SubstrateService substrateService;

        @Autowired
        public SubstrateController(SubstrateService substrateService) {
            this.substrateService = substrateService;
        }



        @GetMapping("")
        public ResponseEntity<List<Substrate>> getAllSubstrate() {
            return new ResponseEntity<>(substrateService.getAllSubstrate(), HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Substrate> getSubstrateById( @PathVariable("id") Long substrateId){
            Optional<Substrate> takeById = substrateService.getSubstrateById(substrateId);
            return takeById
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PostMapping("")
        public ResponseEntity<Substrate> addSubstrate(@Valid @RequestBody Substrate substrate){
            Optional<Substrate> substrateAdded = substrateService.addSubstrate(substrate);
            return substrateAdded
                    .map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Substrate> updateSubstrate(@RequestBody SubstrateCost substrateTestCost, @RequestBody ComplexityEnum complexityEnum,
                                         @RequestBody TechnologyEnum technologyEnum, @RequestBody Substrate substrate, @PathVariable("id") Long substrateId) {
            Optional<Substrate> substrateUpdate = substrateService.updateSubstrate(substrateTestCost, complexityEnum, technologyEnum, substrate, substrateId);
            return substrateUpdate
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Substrate> removeSubstrateById(@PathVariable("id") Long substrateId) {
            Optional<Substrate> substrateDelete = substrateService.removeSubstrateById(substrateId);
            return substrateDelete
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
}
