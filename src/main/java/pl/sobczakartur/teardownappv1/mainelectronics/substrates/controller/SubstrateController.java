package pl.sobczakartur.teardownappv1.mainelectronics.substrates.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.service.SubstrateService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/substrate")
public class SubstrateController {


        private final SubstrateService substrateService;

        @Autowired
        public SubstrateController(@Qualifier("substrateServiceImpl") SubstrateService substrateService) {
            this.substrateService = substrateService;
        }


        @GetMapping
        public ResponseEntity<List<Substrate>> getAllSubstrate() {
            return new ResponseEntity<>(substrateService.getAllSubstrate(), HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Substrate> getSubstrateById(@PathVariable("id") Long substrateId){
            Optional<Substrate> takeById = substrateService.getSubstrateById(substrateId);
            return takeById
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PostMapping
        public ResponseEntity<Substrate> addSubstrate(@RequestBody Substrate substrate){
            Optional<Substrate> substrateAdded = substrateService.addSubstrate(substrate);
            return substrateAdded
                    .map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        }

//    @PostMapping
//    public ResponseEntity<Substrate> addSubstrate(@Valid @RequestBody Substrate substrate){
//        Optional<Substrate> substrateAdded = substrateService.addSubstrate(substrate);
//        return substrateAdded
//                .map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
//    }

        @PutMapping("/{id}")
        public ResponseEntity<Substrate> updateSubstrate(@RequestBody Substrate substrate, @PathVariable("id") Long substrateId) {
            Optional<Substrate> substrateUpdated = substrateService.updatedSubstrate(substrate, substrateId);
            return substrateUpdated
                    .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PatchMapping("/{id}")
        public ResponseEntity<Substrate> partiallyUpdatedSubstrate(@RequestBody Substrate substrateUpd, @PathVariable("id") Long substrateId) {
            Optional<Substrate> substrateUpdated = substrateService.partiallyUpdatedSubstrate(substrateUpd, substrateId);
            return substrateUpdated
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