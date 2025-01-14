package pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity;

import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "substrates")
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

    @PositiveOrZero
    private Double area;

    @PositiveOrZero
    private Integer technologyMetalLayers;  //TechnologyEnum

    @PositiveOrZero
    private Double complexity;  //ComplexityEnum

    @PositiveOrZero
    private Double thickness;

    @PositiveOrZero
    private Double weight;

    @PositiveOrZero
    private Double testCost;  //SubstrateTestCost

    @PositiveOrZero
    private Double substrateCost;  //SubstrateTestCost


}
