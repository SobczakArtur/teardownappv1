package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import org.springframework.stereotype.Component;

//@Component
public enum Complexity {
    SIMPLE(0.1),
    LOW(0.3),
    MEDIUM(0.4),
    HIGH(0.5);


    private final Double complValue;

    Complexity(Double complValue) {
        this.complValue = complValue;
    }

    public Double getComplValue() {
        return complValue;
    }
}
