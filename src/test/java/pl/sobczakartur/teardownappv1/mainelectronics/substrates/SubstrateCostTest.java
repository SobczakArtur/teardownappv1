package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//@SpringBootTest
public class SubstrateCostTest {

    private SubstrateCost substrateCost;

    @Test
    void substrateCost_zeroNumber_exceptionResult(){
        //given
        substrateCost = new SubstrateCost(null, null);
        Technology itemToTest = createMock(4, 1.41);

        //then
        assertThrows(ArithmeticException.class, () -> {SubstrateCost.substrateCost(.0, itemToTest);});
    }

    @Test
    void substrateCost_negativeNumber_exceptionResult(){
        //given
        substrateCost = new SubstrateCost(null, null);
        Technology itemToTest = createMock(4, 1.41);

        //then
        assertThrows(ArithmeticException.class, () -> {SubstrateCost.substrateCost(-.4, itemToTest);});
    }

    @Test
    void substrateCost_nullNumber_nullResult(){
        //given
        substrateCost = new SubstrateCost(null, null);
        Technology itemToTest = createMock(4, 1.41);

        //then
        assertThrows(NullPointerException.class, () -> {SubstrateCost.substrateCost(null, itemToTest);});
    }

    @Test
    void substrateCost_positiveNumber_positiveResult(){

        //given
        substrateCost = new SubstrateCost(null, null);
        Technology itemToTest = createMock(4, 1.41);

        //when
        Double costResult = substrateCost.substrateCost(.4, itemToTest);

        //then
        assertEquals(costResult, 1.1347517730496455);
    }

    private Technology createMock (int metalLayers, double factor){
        Technology technology = Mockito.mock(Technology.class);
        Mockito.when(technology.getMetalLayers()).thenReturn(metalLayers);
        Mockito.when(technology.getFactor()).thenReturn(factor);
        return technology;
    }
}
