package pl.sobczakartur.teardownappv1.mainelectronics.substrates.util;

import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Area;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.TechnologyEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.exception.InvalidSubstrateParametersException;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Calculates substrate and test costs based on technology, complexity and area.
 */

public final class SubstrateCost {

    private final TechnologyEnum technology;
    private final ComplexityEnum complexity;
    private final Area area;

    public SubstrateCost(TechnologyEnum technology, ComplexityEnum complexity, Area area){
        this.technology = technology;
        this.complexity = complexity;
        this.area = area;
    }

    private void validateInputs() {
        if (complexity.getCompl() <= 0 || technology.getMetalLs() <= 0 || technology.getFactor() <= 0) {
            throw new InvalidSubstrateParametersException("complexity, metalLs and factor must be positive numbers");
        }
    }


    public BigDecimal getSubstrateCost() {

        validateInputs();

        BigDecimal metalLs = BigDecimal.valueOf(technology.getMetalLs());
        BigDecimal compl = BigDecimal.valueOf(complexity.getCompl());
        BigDecimal factor = BigDecimal.valueOf(technology.getFactor());
        BigDecimal areaVal = BigDecimal.valueOf(area.getAreaValue());

        return metalLs.multiply(compl)
                .divide(factor, MathContext.DECIMAL128)
                .add(areaVal.divide(BigDecimal.valueOf(6), MathContext.DECIMAL128));

    }

    public BigDecimal getTestCost() {

        validateInputs();

        BigDecimal metalLs = BigDecimal.valueOf(technology.getMetalLs());
        BigDecimal compl = BigDecimal.valueOf(complexity.getCompl());
        BigDecimal factor = BigDecimal.valueOf(technology.getFactor());
        BigDecimal areaVal = BigDecimal.valueOf(area.getAreaValue());

        return factor.multiply(compl)
                .divide(metalLs, MathContext.DECIMAL128)
                .add(areaVal.divide(BigDecimal.valueOf(6), MathContext.DECIMAL128));
    }
}