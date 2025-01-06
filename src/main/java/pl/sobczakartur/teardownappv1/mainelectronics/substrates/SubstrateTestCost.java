package pl.sobczakartur.teardownappv1.mainelectronics.substrates;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//@Service
public class SubstrateTestCost {


    private final Complexity complexity;
    private final Technology technology;
    private final Area area;


    @Autowired
    public SubstrateTestCost(Complexity complexity, Technology technology, Area area) {
        this.complexity = complexity;
        this.technology = technology;
        this.area = area;
    }

    public Double substrateCost() {

        if (complexity.getComplValue() <= 0 || technology.getMetalLayers() <= 0 || technology.getFactor() <= 0) {
            throw new ArithmeticException("You can not divide or multiply by zero and negative numbers");
        }
        return technology.getMetalLayers() * complexity.getComplValue() / technology.getFactor() + (area.getArea() / 6);
    }

    public Double testCost() {
        if (complexity.getComplValue() <= 0 || technology.getMetalLayers() <= 0 || technology.getFactor() <= 0) {
            throw new ArithmeticException("You can not divide or multiply by zero and negative numbers");
        }
        return technology.getFactor() * complexity.getComplValue() / technology.getMetalLayers() + (area.getArea() / 6);
    }
}




//        if(complexity.getComplValue() <= 0 || technology.getMetalLayers() <= 0 || technology.getFactor() <= 0){
//            throw new ArithmeticException("You can not divide or multiply by zero and negative numbers");
//        }if(complexity.getComplValue() == null || technology.getMetalLayers() == null || technology.getFactor() == null){
//            throw new NullPointerException("You can not provide null value");
//        }

//        if(complexity.getComplValue() <= 0 || technology.getMetalLayers() <= 0 || technology.getFactor() <= 0){
//            throw new ArithmeticException("You can not divide or multiply by zero and negative numbers");
//        }if(complexity.getComplValue() == null || technology.getMetalLayers() == null || technology.getFactor() == null){
//            throw new NullPointerException("You can not provide null value");
//        }


//        List<Technology> technologies = Arrays.asList(technology);
//        Stream<Technology> technologyStream = technologies.stream();
//        technologyStream
//                .map(Technology::getMetalLayers)
//                .collect(Collectors.toList());