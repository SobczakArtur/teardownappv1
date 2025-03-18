package pl.sobczakartur.teardownappv1.mainelectronics.substrates.enums;

import lombok.Getter;

@Getter
public enum TechnologyEnum {
    TWO_LAYER_C("2 Layer C", "R4F4", 2, 1.64),
    TWO_LAYER_F( "2 Layer F", "Poly", 2, 3.14),
    TWO_LAYER_FV( "2 Layer FwV", "Poly", 2, 2.01),
    THREE_LAYER_C("3 Layer C", "R4F4", 3, 1.64),
    THREE_LAYER_F( "3 Layer F", "Poly", 3, 3.14),
    THREE_LAYER_FV( "3 Layer FwV", "Poly", 3, 2.01),
    FOUR_LAYER_C( "4 Layer C", "R4F4", 4, 1.64),
    FOUR_LAYER_B( "4 Layer B", "R4F4", 4, 1.41),
    FOUR_LAYER_F("4 Layer F", "Poly", 4, 3.14),
    FOUR_LAYER_FV( "4 Layer FwV", "Poly", 4, 2.01);


    private final String description;
    private final String coreMaterial;
    private final Integer metalLayers;
    private final Double factor;


    TechnologyEnum(String description, String coreMaterial, Integer metalLayers, Double factor) {
        this.description = description;
        this.coreMaterial = coreMaterial;
        this.metalLayers = metalLayers;
        this.factor = factor;
    }
}
