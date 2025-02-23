package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;

public class Area {

    private Substrate substrate;

    public double getArea() {
        return substrate.getArea();
    }

    public void display(){
        System.out.println(getArea());
    }

}
