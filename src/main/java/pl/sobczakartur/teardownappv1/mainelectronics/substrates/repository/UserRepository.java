package pl.sobczakartur.teardownappv1.mainelectronics.substrates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}