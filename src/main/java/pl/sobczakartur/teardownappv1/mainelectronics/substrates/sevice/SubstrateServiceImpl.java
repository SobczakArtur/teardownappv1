package pl.sobczakartur.teardownappv1.mainelectronics.substrates.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.repository.SubstrateRepository;

import java.util.List;
import java.util.Objects;
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
    public Substrate updateSubstrate(Substrate substrate, Long substrateId) {
        Substrate substrDB = substrateRepository.findById(substrateId).get();

        if (Optional.ofNullable(substrate.getArea()).orElse(0.0) == 0.0){
            substrDB.setArea(substrate.getArea());
        }
        if (Optional.ofNullable(substrate.getSubstrateMarking()).orElse("").equals("")){
            substrDB.setSubstrateMarking(substrate.getSubstrateMarking());
        }




        return substrateRepository.save(substrDB);
    }

    @Override
    public void deleteSubstrateById(Long substrateId) {

    }
}
