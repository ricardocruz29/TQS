package ptua.ex2.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

//import lombok.Data;

//@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;
    private String model;
    private String maker;

    // Constructors //
    public Car(){

    }

    public Car(String model, String maker){
        this.model = model;
        this.maker = maker;
    }

    // Equals and hashCode //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId.equals(car.carId) && model.equals(car.model) && maker.equals(car.maker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, model, maker);
    }

    // toString //
    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", model='" + model + '\'' +
                ", maker='" + maker + '\'' +
                '}';
    }

    /* Getters & Setters
     Generated automatically by library lombok (bugs of importing)
    */

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

}
