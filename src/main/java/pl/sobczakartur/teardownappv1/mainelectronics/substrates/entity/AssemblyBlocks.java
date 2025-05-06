package pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums.FunctionalBlock;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assembly_blocks")
public class AssemblyBlocks {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assemblyBlocksId;

    @Column(length = 255)
    private String assemblyName;

    @Column(length = 255)
    @Enumerated(EnumType.STRING)
    private FunctionalBlock functionalBlock;


    @ManyToOne
    @JoinColumn(name = "substrate_id")
    private Substrate substrate;

    @PrePersist
    @PreUpdate
    private void updateAssemblyName() {
        if (substrate != null) {
            this.assemblyName = substrate.getAssemblyName();
        }
    }

    public void syncAssemblyNameWithSubstrate() {
        updateAssemblyName();
    }
}
