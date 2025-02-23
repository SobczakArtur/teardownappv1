package pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "substrate")
public class Substrate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long substrateId;

    @NotNull
    @Column(length = 255)
    private String assemblyName;

    @NotNull
    @Column(length = 255)
    private String substrateMarking;

    @NotNull
    @Column(length = 255)
    private String manufacturer;

    @NotNull
    @Column(length = 255)
    private String technologyName; //TechnologyEnum

    @NotNull
    @Column(length = 255)
    private String technologyDescription; //TechnologyEnum

    @NotNull
    @Column(length = 255)
    private String technologyCoreMaterial;  //TechnologyEnum

    @Positive
    private Double area;

    @Positive
    private Integer technologyMetalLayers;  //TechnologyEnum

    @Positive
    private Double complexity;  //ComplexityEnum

    @Positive
    private Double thickness;

    @PositiveOrZero
    private Double weight;

    @PositiveOrZero
    private Double testCost;  //SubstrateTestCost

    @PositiveOrZero
    private Double substrateCost;  //SubstrateTestCost



//    POSTMAN
//[
//    {
//        "assemblyName": "120 Hz Display",
//            "substrateMarking": "hhdkdjj",
//            "manufacturer": "Techinsight",
//            "technologyName": "TWO_LAYER_F",
//            "technologyDescription": "2 Layer F",
//            "technologyCoreMaterial": "Poly",
//            "area": "2.9",
//            "technologyMetalLayers": "2",
//            "complexity": "0.3",
//            "thickness": "1.1",
//            "weight": "3.0",
//            "testCost": "5.7",
//            "substrateCost": "3.5"
//    }
//]


}
