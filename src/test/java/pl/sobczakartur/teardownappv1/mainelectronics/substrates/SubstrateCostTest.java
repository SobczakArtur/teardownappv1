package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class SubstrateCostTest {

    private SubstrateCost substrateCost;

    @Test
    void substrateCost_onlyPositiveNumbers_positiveResult(){

        //given
        substrateCost = new SubstrateCost(null, null);
        Technology itemToTest = createMockForPositiveNumbers();

        //when
        Double costResult = substrateCost.substrateCost(.4, itemToTest);

        //then
        assertEquals(costResult, 1.1347517730496455);



    }

    private Technology createMockForPositiveNumbers (){
        Technology technology = Mockito.mock(Technology.class);

        Mockito.when(technology.getMetalLayers()).thenReturn(4);
        Mockito.when(technology.getFactor()).thenReturn(1.41);

        return technology;
    }
}
