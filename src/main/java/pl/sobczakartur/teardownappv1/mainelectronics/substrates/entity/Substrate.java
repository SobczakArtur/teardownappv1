package pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.Area;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.SubstrateCost;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.TechnologyEnum;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
    private TechnologyEnum technology; //TechnologyEnum

    @Column(length = 255)
    private String technologyDescription = technology.getDescription(); //TechnologyEnum

    @Column(length = 255)
    private String technologyCoreMaterial = technology.getCoreMaterial();  //TechnologyEnum

    @Positive
    private Area area;

    @Positive
    private Integer technologyMetalLayers = technology.getMetalLayers();  //TechnologyEnum

    @Positive
    private ComplexityEnum complexity;  //ComplexityEnum

    @Positive
    private Double thickness;

    @PositiveOrZero
    private Double weight;

    @Transient
    SubstrateCost substrateCostObject = new SubstrateCost(technology, complexity, area);

    @PositiveOrZero
    private Double testCost = substrateCostObject.testCost();  //SubstrateCost

    @PositiveOrZero
    private Double substrateCost = substrateCostObject.substrateCost();  //SubstrateCost





//    POSTMAN
//[
//    {
//            "assemblyName": "120 Hz Display",
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
