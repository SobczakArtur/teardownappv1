package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class SubstrateTestCostTest {

    private SubstrateTestCost substrateTestCost;

    @Test
    void substrateCost_nullNumber_exceptionResult(){

        //given
        Technology technology = createMockTech(4, null);
        Complexity complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);
        substrateTestCost = new SubstrateTestCost(complexity, technology, area);

        //then
        assertThrows(NullPointerException.class, substrateTestCost::substrateCost);
    }

    @Test
    void substrateCost_zeroNumber_exceptionResult(){

        //given
        Technology technology = createMockTech(0, 1.41);
        Complexity complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);
        substrateTestCost = new SubstrateTestCost(complexity, technology, area);

        //then
        assertThrows(ArithmeticException.class, substrateTestCost::substrateCost);
    }

    @Test
     void substrateCost_negativeNumber_exceptionResult(){

        //given
        Technology technology = createMockTech(-4, 1.41);
        Complexity complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);
        substrateTestCost = new SubstrateTestCost(complexity, technology, area);

        //then
        assertThrows(ArithmeticException.class, substrateTestCost::substrateCost);
    }



    @Test
    void substrateCost_positiveNumber_positiveResult(){

        //given
        Technology technology = createMockTech(4, 1.41);
        Complexity complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);
        substrateTestCost = new SubstrateTestCost(complexity, technology, area);

        //when
        Double costResult = substrateTestCost.substrateCost();

        //then
        assertEquals(costResult, 1.5047517730496456);
    }

    @Test
    void testCost_nullNumber_exceptionResult(){

        //given
        Technology technology = createMockTech(4, 1.41);
        Complexity complexity = createMockCompl(null);
        Area area = createMockArea(2.22);
        substrateTestCost = new SubstrateTestCost(complexity, technology, area);

        //then
        assertThrows(NullPointerException.class, substrateTestCost::testCost);
    }

    @Test
    void testCost_zeroNumber_exceptionResult(){

        //given
        Technology technology = createMockTech(0, 1.41);
        Complexity complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);
        substrateTestCost = new SubstrateTestCost(complexity, technology, area);

        //then
        assertThrows(ArithmeticException.class, substrateTestCost::testCost);
    }

    @Test
    void testCost_negativeNumber_exceptionResult(){

        //given
        Technology technology = createMockTech(-4, 1.41);
        Complexity complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);
        substrateTestCost = new SubstrateTestCost(complexity, technology, area);

        //then
        assertThrows(ArithmeticException.class, substrateTestCost::testCost);
    }


    @Test
    void testCost_positiveNumber_positiveResult(){

        //given
        Technology technology = createMockTech(4, 1.41);
        Complexity complexity = createMockCompl(0.4);
        Area area = createMockArea(2.22);
        substrateTestCost = new SubstrateTestCost(complexity, technology, area);

        //when
        Double costResult = substrateTestCost.testCost();

        //then
        assertEquals(costResult, 0.511);
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

    private Area createMockArea (Double areaValue){
        Area area = Mockito.mock(Area.class);
        Mockito.when(area.getArea()).thenReturn(areaValue);
        return area;
    }

}




//    @Test
//    void substrateCost_nullNumber_nullResult(){
//
//        //given
//        Technology technology = createMockTech(4, 1.41);
//        Complexity complexity = createMockCompl(null);
//        substrateTestCost = new SubstrateTestCost(complexity, technology);
//
//        //when
//        Double costResult = SubstrateTestCost.substrateTestCost();
//
//        //then
////        assertThrows(NullPointerException.class, SubstrateTestCost::substrateTestCost);
//        assertNull(substrateTestCost);
//    }
