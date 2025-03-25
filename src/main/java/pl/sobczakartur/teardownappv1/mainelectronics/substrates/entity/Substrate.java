package pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.TechnologyEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.util.SubstrateCost;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "substrate")
public class Substrate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long substrateId;

//    @NotNull
    @Column(length = 255)
    private String assemblyName;

//    @NotNull
    @Column(length = 255)
    private String substrateMarking;

//    @NotNull
    @Column(length = 255)
    private String manufacturer;

//    @NotNull
    @Column(length = 255)
    @Enumerated(EnumType.STRING)
    private TechnologyEnum technology; //TechnologyEnum

    @Column(length = 255)
    private String technologyDescription; //TechnologyEnum

    @Column(length = 255)
    private String technologyCoreMaterial;  //TechnologyEnum

//    @NotNull
    @Embedded
    @Positive
    private Area areaValue;

    @Positive
    private Integer technologyMetalLrs;  //TechnologyEnum

//    @NotNull
    @Positive
    @Enumerated(EnumType.STRING)
    private ComplexityEnum complexity;  //ComplexityEnum

    @Positive
    private Double thickness;

    @PositiveOrZero
    private Double weight;

//    @Column(precision = 10, scale = 2)
    @PositiveOrZero
    private Double substrateCost;

//    @Column(precision = 10, scale = 2)
    @PositiveOrZero
    private Double testCost;


    @PrePersist
    @PreUpdate
    private void updateTechnologyAndCostsFields() {
        if (technology != null) {
            this.technologyDescription = technology.getDescription();
            this.technologyCoreMaterial = technology.getCoreMaterial();
            this.technologyMetalLrs = technology.getMetalLrs();

            if (technology != null && complexity != null && areaValue != null) {
                SubstrateCost substrateCostObject = new SubstrateCost(technology, complexity, areaValue);
                this.substrateCost = substrateCostObject.substrateCost();
                this.testCost = substrateCostObject.testCost();
                }
        }
    }

    public void recalculateFields() {
        updateTechnologyAndCostsFields();
    }
}
