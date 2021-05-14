package ptua.airqualityAPI.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import ptua.airqualityAPI.API.AQICN_API;
import ptua.airqualityAPI.Cache.Cache;
import ptua.airqualityAPI.Models.AirMetricsData;
import ptua.airqualityAPI.Services.AirQualityService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

/* Tests
       -Check when requesting Air data from city, the correct result is returned
       -Check if invalid city then null will be returned
       -Check when requesting Air data from latitude and longitude, the correct result is returned
       -Check if latitude or longitude are invalid then null is returned
       -Check if the the same city is requested many times, the api method only will be called once, wheter for city or latitude and longitude.
       -Check if the Cache is when requested is the expected.
*/
@ExtendWith(MockitoExtension.class)
public class AirQualityServiceTest {
    private AirMetricsData airMetricsData_Lisbon = null;
    private AirMetricsData airMetricsData_Paris = null;
    private AirMetricsData airMetricsData_Beijing = null;
    private AirMetricsData airMetricsData_Madrid= null;

    @Mock(lenient = true)
    private AQICN_API api_repository;

    @InjectMocks
    private AirQualityService service;

    @BeforeEach
    private void setUp() throws Exception{
        /* data */
        String json_response_Lisbon = "{\"data\":{\"city\":{\"geo\":[38.748611111111,-9.1488888888889],\"name\":\"Entrecampos, Lisboa, Portugal\"},\"iaqi\":{\"co\":null,\"no2\":{\"v\":10.0},\"o3\":{\"v\":24.8},\"pm10\":{\"v\":24.0},\"pm25\":{\"v\":25.0},\"so2\":{\"v\":0.8}}}}";
        String json_response_Paris = "{\"data\":{\"city\":{\"geo\":[48.856614,2.3522219],\"name\":\"Paris\"},\"iaqi\":{\"co\":{\"v\":0.1},\"no2\":{\"v\":21.2},\"o3\":{\"v\":30.9},\"pm10\":{\"v\":6.0},\"pm25\":{\"v\":33.0},\"so2\":{\"v\":0.6}}}}";
        String json_response_Beijing = "{\"data\":{\"city\":{\"geo\":[39.954592,116.468117],\"name\":\"Beijing (北京)\"},\"iaqi\":{\"co\":{\"v\":7.3},\"no2\":{\"v\":16.0},\"o3\":{\"v\":20.4},\"pm10\":{\"v\":32.0},\"pm25\":{\"v\":78.0},\"so2\":{\"v\":1.6}}}}";
        String json_response_Madrid = "{\"data\":{\"city\":{\"geo\":[40.4167754,-3.7037902],\"name\":\"Madrid\"},\"iaqi\":{\"co\":{\"v\":0.1},\"no2\":{\"v\":7.8},\"o3\":{\"v\":17.5},\"pm10\":{\"v\":10.0},\"pm25\":{\"v\":21.0},\"so2\":{\"v\":2.1}}}}";

        ObjectMapper mapper = new ObjectMapper();

        /* 2 of them will be used for the city method, and the other 2 for latitude and longitude method */
        airMetricsData_Lisbon = mapper.readValue(json_response_Lisbon, AirMetricsData.class);
        airMetricsData_Paris = mapper.readValue(json_response_Paris, AirMetricsData.class);
        airMetricsData_Beijing = mapper.readValue(json_response_Beijing, AirMetricsData.class);
        airMetricsData_Madrid = mapper.readValue(json_response_Madrid, AirMetricsData.class);

        // By City
        Mockito.when(api_repository.getAirMetris_City("Lisbon")).thenReturn(airMetricsData_Lisbon);
        Mockito.when(api_repository.getAirMetris_City("Paris")).thenReturn(airMetricsData_Paris);

        // By Latitude and Longitude
        Mockito.when(api_repository.getAirMetris_LatANDLong(39.954592,116.468117)).thenReturn(airMetricsData_Beijing); //Beijing
        Mockito.when(api_repository.getAirMetris_LatANDLong(40.4167754, -3.7037902)).thenReturn(airMetricsData_Madrid); //Madrid
    }

    @AfterEach
    public void tearDown(){
        reset(api_repository);
    }

    //Check when requesting Air data from city, the correct result is returned
    //Check if the the same city is requested many times, the api method only will be called once, wheter for city or latitude and longitude.
    @Test
    public void TestWhenGetRequestCityServiceThenReturnCorrectResults(){
        assertThat(service.getAirMetricsCity("Lisbon")).isEqualTo(airMetricsData_Lisbon);
        //without cache the getAirMetris_city("Paris") would be 3. In this case it must be 1
        service.getAirMetricsCity("Paris");
        assertThat(service.getAirMetricsCity("Paris")).isEqualTo(airMetricsData_Paris);
        assertThat(service.getAirMetricsCity("Paris")).isEqualTo(airMetricsData_Paris);

        verify(api_repository, VerificationModeFactory.times(1)).getAirMetris_City("Lisbon");
        verify(api_repository, VerificationModeFactory.times(1)).getAirMetris_City("Paris");
    }

    //Check if invalid city then null will be returned
    //Check if the the same city is requested many times, the api method only will be called once, wheter for city or latitude and longitude.
    @Test
    public void TestWhenGetRequestInvalidCityServiceThenReturnNull(){
        assertThat(service.getAirMetricsCity("teste")).isNull();
        verify(api_repository, VerificationModeFactory.times(1)).getAirMetris_City("teste");
        //In this case because "teste" was not successfull, it will not be stored on the cache, and will be requested again. Expected result is 2
        service.getAirMetricsCity("teste");
        verify(api_repository, VerificationModeFactory.times(2)).getAirMetris_City("teste");
    }

    //Check when requesting Air data from latitude and longitude, the correct result is returned
    //Check if the the same city is requested many times, the api method only will be called once, wheter for city or latitude and longitude.
    @Test
    public void TestWhenGetRequestLatLongServiceThenReturnCorrectResults(){
        assertThat(service.getAirMetricsLatLong(39.954592,116.468117)).isEqualTo(airMetricsData_Beijing);
        service.getAirMetricsCity("Madrid");
        assertThat(service.getAirMetricsLatLong(40.4167754, -3.7037902)).isEqualTo(airMetricsData_Madrid);


        verify(api_repository, VerificationModeFactory.times(1)).getAirMetris_LatANDLong(39.954592,116.468117);
        verify(api_repository, VerificationModeFactory.times(1)).getAirMetris_LatANDLong(40.4167754, -3.7037902);
        verify(api_repository, VerificationModeFactory.times(1)).getAirMetris_City("Madrid");
    }

    //Check if latitude or longitude are invalid then null is returned
    @Test
    public void TestWhenGetRequestInvalidLatLongServiceThenReturnNull(){
        assertThat(service.getAirMetricsLatLong(-200.0,180.0)).isNull();
    }
}
