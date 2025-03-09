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
    @Enumerated(EnumType.STRING)
    private TechnologyEnum technology; //TechnologyEnum

    @Column(length = 255)
    private String technologyDescription; //TechnologyEnum

    @Column(length = 255)
    private String technologyCoreMaterial;  //TechnologyEnum

    @NotNull
    @Embedded
//    @Positive
    private Area areaValue;

    @Positive
    private Integer technologyMetalLayers;  //TechnologyEnum

    @NotNull
//    @Enumerated(EnumType.STRING)
//    @Positive
    private ComplexityEnum complexity;  //ComplexityEnum

    @Positive
    private Double thickness;

    @PositiveOrZero
    private Double weight;

    @PositiveOrZero
    private Double substrateCost;

    @PositiveOrZero
    private Double testCost;


    @PrePersist
    @PreUpdate
    private void updateTechnologyAndCostsFields() {
        if (technology != null) {
            this.technologyDescription = technology.getDescription();
            this.technologyCoreMaterial = technology.getCoreMaterial();
            this.technologyMetalLayers = technology.getMetalLayers();

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
