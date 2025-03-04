package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class SubstrateCost {

    private final TechnologyEnum technology;
    private final ComplexityEnum complexity;
    private final Area area;

    @Autowired
    public SubstrateCost(TechnologyEnum technology, ComplexityEnum complexity, Area area){
        this.technology = technology;
        this.complexity = complexity;
        this.area = area;
    }


    public double substrateCost() {

        if (complexity.getCompl() <= 0 || technology.getMetalLayers() <= 0 || technology.getFactor() <= 0) {
            throw new ArithmeticException("You can not divide or multiply by zero and negative numbers");
        }
        return technology.getMetalLayers() * complexity.getCompl() / technology.getFactor() + (area.getAreaValue() / 6);
    }

    public double testCost() {
        if (complexity.getCompl() <= 0 || technology.getMetalLayers() <= 0 || technology.getFactor() <= 0) {
            throw new ArithmeticException("You can not divide or multiply by zero and negative numbers");
        }
        return technology.getFactor() * complexity.getCompl() / technology.getMetalLayers() + (area.getAreaValue() / 6);
    }
}