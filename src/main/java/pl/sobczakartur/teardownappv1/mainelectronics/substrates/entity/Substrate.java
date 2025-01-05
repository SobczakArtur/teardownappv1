package pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.Complexity;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.SubstrateTestCost;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.Technology;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Substrate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long substrateId;
    private String assemblyName;
    private String substrateMarking;
    private String manufacturer;
    private Technology technology;
    private Technology description;
    private Technology coreMaterial;
    private double area;
    private Technology metalLayers;
    private Complexity complexity;
    private double thickness;
    private double weight;
    private SubstrateTestCost substrateTestCost;

}
