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


               return technology.getMetalLayers() * complexity / technology.getFactor();



//        List<Technology> technologies = Arrays.asList(technology);
//        Stream<Technology> technologyStream = technologies.stream();
//        technologyStream
//                .map(Technology::getMetalLayers)
//                .collect(Collectors.toList());
    }
}
