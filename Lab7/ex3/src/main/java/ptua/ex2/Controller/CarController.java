package ptua.ex2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptua.ex2.Model.Car;
import ptua.ex2.Service.CarManagerService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar (@RequestBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carManagerService.save(car);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping(path = "/cars", produces = "application/json")
    public List<Car> getAllCars(){
        return carManagerService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId) throws InvalidConfigurationPropertyValueException {
        Car car = carManagerService.getCarDetails(carId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(car, status);
    }
}
