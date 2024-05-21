package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import org.springframework.beans.factory.annotation.Autowired;

public class SubstrateCost {

    private static Complexity complexity;
    private static Technology technology;

//    @Autowired
    public SubstrateCost(Complexity complexity, Technology technology) {
        this.complexity = complexity;
        this.technology = technology;
    }

    public static double substrateCost(Complexity complexity, Technology technology){

        if(complexity.getComplValue() <= 0 || technology.getMetalLayers() <= 0 || technology.getFactor() <= 0){
            throw new ArithmeticException("You can not divide or multiply by zero and negative numbers");
        } else if (complexity.getComplValue() == null || technology.getMetalLayers() == null || technology.getFactor() == null){
            throw new NullPointerException("You can not provide null value");
        }
            return technology.getMetalLayers() * complexity.getComplValue() / technology.getFactor();
    }
}


//        List<Technology> technologies = Arrays.asList(technology);
//        Stream<Technology> technologyStream = technologies.stream();
//        technologyStream
//                .map(Technology::getMetalLayers)
//                .collect(Collectors.toList());