package pl.sobczakartur.teardownappv1.mainelectronics.substrates.util;

import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.TechnologyEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Area;


public class SubstrateCost {

    private final TechnologyEnum technology;
    private final ComplexityEnum complexity;
    private final Area area;

    public SubstrateCost(TechnologyEnum technology, ComplexityEnum complexity, Area area){
        this.technology = technology;
        this.complexity = complexity;
        this.area = area;
    }


    public double substrateCost() {

        if (complexity.getCompl() <= 0 || technology.getMetalLrs() <= 0 || technology.getFactor() <= 0) {
            throw new ArithmeticException("You can not divide or multiply by zero and negative numbers");
        }
        return technology.getMetalLrs() * complexity.getCompl() / technology.getFactor() + (area.getAreaValue() / 6);
    }

    public double testCost() {
        if (complexity.getCompl() <= 0 || technology.getMetalLrs() <= 0 || technology.getFactor() <= 0) {
            throw new ArithmeticException("You can not divide or multiply by zero and negative numbers");
        }
        return technology.getFactor() * complexity.getCompl() / technology.getMetalLrs() + (area.getAreaValue() / 6);
    }
}