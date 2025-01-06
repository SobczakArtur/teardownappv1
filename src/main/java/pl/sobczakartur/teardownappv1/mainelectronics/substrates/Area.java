package pl.sobczakartur.teardownappv1.mainelectronics.substrates;

import org.springframework.beans.factory.annotation.Autowired;
import pl.sobczakartur.teardownappv1.mainelectronics.substrates.entity.Substrate;


public class Area {

    private Substrate substrates;
    private final double area = substrates.getArea();

    @Autowired
    public Area(Substrate substrates) {
        this.substrates = substrates;
    }

    public double getArea() {
        return area;
    }

    public void display(){
        System.out.println(area);
    }

}
