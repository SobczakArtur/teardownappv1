package pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums;

import lombok.Getter;

@Getter
public enum TechnologyEnum {
    TWO_L_C("2 L C", "R4F4", 2, 1.64),
    TWO_L_F( "2 L F", "Poly", 2, 3.14),
    TWO_L_FV( "2 L FwV", "Poly", 2, 2.01),
    THREE_L_C("3 L C", "R4F4", 3, 1.64),
    THREE_L_F( "3 L F", "Poly", 3, 3.14),
    THREE_L_FV( "3 L FwV", "Poly", 3, 2.01),
    FOUR_L_C( "4 L C", "R4F4", 4, 1.64),
    FOUR_L_B( "4 L B", "R4F4", 4, 1.41),
    FOUR_L_F("4 L F", "Poly", 4, 3.14),
    FOUR_L_FV( "4 L FwV", "Poly", 4, 2.01);


    private final String description;
    private final String coreMaterial;
    private final Integer metalLs;
    private final Double factor;


    TechnologyEnum(String description, String coreMaterial, Integer metalLs, Double factor) {
        this.description = description;
        this.coreMaterial = coreMaterial;
        this.metalLs = metalLs;
        this.factor = factor;
    }
}