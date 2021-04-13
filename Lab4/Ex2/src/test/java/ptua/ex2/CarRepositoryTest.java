package ptua.ex2;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ptua.ex2.Model.Car;
import ptua.ex2.Repository.CarRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;


    @Test
    public void whenFindByCarId_thenReturnCar(){
        Car bmw = new Car("BMW","M4");
        entityManager.persistAndFlush(bmw);

        Car bmw_found = carRepository.findByCarId(Long.valueOf("1"));
        assertThat(bmw_found.getModel()).isEqualTo(bmw.getModel());
    }

    @Test
    public void whenInvalidCarId_thenReturnNull(){
        Car car_notFound = carRepository.findByCarId(Long.valueOf("-1"));
        assertThat(car_notFound).isNull();
    }
}
