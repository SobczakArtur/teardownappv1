package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import org.springframework.stereotype.Component;

//@Component
public enum Technology {
    TWO_LAYER_C("2 Layer C", "R4F4", 2, 1.64),
    TWO_LAYER_F("2 Layer F", "Poly", 2, 3.14),
    TWO_LAYER_FV("2 Layer FwV", "Poly", 2, 2.01),
    THREE_LAYER_C("3 Layer C", "R4F4", 3, 1.64),
    THREE_LAYER_F("3 Layer F", "Poly", 3, 3.14),
    THREE_LAYER_FV("3 Layer FwV", "Poly", 3, 2.01),
    FOUR_LAYER_C("4 Layer C", "R4F4", 4, 1.64),
    FOUR_LAYER_B("4 Layer B", "R4F4", 4, 1.41),
    FOUR_LAYER_F("4 Layer F", "Poly", 4, 3.14),
    FOUR_LAYER_FV("4 Layer FwV", "Poly", 4, 2.01);

    private final String description;
    private final String coreMaterial;
    private final Integer metalLayers;
    private final Double factor;


    Technology(String description, String coreMaterial, Integer metalLayers, Double factor) {
        this.description = description;
        this.coreMaterial = coreMaterial;
        this.metalLayers = metalLayers;
        this.factor = factor;
    }


    public String getDescription() {
        return description;
    }

    public String getCoreMaterial() {
        return coreMaterial;
    }


    public Integer getMetalLayers() {
        return metalLayers;
    }

    public Double getFactor() {
        return factor;
    }
}

//    private final String TWO_LAYER_C = "2 Layer C";
//    private final String TWO_LAYER_F = "2 Layer F";
//    private final String TWO_LAYER_FV = "2 Layer FwV";
//
//    private final String THREE_LAYER_C = "3 Layer C";
//    private final String THREE_LAYER_F = "3 Layer F";
//    private final String THREE_LAYER_FV = "3 Layer FwV";
//
//    private final String FOUR_LAYER_C = "4 Layer C";
//    private final String FOUR_LAYER_B = "4 Layer B";
//    private final String FOUR_LAYER_F = "4 Layer F";
//    private final String FOUR_LAYER_FV = "4 Layer FwV";

// 1.41 , 1.64 , 2.01 , 3.14

//    TWO_LAYER_C("2 Layer C", "R4F4", 2, 2.01),
//    TWO_LAYER_F("2 Layer F", "Poly", 2, 1.41),
//    TWO_LAYER_FV("2 Layer FwV", "Poly", 2, 1.64),
//    THREE_LAYER_C("3 Layer C", "R4F4", 3, 2.01),
//    THREE_LAYER_F("3 Layer F", "Poly", 3, 1.41),
//    THREE_LAYER_FV("3 Layer FwV", "Poly", 3, 1.64),
//    FOUR_LAYER_C("4 Layer C", "R4F4", 4, 2.01),
//    FOUR_LAYER_B("4 Layer B", "R4F4", 4, 3.14),
//    FOUR_LAYER_F("4 Layer F", "Poly", 4, 1.41),
//    FOUR_LAYER_FV("4 Layer FwV", "Poly", 4, 1.64);