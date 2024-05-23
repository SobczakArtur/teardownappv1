package pl.sobczakartur.teardownappv1;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
public class TeardownV1Application {

	public static void main(String[] args) {
		run(TeardownV1Application.class, args);
//		SubstrateCost substrateCost = new SubstrateCost(Complexity.MEDIUM, Technology.FOUR_LAYER_B);

	}
}
