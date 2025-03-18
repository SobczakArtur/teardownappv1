package pl.sobczakartur.teardownappv1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@ComponentScan("pl.sobczakartur.teardownappv1.mainelectronics.substrates")
public class TeardownV1Application {

	public static void main(String[] args) {
		run(TeardownV1Application.class, args);

	}
}
