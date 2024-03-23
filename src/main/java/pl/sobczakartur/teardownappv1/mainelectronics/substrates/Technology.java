package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

public enum Technology {
    TWO_LAYER_C("2 Layer C", "R4F4", 2),
    TWO_LAYER_F("2 Layer F", "Poly", 2),
    TWO_LAYER_FV("2 Layer FwV", "Poly", 2),
    THREE_LAYER_C("3 Layer C", "R4F4", 3),
    THREE_LAYER_F("3 Layer F", "Poly", 3),
    THREE_LAYER_FV("3 Layer FwV", "Poly", 3),
    FOUR_LAYER_C("4 Layer C", "R4F4", 4),
    FOUR_LAYER_B("4 Layer B", "R4F4", 4),
    FOUR_LAYER_F("4 Layer F", "Poly", 4),
    FOUR_LAYER_FV("4 Layer FwV", "Poly", 4);

    private final int metalLayers;


    Technology(String description, String coreMaterial, int metalLayers) {
        this.metalLayers = metalLayers;
    }

    public int getMetalLayers() {
        return metalLayers;
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
