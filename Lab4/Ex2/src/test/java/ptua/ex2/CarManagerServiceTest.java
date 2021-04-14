package ptua.ex2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import ptua.ex2.Model.Car;
import ptua.ex2.Repository.CarRepository;
import ptua.ex2.Service.CarManagerService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {
    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @BeforeEach
    public void setUp(){
        //Set up 2 cars with mockito -> Take in consideration that we are doing this, using Mockito because we are using an approach Top to Bottom
        Car bmw = new Car("M4", "BMW");
        Car fiat = new Car("Punto", "Fiat");
        Mockito.when(carRepository.findByCarId(Long.valueOf("1"))).thenReturn(bmw);
        Mockito.when(carRepository.findByCarId(Long.valueOf("2"))).thenReturn(fiat);
        Mockito.when(carRepository.findByCarId(Long.valueOf("-1"))).thenReturn(null);
    }

    @Test
    public void testValidId_CarFound() {
        //Check if the method is working properly and returning the previously 2 cars
        Car bmw_found = carService.getCarDetails(Long.valueOf("1"));
        assertThat(bmw_found.getModel()).isEqualTo("M4");

        Car fiat_found = carService.getCarDetails(Long.valueOf("2"));
        assertThat(fiat_found.getModel()).isEqualTo("Punto");
    }

    @Test
    public void testInvalidId_CarNotFound() {
        //Check if it returns nulls if the id doesn't exist
        Car car_notFound = carService.getCarDetails(Long.valueOf("-1"));
        assertThat(car_notFound).isNull();

        //Check if the method was called only once
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(Long.valueOf("-1"));
        Mockito.reset(carRepository);
    }
}
