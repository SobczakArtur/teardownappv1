package pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.ComplexityEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.TechnologyEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.util.SubstrateCost;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
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
    private Integer technologyMetalLs;  //TechnologyEnum

//    @NotNull
    @Positive
    @Enumerated(EnumType.STRING)
    private ComplexityEnum complexity;  //ComplexityEnum

    @Positive
    private Double thickness;

    @PositiveOrZero
    private Double weight;

    @Column(precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2)")
    @PositiveOrZero
    private BigDecimal substrateCost;

    @Column(precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2)")
    @PositiveOrZero
    private BigDecimal testCost;



    @OneToMany(mappedBy = "substrate", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AssemblyBlocks> assemblyBlocks = new ArrayList<>();


    @PrePersist
    @PreUpdate
    private void updateTechnologyAndCostsFields() {
        if (technology != null) {
            this.technologyDescription = technology.getDescription();
            this.technologyCoreMaterial = technology.getCoreMaterial();
            this.technologyMetalLs = technology.getMetalLs();

            if (technology != null && complexity != null && areaValue != null) {
                SubstrateCost substrateCostObject = new SubstrateCost(technology, complexity, areaValue);
                this.substrateCost = substrateCostObject.getSubstrateCost();
                this.testCost = substrateCostObject.getTestCost();
                }
        }
    }

    public void recalculateFields() {
        updateTechnologyAndCostsFields();
    }
}
