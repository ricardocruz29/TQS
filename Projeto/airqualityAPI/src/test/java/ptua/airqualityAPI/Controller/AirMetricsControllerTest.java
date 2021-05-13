package ptua.airqualityAPI.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ptua.airqualityAPI.Cache.Cache;
import ptua.airqualityAPI.Models.AirMetricsData;
import ptua.airqualityAPI.Services.AirQualityService;
import static org.mockito.BDDMockito.given;
import org.mockito.internal.verification.VerificationModeFactory;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* Tests
       -Check when requesting Air data from city, the correct result is returned
       -Check if city has less than 2 letters then BadRequest is thrown
       -Check if city contains numbers then BadRequest is thrown
       -Check when requesting Air data from latitude and longitude, the correct result is returned
       -Check if latitude or longitude are out of range then BadRequest is thrown
       -Check when requested cacheStatistics, the correct result is returned
*/

@WebMvcTest
public class AirMetricsControllerTest {
    private AirMetricsData airMetricsData = null;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService airQualityService;

    @BeforeEach
    public void setUp() throws Exception{
        String json_response_paris = "{\"data\":{\"city\":{\"geo\":[48.856614,2.3522219],\"name\":\"Paris\"},\"iaqi\":{\"co\":{\"v\":0.1},\"no2\":{\"v\":12.7},\"o3\":{\"v\":31.3},\"pm10\":{\"v\":6.0},\"pm25\":{\"v\":13.0},\"so2\":{\"v\":0.6}}}}";
        ObjectMapper mapper = new ObjectMapper();
        airMetricsData = mapper.readValue(json_response_paris, AirMetricsData.class);
    }

    @AfterEach
    public void tearDown(){
        reset(airQualityService);
    }

    //Check when requesting Air data from city, the correct result is returned
    @Test
    public void TestCityRequestThenReturnCorrect() throws Exception{
        given(airQualityService.getAirMetricsCity("Paris")).willReturn(airMetricsData);

        mvc.perform(get("/airMetricsAPI/getAirMetricsCity?city=Paris").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(airMetricsData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.city.name", is(airMetricsData.getParamData().getCity().getCity_name())))
                .andExpect(jsonPath("$.data.iaqi.co.v", is(airMetricsData.getParamData().getIAQI().getCo().getMetricValue())))
                .andExpect(jsonPath("$.data.iaqi.no2.v", is(airMetricsData.getParamData().getIAQI().getNo2().getMetricValue())))
                .andExpect(jsonPath("$.data.iaqi.so2.v", is(airMetricsData.getParamData().getIAQI().getSo2().getMetricValue())))
                .andExpect(jsonPath("$.data.iaqi.pm10.v", is(airMetricsData.getParamData().getIAQI().getPm10().getMetricValue())))
                .andExpect(jsonPath("$.data.iaqi.pm25.v", is(airMetricsData.getParamData().getIAQI().getPm25().getMetricValue())))
                .andExpect(jsonPath("$.data.iaqi.o3.v", is(airMetricsData.getParamData().getIAQI().getO3().getMetricValue())));

        verify(airQualityService, VerificationModeFactory.times(1)).getAirMetricsCity("Paris");
    }

    //Check if city has less than 2 letters then BadRequest is thrown
    @Test
    public void TestInvalidCityLengthThenBadRequest() throws Exception{
        mvc.perform(get("/airMetricsAPI/getAirMetricsCity?city=Pa").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson("")))
                .andExpect(status().isBadRequest());

        verify(airQualityService, VerificationModeFactory.times(0)).getAirMetricsCity("Pa");
    }

    //Check if city contains numbers then BadRequest is thrown
    @Test
    public void TestInvalidCityWithNumberThenBadRequest() throws Exception{
        mvc.perform(get("/airMetricsAPI/getAirMetricsCity?city=Paris1").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson("")))
                .andExpect(status().isBadRequest());

        verify(airQualityService, VerificationModeFactory.times(0)).getAirMetricsCity("Paris1");
    }

    //Check when requesting Air data from latitude and longitude, the correct result is returned
    @Test
    public void TestLatitudeAndLongitudeRequestThenReturnCorrect() throws Exception{
        Double latitude = 48.856614;
        Double longitude = 2.3522219;
        given(airQualityService.getAirMetricsLatLong(latitude, longitude)).willReturn(airMetricsData);

        mvc.perform(get("/airMetricsAPI/getAirMetricsLatitudeLongitude?latitude=48.856614&longitude=2.3522219").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(airMetricsData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.city.name", is(airMetricsData.getParamData().getCity().getCity_name())))
                .andExpect(jsonPath("$.data.iaqi.co.v", is(airMetricsData.getParamData().getIAQI().getCo().getMetricValue())))
                .andExpect(jsonPath("$.data.iaqi.no2.v", is(airMetricsData.getParamData().getIAQI().getNo2().getMetricValue())))
                .andExpect(jsonPath("$.data.iaqi.so2.v", is(airMetricsData.getParamData().getIAQI().getSo2().getMetricValue())))
                .andExpect(jsonPath("$.data.iaqi.pm10.v", is(airMetricsData.getParamData().getIAQI().getPm10().getMetricValue())))
                .andExpect(jsonPath("$.data.iaqi.pm25.v", is(airMetricsData.getParamData().getIAQI().getPm25().getMetricValue())))
                .andExpect(jsonPath("$.data.iaqi.o3.v", is(airMetricsData.getParamData().getIAQI().getO3().getMetricValue())));

        verify(airQualityService, VerificationModeFactory.times(1)).getAirMetricsLatLong(latitude,longitude);
    }

    //Check if latitude or longitude are out of range then BadRequest is thrown
    @Test
    public void TestInvalidLatitudeOrLongitudeThenBadRequest() throws Exception{
        //latitude out of bounds [-90,90]
        mvc.perform(get("/airMetricsAPI/getAirMetricsLatitudeLongitude?latitude=92.0&longitude=2.3522219").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson("")))
                .andExpect(status().isBadRequest());

        verify(airQualityService, VerificationModeFactory.times(0)).getAirMetricsLatLong(92.0,2.3522219);

        //latitude and longitude out of bounds [-90,90] [-180,180]
        mvc.perform(get("/airMetricsAPI/getAirMetricsLatitudeLongitude?latitude=-92.0&longitude=182.0").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson("")))
                .andExpect(status().isBadRequest());
        verify(airQualityService, VerificationModeFactory.times(0)).getAirMetricsLatLong(-92.0,182.0);

        //longitude out of bounds [-180,180]
        mvc.perform(get("/airMetricsAPI/getAirMetricsLatitudeLongitude?latitude=75.0&longitude=-182.0").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson("")))
                .andExpect(status().isBadRequest());
        verify(airQualityService, VerificationModeFactory.times(0)).getAirMetricsLatLong(75.0,-182.0);

    }

    //-Check when requested cacheStatistics, the correct result is returned
    @Test
    public void TestRequestCacheStatisticsThenReturnCorrect() throws Exception{
        Cache cache = new Cache(2);
        AirMetricsData airMetricsData = new AirMetricsData();
        Double latitude = -10.0;
        Double longitude = 10.0;
        cache.storeRequest( latitude, longitude , airMetricsData);
        cache.getRequest(latitude,longitude);

        given(airQualityService.getCacheStatistics()).willReturn(cache);

        mvc.perform(get("/airMetricsAPI/getCacheStatistics").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfRequests", is(1)))
                .andExpect(jsonPath("$.numberOfMisses", is(0)))
                .andExpect(jsonPath("$.numberOfHits", is(1)));

        verify(airQualityService, VerificationModeFactory.times(1)).getCacheStatistics();

        //With wrong latitude
        cache.getRequest(50.0,longitude);
        mvc.perform(get("/airMetricsAPI/getCacheStatistics").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfRequests", is(2)))
                .andExpect(jsonPath("$.numberOfMisses", is(1)))
                .andExpect(jsonPath("$.numberOfHits", is(1)));

        verify(airQualityService, VerificationModeFactory.times(2)).getCacheStatistics();
    }

}
