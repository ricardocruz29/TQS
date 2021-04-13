package ptua.ex2;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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


@WebMvcTest
public class CarControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CarManagerService carService;

    @Test
    void testPostCarAndSeeTheReturned() throws Exception{
        //Create car and set his ID
        Car bmw = new Car("M4", "BMW");
        bmw.setCarId(1L);
        //Simulate when we try to save a car it returns the previously created. Remember we have just the skeleton of carService
        //Do this when we don't have carService yet
        given(carService.getCarDetails(Long.valueOf("1"))).willReturn(bmw);
        //when( carService.save(Mockito.any())).thenReturn(bmw);


        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(bmw)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("M4")))
                .andExpect(jsonPath("$.maker", is("BMW")));
        verify(carService, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(carService);
    }

    @Test
    public void givenCar_whenGetCar_thenReturnJson() throws Exception{
        Car bmw = new Car("M4", "BMW");
        bmw.setCarId(1L);

        given(carService.getCarDetails(Long.valueOf("1"))).willReturn(bmw);
        //when(service.getCarDetails(Long.valueOf("1"))).thenReturn(bmw);

        mvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("M4")))
                .andExpect(jsonPath("$.maker", is("BMW")));
        verify(carService, VerificationModeFactory.times(1)).getCarDetails(Long.valueOf("1"));
        reset(carService);
    }
}
