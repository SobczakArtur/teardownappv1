package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class SubstrateCostTest {

    private SubstrateCost substrateCost;

    @Test
    void substrateCost_zeroNumber_exceptionResult(){

        //given
        Technology technology = createMockTech(0, 1.41);
        Complexity complexity = createMockCompl(0.4);
        substrateCost = new SubstrateCost(complexity, technology);

        //then
        assertThrows(ArithmeticException.class, SubstrateCost::substrateCost);
    }

    @Test
     void substrateCost_negativeNumber_exceptionResult(){

        //given
        Technology technology = createMockTech(-4, 1.41);
        Complexity complexity = createMockCompl(0.4);
        substrateCost = new SubstrateCost(complexity, technology);

        //then
        assertThrows(ArithmeticException.class, SubstrateCost::substrateCost);
    }



    @Test
    void substrateCost_positiveNumber_positiveResult(){

        //given
        Technology technology = createMockTech(4, 1.41);
        Complexity complexity = createMockCompl(0.4);
        substrateCost = new SubstrateCost(complexity, technology);

        //when
        Double costResult = SubstrateCost.substrateCost();

        //then
        assertEquals(costResult, 1.1347517730496455);
    }

    @Test
    void testCost_zeroNumber_exceptionResult(){

        //given
        Technology technology = createMockTech(0, 1.41);
        Complexity complexity = createMockCompl(0.4);
        substrateCost = new SubstrateCost(complexity, technology);

        //then
        assertThrows(ArithmeticException.class, SubstrateCost::testCost);
    }

    @Test
    void testCost_negativeNumber_exceptionResult(){

        //given
        Technology technology = createMockTech(-4, 1.41);
        Complexity complexity = createMockCompl(0.4);
        substrateCost = new SubstrateCost(complexity, technology);

        //then
        assertThrows(ArithmeticException.class, SubstrateCost::testCost);
    }


    @Test
    void testCost_positiveNumber_positiveResult(){

        //given
        Technology technology = createMockTech(4, 1.41);
        Complexity complexity = createMockCompl(0.4);
        substrateCost = new SubstrateCost(complexity, technology);

        //when
        Double costResult = SubstrateCost.testCost();

        //then
        assertEquals(costResult, 0.141);
    }



    private Technology createMockTech (Integer metalLayers, Double factor){
        Technology technology = Mockito.mock(Technology.class);
        Mockito.when(technology.getMetalLayers()).thenReturn(metalLayers);
        Mockito.when(technology.getFactor()).thenReturn(factor);
        return technology;
    }

    private Complexity createMockCompl (Double complValue){
        Complexity complexity = Mockito.mock(Complexity.class);
        Mockito.when(complexity.getComplValue()).thenReturn(complValue);
        return complexity;
    }

}




//    @Test
//    void substrateCost_nullNumber_nullResult(){
//
//        //given
//        Technology technology = createMockTech(4, 1.41);
//        Complexity complexity = createMockCompl(null);
//        substrateCost = new SubstrateCost(complexity, technology);
//
//        //when
//        Double costResult = SubstrateCost.substrateCost();
//
//        //then
////        assertThrows(NullPointerException.class, SubstrateCost::substrateCost);
//        assertNull(substrateCost);
//    }
