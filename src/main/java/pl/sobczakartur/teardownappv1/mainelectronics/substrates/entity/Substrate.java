package pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.Complexity;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.SubstrateTestCost;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.Technology;

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
