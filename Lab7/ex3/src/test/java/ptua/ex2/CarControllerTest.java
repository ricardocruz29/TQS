package ptua.ex2;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import ptua.ex2.Controller.CarController;
import ptua.ex2.Model.Car;
import ptua.ex2.Service.CarManagerService;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@Testcontainers
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CarManagerService carService;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    void testPostCarAndSeeTheReturned() throws Exception{
        //Create car and set his ID
        Car bmw = new Car("M4", "BMW");
        bmw.setCarId(1L);
        //Simulate when we try to save a car it returns the previously created. Remember we have just the skeleton of carService
        //Do this when we don't have carService yet
        given(carService.save(Mockito.any())).willReturn(bmw);
        //when( carService.save(Mockito.any())).thenReturn(bmw);

        RestAssuredMockMvc.given().auth().none().contentType("application/json")
                .body(JsonUtil.toJson(bmw))
                .when().post("/api/cars")
                .then().statusCode(201)
                .body("maker", Matchers.equalTo("BMW"))
                .body("model", Matchers.equalTo("M4"));
        /*
        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bmw)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("M4")))
                .andExpect(jsonPath("$.maker", is("BMW")));
         */

        verify(carService, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(carService);
    }

    @Test
    public void givenCar_whenGetCar_thenReturnJson() throws Exception{
        Car bmw = new Car("M4", "BMW");
        bmw.setCarId(1L);

        given(carService.getCarDetails(Long.valueOf("1"))).willReturn(bmw);

        RestAssuredMockMvc.given().auth().none()
                .when().get("/api/cars/1")
                .then().statusCode(200)
                .body("maker", is("BMW"))
                .body("model", is("M4"));

        /*
        mvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("M4")))
                .andExpect(jsonPath("$.maker", is("BMW")));
         */

        verify(carService, VerificationModeFactory.times(1)).getCarDetails(Long.valueOf("1"));
        reset(carService);
    }
}
