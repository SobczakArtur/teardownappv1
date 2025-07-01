package pl.sobczakartur.teardownappv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "pl.sobczakartur.teardownappv1")
@EnableJpaRepositories(basePackages = "pl.sobczakartur.teardownappv1.mainelectronics.substrates.repository")
@EntityScan(basePackages = "pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity")
public class TeardownV1Application {

	public static void main(String[] args) {
		SpringApplication.run(TeardownV1Application.class, args);

	}
}
