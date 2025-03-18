package pl.sobczakartur.teardownappv1.mainelectronics.substrates.util;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Area;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.TechnologyEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class SubstrateCostTest {

    @Autowired
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
        assertThrows(NullPointerException.class, substrateCost::substrateCost);
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
        assertThrows(ArithmeticException.class, substrateCost::substrateCost);
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
        assertThrows(ArithmeticException.class, substrateCost::substrateCost);
    }



    @Test
    void substrateCost_positiveNumber_positiveResult(){

        //given
        TechnologyEnum technology = createMockTech(4, 1.41);
        ComplexityEnum complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);
        substrateCost = new SubstrateCost(technology, complexity, area);

        //when
        Double costResult = substrateCost.substrateCost();

        //then
        assertEquals(costResult, 1.5047517730496456);
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
        assertThrows(NullPointerException.class, substrateCost::testCost);
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
        assertThrows(ArithmeticException.class, substrateCost::testCost);
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
        assertThrows(ArithmeticException.class, substrateCost::testCost);
    }


    @Test
    void testCost_positiveNumber_positiveResult(){

        //given
        TechnologyEnum technology = createMockTech(4, 1.41);
        ComplexityEnum complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);

        //when
        substrateCost = new SubstrateCost(technology, complexity, area);
        Double costResult = substrateCost.testCost();

        //then
        assertEquals(costResult, 0.511);
    }



    TechnologyEnum createMockTech (Integer metalLayers, Double factor){
        TechnologyEnum technology = Mockito.mock(TechnologyEnum.class);
        Mockito.when(technology.getMetalLayers()).thenReturn(metalLayers);
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