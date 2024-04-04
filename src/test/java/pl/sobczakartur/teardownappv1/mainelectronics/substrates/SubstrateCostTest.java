package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class SubstrateCostTest {

    private SubstrateCost substrateCost;

    @Test
    void computingSubstrateCost(){

        //given
        substrateCost = new SubstrateCost(null, null);
        Technology itemToTest = createMock();

        //when
        Double costResult = substrateCost.substrateCost(.4, itemToTest);

        //then
        assertEquals(costResult, 1.1347517730496455);
    }

    private Technology createMock (){
        Technology technology = Mockito.mock(Technology.class);

        Mockito.when(technology.getMetalLayers()).thenReturn(4);
        Mockito.when(technology.getFactor()).thenReturn(1.41);

        return technology;
    }
}
