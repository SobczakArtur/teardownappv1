package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

public class SubstrateCost {

    private Double complexity;
    private Technology technology;

//    @Autowired
    public SubstrateCost(Double complexity, Technology technology) {
        this.complexity = complexity;
        this.technology = technology;
    }

    public static Double substrateCost(Double complexity, Technology technology){

        if(complexity <= 0 || technology.getMetalLayers() <= 0 || technology.getFactor() <= 0){
            throw new ArithmeticException("You can not divide or multiply by zero");
        }
               return technology.getMetalLayers() * complexity / technology.getFactor();
    }
}


//        List<Technology> technologies = Arrays.asList(technology);
//        Stream<Technology> technologyStream = technologies.stream();
//        technologyStream
//                .map(Technology::getMetalLayers)
//                .collect(Collectors.toList());