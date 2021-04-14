package ptua.ex2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import ptua.ex2.Model.Car;
import ptua.ex2.Repository.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

//@AutoConfigureTestDatabase
@TestPropertySource( locations = "application-integrationtest.properties")
public class CarControllerTemplateTestIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }


    @Test
    public void testValidInput_thenCreateCar() {
        Car bmw = new Car("M4", "BMW");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", bmw, Car.class);

        List<Car> cars_found = carRepository.findAll();
        assertThat(cars_found).extracting(Car::getModel).containsOnly("M4");
        assertThat(cars_found).extracting(Car::getMaker).containsOnly("BMW");
    }

    @Test
    public void testGivenCars_whenGetCars_thenStatus200()  {
        createTestCar("M4", "BMW");
        createTestCar("Punto", "Fiat");


        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("BMW","Fiat");
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("M4","Punto");
    }

    private void createTestCar(String model, String maker) {
        Car car = new Car(model, maker);
        carRepository.saveAndFlush(car);
    }
}
