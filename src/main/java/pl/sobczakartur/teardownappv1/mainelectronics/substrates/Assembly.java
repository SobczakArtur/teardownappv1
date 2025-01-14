package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

public class Assembly {
    // TODO Change this class to DTO object or builder design pattern???
    private String assemblyName;
    private String substrateMarking;
    private String manufacturer;
    private TechnologyEnum technology;
    private TechnologyEnum description;
    private TechnologyEnum coreMaterial;
    private double area;
    private TechnologyEnum metalLayers;
    private ComplexityEnum complexity;
    private double thickness;
    private double weight;
    private SubstrateTestCost substrateTestCost;
}
