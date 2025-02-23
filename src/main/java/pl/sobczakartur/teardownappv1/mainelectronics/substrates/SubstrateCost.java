package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import org.springframework.beans.factory.annotation.Autowired;

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


    public Double substrateCost() {

        if (complexity.getCompl() <= 0 || technology.getMetalLayers() <= 0 || technology.getFactor() <= 0) {
            throw new ArithmeticException("You can not divide or multiply by zero and negative numbers");
        }
        return technology.getMetalLayers() * complexity.getCompl() / technology.getFactor() + (area.getArea() / 6);
    }

    public Double testCost() {
        if (complexity.getCompl() <= 0 || technology.getMetalLayers() <= 0 || technology.getFactor() <= 0) {
            throw new ArithmeticException("You can not divide or multiply by zero and negative numbers");
        }
        return technology.getFactor() * complexity.getCompl() / technology.getMetalLayers() + (area.getArea() / 6);
    }
}