package pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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


}
