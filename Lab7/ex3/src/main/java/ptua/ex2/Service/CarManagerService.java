package ptua.ex2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptua.ex2.Model.Car;
import ptua.ex2.Repository.CarRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CarManagerService {
    @Autowired
    private CarRepository carRepository;

    public Car save(Car car){
        return carRepository.save(car);
    }

    public Car getCarDetails(Long id){
        return carRepository.findByCarId(id);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

}
