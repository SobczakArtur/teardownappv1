package pl.sobczakartur.teardownappv1.mainelectronics.substrates.util;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Area;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.TechnologyEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.exception.InvalidSubstrateParametersException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
public class SubstrateCostUnitTest {


    private SubstrateCost substrateCost;


    @Test
    void substrateCost_nullNumber_exceptionResult(){

        //given
        TechnologyEnum technology = createMockTech(4, null);
        ComplexityEnum complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);

        //when
        substrateCost = new SubstrateCost(technology, complexity, area);

        //then
        assertThrows(NullPointerException.class, substrateCost::getSubstrateCost);
    }

    @Test
    void substrateCost_zeroNumber_exceptionResult(){

        //given
        TechnologyEnum technology = createMockTech(0, 1.41);
        ComplexityEnum complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);

        //when
        substrateCost = new SubstrateCost(technology, complexity, area);

        //then
        assertThrows(InvalidSubstrateParametersException.class, substrateCost::getSubstrateCost);
    }

    @Test
    void substrateCost_negativeNumber_exceptionResult(){

        //given
        TechnologyEnum technology = createMockTech(-4, 1.41);
        ComplexityEnum complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);

        //when
        substrateCost = new SubstrateCost(technology, complexity, area);

        //then
        assertThrows(InvalidSubstrateParametersException.class, substrateCost::getSubstrateCost);
    }



    @Test
    void substrateCost_positiveNumber_positiveResult(){

        //given
        TechnologyEnum technology = createMockTech(4, 1.41);
        ComplexityEnum complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);
        substrateCost = new SubstrateCost(technology, complexity, area);

        //when
        BigDecimal costResult = substrateCost.getSubstrateCost();

        //then
        assertEquals(new BigDecimal(String.valueOf(costResult)), new BigDecimal("1.504751773049645390070921985815603"));
    }

    @Test
    void testCost_nullNumber_exceptionResult(){

        //given
        TechnologyEnum technology = createMockTech(4, 1.41);
        ComplexityEnum complexity = createMockCompl(null);
        Area area = createMockArea(2.22);

        //when
        substrateCost = new SubstrateCost(technology, complexity, area);

        //then
        assertThrows(NullPointerException.class, substrateCost::getTestCost);
    }

    @Test
    void testCost_zeroNumber_exceptionResult(){

        //given
        TechnologyEnum technology = createMockTech(0, 1.41);
        ComplexityEnum complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);

        //when
        substrateCost = new SubstrateCost(technology, complexity, area);

        //then
        assertThrows(InvalidSubstrateParametersException.class, substrateCost::getTestCost);
    }

    @Test
    void testCost_negativeNumber_exceptionResult(){

        //given
        TechnologyEnum technology = createMockTech(-4, 1.41);
        ComplexityEnum complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);

        //when
        substrateCost = new SubstrateCost(technology, complexity, area);

        //then
        assertThrows(InvalidSubstrateParametersException.class, substrateCost::getTestCost);
    }


    @Test
    void testCost_positiveNumber_positiveResult(){

        //given
        TechnologyEnum technology = createMockTech(4, 1.41);
        ComplexityEnum complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);

        //when
        substrateCost = new SubstrateCost(technology, complexity, area);
        BigDecimal costResult = substrateCost.getTestCost();

        //then
        assertEquals(new BigDecimal(String.valueOf(costResult)), new BigDecimal("0.511"));
    }



    TechnologyEnum createMockTech (Integer metalLs, Double factor){
        TechnologyEnum technology = Mockito.mock(TechnologyEnum.class);
        Mockito.when(technology.getMetalLs()).thenReturn(metalLs);
        Mockito.when(technology.getFactor()).thenReturn(factor);
        return technology;
    }

    ComplexityEnum createMockCompl (Double compl){
        ComplexityEnum complexity = Mockito.mock(ComplexityEnum.class);
        Mockito.when(complexity.getCompl()).thenReturn(compl);
        return complexity;
    }

    Area createMockArea (Double areaValue){
        Area area = Mockito.mock(Area.class);
        Mockito.when(area.getAreaValue()).thenReturn(areaValue);
        return area;
    }

}