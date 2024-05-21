package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class SubstrateCostTest {

//    private SubstrateCost substrateCost;

    @Test
    void substrateCost_zeroNumber_exceptionResult(){
        //given
//        substrateCost = new SubstrateCost(null, null);
        Technology technology = createMockTech(0, 1.41);
        Complexity complexity = createMockCompl(0.4);

        //then
        assertThrows(ArithmeticException.class, () -> {SubstrateCost.substrateCost(complexity, technology);});
    }

    @Test
     void substrateCost_negativeNumber_exceptionResult(){
        //given
//        substrateCost = new SubstrateCost(null, null);
        Technology technology = createMockTech(-4, 1.41);
        Complexity complexity = createMockCompl(0.4);

        //then
        assertThrows(ArithmeticException.class, () -> {SubstrateCost.substrateCost(complexity, technology);});
    }

    @Test
    void substrateCost_nullNumber_nullResult(){
        //given
//        substrateCost = new SubstrateCost(null, null);
        Technology technology = createMockTech(null, 1.41);
        Complexity complexity = createMockCompl(0.4);

        //then
        assertThrows(NullPointerException.class, () -> {SubstrateCost.substrateCost(complexity, technology);});
    }

    @Test
    void substrateCost_positiveNumber_positiveResult(){

        //given
//        substrateCost = new SubstrateCost(null, null);
        Technology technology = createMockTech(4, 1.41);
        Complexity complexity = createMockCompl(0.4);

        //when
        Double costResult = SubstrateCost.substrateCost(complexity, technology);

        //then
        assertEquals(costResult, 1.1347517730496455);
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
