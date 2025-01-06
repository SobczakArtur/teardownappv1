package pl.sobczakartur.teardownappv1.mainelectronics.substrates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;

@Repository
public interface SubstrateRepository extends JpaRepository<Substrate, Long> {
}
