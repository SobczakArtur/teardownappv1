package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubstrateCost {

    private Complexity complexity;
    private Technology technology;
    public long substrateCost;

//    @Autowired
    public SubstrateCost(Complexity complexity, Technology technology) {
        this.complexity = complexity;
        this.technology = technology;
    }

    public static Integer substrateCost(Complexity complexity, Technology technology){
        List<Technology> technologies = Arrays.asList(technology);
        Stream<Technology> technologyStream = technologies.stream();
                technologyStream
                .map(Technology::getMetalLayers)
                .collect(Collectors.toList());

                technologyStream.forEach(System.out::println);

               return 1;
    }



}
