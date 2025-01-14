package pl.sobczakartur.teardownappv1.mainelectronics;

import pl.sobczakartur.teardownappv1.mainelectronics.substrates.TechnologyEnum;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;


public class None {

    public void setTechnologyValues(TechnologyEnum technology){
        Substrate substrate = new Substrate();
        substrate.setTechnologyName(technology.getName());
        substrate.setTechnologyDescription(technology.getDescription());
        substrate.setTechnologyCoreMaterial(technology.getCoreMaterial());
        substrate.setTechnologyMetalLayers(technology.getMetalLayers());
    }

}

